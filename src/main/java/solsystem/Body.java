package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.lang.Double;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

/**
 * Body class.  Ideally, probably don't want this used, but to act as a template for derived classes. What do I do?
 *
 * @author Blake Cormorant
 */

public class Body {
    private String name;
    private double mass;
    private double aphelion;
    private double perihelion;
    public Color color;
    //private Coords<Double> location = new Coords<>(new Double(0), new Double(0)); // cannot do Coords<double>, since containers cannot contain primitives.
    //private Coords<Double> velocity = new Coords<>(new Double(0), new Double(0));
    private Coords<Double> location = new Coords<>((double)0, (double)0); // cannot do Coords<double>, since containers cannot contain primitives.
    //public Coords<Integer> screenLocation; // This could be set by another class - actually,it should not be stored here
    private Coords<Double> velocity;
    private Logger dbgLog;

    // Aphelion and perihelion are in 1000kms, diam in km, mass relative to sun x10^-6. Orbital speed in km(per s?).
    public Body(String name, double mass, double diameter, double aphelion, double perihelion, double orbitalSpeed, Color colour){
        dbgLog = Logger.getLogger(Framework.class.getName());
        dbgLog.log(Level.INFO, "Body Constructor");
        this.name = name;
        this.mass = mass;
        this.aphelion = aphelion;
        this.perihelion = perihelion;
        this.color = colour;

        // Need to calculate a suitable starting position and orbital speed (vector).
        CalculateStartingLocation();
        //CalculateScreenLocation(); // Must be done elsewhere, as this Class won't have access to screen position, zoom, etc.

    }

    public Coords<Double> GetLocation(){
        return location;
    }

    private void CalculateStartingLocation(){
        System.out.printf("CalculateStartingLocation()...");
        // This is rubbish at the moment
        System.out.printf("aphelion: %f\n", aphelion);
        location.x = aphelion;
        System.out.printf("assignment made\n");
        System.out.printf("location: %f\n", location.x);
        location.y = (double)0.0;
    }

}
