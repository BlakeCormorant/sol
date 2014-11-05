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
    protected double mass;
    protected double radius;
    protected double distance; // 1000 kms
    protected double period; // days
    protected double minTempK;
    protected double maxTempK;
    public Color color;
    public Coords<Double> location = new Coords<>((double)0, (double)0); // cannot do Coords<double>, since containers cannot contain primitives.
    private Coords<Double> velocity;
    private Logger dbgLog;
/*
    public Body(Body b) {
        this.name = b.name;
        this.mass = b.mass;
        this.radius = b.radius;
        this.aphelion = b.aphelion;
        this.perihelion = b.perihelion;
        this.period = b.period;
        this.minTempK = b.minTempK;
        this.maxTempK = b.maxTempK;
        this.color = b.color;
        this.location = b.location.

    }
*/
    // diam in km, mass relative to sun x10^-6. Orbital speed in km(per s?).
    public Body(String name, double mass, double radius, double distance,
                double period, double minTempK, double maxTempK, Color colour){
        dbgLog = Logger.getLogger(Framework.class.getName());
        dbgLog.log(Level.INFO, "Body Constructor");
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.distance = distance;
        this.period = period;
        this.minTempK = minTempK;
        this.maxTempK = maxTempK;
        this.color = colour;

        // Need to calculate a suitable starting position and orbital speed (vector).
        CalculateStartingLocation();
        //CalculateScreenLocation(); // Must be done elsewhere, as this Class won't have access to screen position, zoom, etc.

    }

    public Body(String name, double mass, double radius, double distance,
                double period, double minTempK, double maxTempK, Color colour, Planet planet){
        dbgLog = Logger.getLogger(Framework.class.getName());
        dbgLog.log(Level.INFO, "Body Constructor");
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.distance = distance;
        this.period = period;
        this.minTempK = minTempK;
        this.maxTempK = maxTempK;
        this.color = colour;

        // Need to calculate a suitable starting position and orbital speed (vector).
        //CalculateStartingLocation();
        //CalculateScreenLocation(); // Must be done elsewhere, as this Class won't have access to screen position, zoom, etc.

    }

    /*public Coords<Double> GetLocation(){
        return location;
    }
*/
    public double GetRadius(){ return radius; }

    public double GetDistance(){ return distance; }

    public double GetPeriod(){ return period; }

    public double GetMinTempK(){ return minTempK; }

    public double GetMaxTempK(){ return maxTempK; }

    protected void CalculateStartingLocation(){
        System.out.println("CalculateStartingLocation()...");
        // This is rubbish at the moment
        //System.out.printf("aphelion: %f\n", aphelion);
        location.x = distance;
        //System.out.printf("assignment made\n");
        //System.out.printf("location: %f\n", location.x);
        location.y = 0.0;
    }

}
