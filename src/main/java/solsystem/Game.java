package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Stack;
import java.util.List;
import java.util.Iterator;


/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
    //static const numPlanets = 3;
    //private Body planet[numPlanets]; // This would require a default constructor, which I don't want?
    private Screen screen; //= new Screen(800, 600);
    private Universe universe; // = new Universe();
    private boolean selected;
    private Body selectedBody;
    private boolean server;
    private MPUpdater mpUpdater;

    public double gameDay;

    public Game(boolean server)
    {
        System.out.println("Game() (constructor)...");
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;

        this.server = server;


        // Why on earth does this little bit need to be run as a thread?! It takes hardly any time...
        // I guess because if there was lots of initialisation, we don't want the app to hang.
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();

    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
        System.out.println("Game.Initialize()...");
        screen = new Screen(800, 600);
        universe = new Universe();
        gameDay = 0;
        selected = false;
        if(server){
            System.out.println("Creating server...");
            mpUpdater = new Server();
            mpUpdater.Connect();
        }
        else
        {
            System.out.println("Creating client...");
            mpUpdater = new Client();
            mpUpdater.Connect();
        }

    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
        System.out.println("Game.LoadContent()...");

    }    
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        gameDay += 0.1;
        //System.out.println("Game.UpdateGame()..."); // This happens rather quickly
        universe.UpdatePlanetPositions(gameDay);
        mpUpdater.Update(gameDay, this);

        gameDay = mpUpdater.GetGameDayUpdate(gameDay);

    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition)
    {
        // This happens every UpdateGame
        if(selected){
            // Centre the view
            screen.SetScreenCentre(selectedBody.location);
        }

	    // Draw the cursor with info
	    universe.DrawCursorInfo(g2d, screen, mousePosition);

        // Draw the planets
        universe.DrawEverything(g2d, screen);

        // Draw the game date
        DrawGameDate(g2d);
    }

    private void DrawGameDate(Graphics2D g2d){
        String info;
        int x = 10;
        int y = screen.GetWindowSize().y - 20;
        info = String.format("Day: %.1f", gameDay);
        g2d.setColor(Color.lightGray);
        g2d.drawString(info, x, y);
    }

    public void HandleKeyboard(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_MINUS)
            screen.ZoomOut();
        if(e.getKeyCode() == KeyEvent.VK_EQUALS) // this is actually minus
            screen.ZoomIn();
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            screen.PanRight();
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            screen.PanLeft();
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            screen.PanUp();
        if(e.getKeyCode() == KeyEvent.VK_UP)
            screen.PanDown();


    }

    public void HandleMouseClick(MouseEvent e){
        // Select the currently selected body
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println("Select");
            selected = true;
            Coords<Double> mouseUniverseLocation = screen.GetUniverseCoords(e.getPoint());
            selectedBody = universe.GetNearest(mouseUniverseLocation);
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            System.out.println("Unselect");
            selected = false;
        }


    }

}
