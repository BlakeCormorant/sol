package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.lang.Double;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

/**
 * Screen class.  I was going to put the functions for calculating plotting coords here
 *
 * @author Blake Cormorant
 */

public final class Screen{
    // Will probably be static methods, as I don't think I need to instantiate one of these classes
    // Static constructor?  What would that do?  Wouldn't make sense, so doesn't compile - good.
    // Actually, probably would like to instantiate one of these, so probably still have static variables (as only have one screen).
    static int screenInstances = 0;
    //static Coords<Integer> centre = new Coords<Integer>(new Integer(0),new Integer(0));
    static Coords<Double> viewUniverseCentre = new Coords<>((double)0,(double)0);
    static double viewUniverseRightOfCentre = 250000; // If we know the window size, ie ratio, then we can work out all the other coords.
    static private Coords<Integer> windowSize = new Coords<>((int)0,(int)0);
    public Screen(int winWidth, int winHeight){
        if (screenInstances > 0){
            throw new RuntimeException("Screen instance already exists.");
        }
        screenInstances++;

        windowSize.x = winWidth;
        windowSize.y = winHeight;


    }

    public void SetScreenCentre(double xUniverseViewCentre, double yUniverseViewCentre){
        viewUniverseCentre.x = xUniverseViewCentre;
        viewUniverseCentre.y = yUniverseViewCentre;
    }

    //public SetScreenZoom()

    public Coords<Integer> CalculateScreenPos(Coords<Double> universePos){
        double xUnity = ((universePos.x - viewUniverseCentre.x) / viewUniverseRightOfCentre);
        double yUnity = ((universePos.y - viewUniverseCentre.y) / viewUniverseRightOfCentre);

        xUnity = (xUnity + 1.0) / 2.0;
        yUnity = (yUnity + 1.0) / 2.0;

        double xScaled = xUnity * windowSize.x;
        double yScaled = yUnity * windowSize.y;

        Coords<Integer> screenPos = new Coords<>((int)xScaled, (int)yScaled);
        return screenPos;
    }

}