package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Blake Cormorant on 05/10/14.
 */
public class Sun extends Body{
    public Sun(String name, double mass, double diameter, double aphelion, double perihelion, double orbitalSpeed, Color colour){
        super(name, mass, diameter, aphelion, perihelion, orbitalSpeed, colour);
        System.out.printf("Sun Constructor\n");
    }
}



