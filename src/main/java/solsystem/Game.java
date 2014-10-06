package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
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
    private List<Planet> planetList = new ArrayList();

    public Game()
    {
        System.out.println("Game() (constructor)...");
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
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

        // Creating the planets here
        planetList.add(new Planet("Mercury", 0.16601, 4878, 69817, 108943, 47.9, Color.yellow));
        planetList.add(new Planet("Venus", 2.4478383, 12104, 108943, 107476, 35.0, Color.green));
        planetList.add(new Planet("Earth", 3.00348959632, 12756, 152098, 147098, 29.8, Color.blue));
        planetList.add(new Planet("Mars", 0.3227151, 6787, 249232, 206655, 24.1, Color.red));
        planetList.add(new Planet("Jupiter", 954.79194, 142800, 816002, 740680, 13.1, Color.orange));
        planetList.add(new Planet("Saturn", 285.8860, 120000, 1503509, 1349824, 9.6, Color.yellow));
        planetList.add(new Planet("Uranus", 43.66244, 51200, 3006318, 2734998, 6.8, Color.green));
        planetList.add(new Planet("Neptune", 51.51389, 48600, 4537040, 4459753, 5.4, Color.blue));
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
        //System.out.println("Game.UpdateGame()..."); // This happens rather quickly

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

        // Draw some text
        g2d.setColor(Color.white);
        g2d.drawString("This position.", mousePosition.x, mousePosition.y); // or GetX()?

        // Draw a dot
        g2d.setColor(Color.red);
        g2d.fillOval(50,50,3,3);

        // Draw the planets
        DrawPlanets(g2d);
    }

    public void DrawPlanets(Graphics2D g2d){
        // Iterator
        Iterator pli = planetList.iterator();
        while(pli.hasNext()){
            g2d.setColor(pli.next().color);
            //g2d.fillOval(pli.next().screenLocation.x, screenLocation.y, 3, 3);
        }
    }
}
