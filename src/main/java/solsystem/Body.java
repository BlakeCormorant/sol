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
    public String name;
    private double mass;
    private double aphelion;
    private double perihelion;
    private double period; // days
    private double diameter;
    private double minTempK;
    private double maxTempK;
    public Color color;
    public Coords<Double> location = new Coords<>((double)0, (double)0); // cannot do Coords<double>, since containers cannot contain primitives.
    private Coords<Double> velocity;
    private Logger dbgLog;
/*
    public Body(Body b) {
        this.name = b.name;
        this.mass = b.mass;
        this.diameter = b.diameter;
        this.aphelion = b.aphelion;
        this.perihelion = b.perihelion;
        this.period = b.period;
        this.minTempK = b.minTempK;
        this.maxTempK = b.maxTempK;
        this.color = b.color;
        this.location = b.location.

    }
*/
    // Aphelion and perihelion are in 1000kms, diam in km, mass relative to sun x10^-6. Orbital speed in km(per s?).
    public Body(String name, double mass, double diameter, double aphelion, double perihelion, double orbitalSpeed, double period, double minTempK, double maxTempK, Color colour){
        dbgLog = Logger.getLogger(Framework.class.getName());
        dbgLog.log(Level.INFO, "Body Constructor");
        this.name = name;
        this.mass = mass;
        this.diameter = diameter;
        this.aphelion = aphelion;
        this.perihelion = perihelion;
        this.period = period;
        this.minTempK = minTempK;
        this.maxTempK = maxTempK;
        this.color = colour;

        // Need to calculate a suitable starting position and orbital speed (vector).
        CalculateStartingLocation();
        //CalculateScreenLocation(); // Must be done elsewhere, as this Class won't have access to screen position, zoom, etc.

    }

    /*public Coords<Double> GetLocation(){
        return location;
    }
*/
    public double GetDiameter(){ return diameter; }

    public double GetAphelion(){ return aphelion; }

    public double GetPerihelion(){ return perihelion; }

    public double GetPeriod(){ return period; }

    public double GetMinTempK(){ return minTempK; }

    public double GetMaxTempK(){ return maxTempK; }

    private void CalculateStartingLocation(){
        System.out.printf("CalculateStartingLocation()...");
        // This is rubbish at the moment
        //System.out.printf("aphelion: %f\n", aphelion);
        location.x = aphelion;
        //System.out.printf("assignment made\n");
        //System.out.printf("location: %f\n", location.x);
        location.y = (double)0.0;
    }

}
