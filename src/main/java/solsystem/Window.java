package solsystem;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Creates frame and set its properties.
 * 
 * @author www.gametutorial.net
 */

public class Window extends JFrame{

    private Coords<Integer> windowSize = new Coords<>(800, 600);
        
    private Window()
    {
        System.out.println("Running Window() (constructor)");

        // Sets the title for this frame.
        this.setTitle("Game title v0.1");

        // Sets size of the frame.
        if(false) // Full screen mode
        {
            // Disables decorations for this frame.
            this.setUndecorated(true);
            // Puts the frame to full screen.
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
        else // Window mode
        {
            // Size of the frame.
            this.setSize(windowSize.x, windowSize.y);
            // Puts frame to center of the screen.
            this.setLocationRelativeTo(null);
            // So that frame cannot be resizable by the user.
            this.setResizable(false);
        }
        
        // Exit the application when user close frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates the instance of the Framework.java that extends the Canvas.java and puts it on the frame.
        System.out.println("Creating new Framework for ContentPane...");
        this.setContentPane(new Framework());
        System.out.println("done.");
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        System.out.printf("Running main() num args: %d\n", args.length);
        if(args.length > 0) {
            System.out.printf("arg[0] %s\n", args[0].toString());
        }
        // Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
