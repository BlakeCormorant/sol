package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Stack;
import java.util.List;


/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
    //static const numPlanets = 3;
    //private Body planet[numPlanets]; // This would require a default constructor, which I don't want?
    private List<Body> planetList = new ArrayList();

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

        // Try creating the planets here
        //Planet aPlanet = new Planet("Mercury", 10, 20, Color.yellow, Coords((double)50,(double)50), Coords((double)2,(double)3));

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
    }
}