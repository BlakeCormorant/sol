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
    final float panDivisor = 10;

    public Screen(int winWidth, int winHeight){
        if (screenInstances > 0){
            throw new RuntimeException("Screen instance already exists.");
        }
        screenInstances++;

        windowSize.x = winWidth;
        windowSize.y = winHeight;


    }

    public Coords<Integer> GetWindowSize(){
        return windowSize;
    }

    public void SetScreenCentre(double xUniverseViewCentre, double yUniverseViewCentre){
        viewUniverseCentre.x = xUniverseViewCentre;
        viewUniverseCentre.y = yUniverseViewCentre;
    }
    public void SetScreenCentre(Coords<Double> universeViewCentre){
        SetScreenCentre(universeViewCentre.x, universeViewCentre.y);
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

    public void ZoomOut(){
        viewUniverseRightOfCentre *= 2;
    }
    public void ZoomIn(){
        viewUniverseRightOfCentre /= 2;
    }
    public void PanRight(){
        viewUniverseCentre.x += (int)((float)viewUniverseRightOfCentre / panDivisor);
    }
    public void PanLeft(){
        viewUniverseCentre.x -= (int)((float)viewUniverseRightOfCentre / panDivisor);
    }
    public void PanUp(){
        viewUniverseCentre.y += (int)((float)viewUniverseRightOfCentre / panDivisor);
    }
    public void PanDown(){
        viewUniverseCentre.y -= (int)((float)viewUniverseRightOfCentre / panDivisor);
    }
    public double GetZoom(){
        return viewUniverseRightOfCentre;
    }
    public Coords<Double> GetUniverseCoords(Point screenPosition){
        Coords<Double> retCoords = new Coords<>((double)0,(double)0);

        double xUnity = (double)screenPosition.x / windowSize.x;
        double yUnity = (double)screenPosition.y / windowSize.y;

        xUnity = (xUnity * 2) - 1;
        yUnity = (yUnity * 2) - 1;

        double xScaled = (xUnity * viewUniverseRightOfCentre) + viewUniverseCentre.x;
        double yScaled = (yUnity * viewUniverseRightOfCentre) + viewUniverseCentre.y;

        retCoords.x = xScaled;
        retCoords.y = yScaled;

        return retCoords;
    }
}