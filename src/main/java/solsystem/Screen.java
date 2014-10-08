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
    static Coords<Integer> viewCentre = new Coords<Integer>((int)0,(int)0);
    public Screen(){
        if (screenInstances > 0){
            throw new RuntimeException("Screen instance already exists.");
        }
        screenInstances++;


    }

    public Coords<Integer> CalculateScreenPos(Coords<Double> universePos){
        Coords<Integer> screenPos = new Coords<>(100, 110);
        return screenPos;
    }

}