package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.lang.Double;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

/**
 * Body class.
 *
 * @author Blake Cormorant
 */

public class Body {
    private String name;
    private double mass;
    private double orbit;
    private Color color;
    private Coords<Double> location = new Coords<>(); // cannot do Coords<double>, since containers cannot contain primitives.
    private Coords<Double> velocity = new Coords<>();
    private Logger dbgLog;

    public Body(String bodyName, double bodyMass, double bodyOrbit, Color bodyColour, Coords<Double> startLocation, Coords<Double> startVelocity){
        dbgLog = Logger.getLogger(Framework.class.getName());
        dbgLog.log(Level.INFO, "Body Constructor");
        name = bodyName;
        mass = bodyMass;
        orbit = bodyOrbit;
        color = bodyColour;
        velocity.Set(startVelocity.x, startVelocity.y);
        location.Set(startLocation.x, startLocation.y);
    }
}
