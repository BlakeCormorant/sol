package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Blake Cormorant on 05/10/14.
 */
public class Planet extends Body{
    public Planet(String name, double mass, double diameter, double aphelion, double perihelion, double orbitalSpeed, double period, Color colour){
        super(name, mass, diameter, aphelion, perihelion, orbitalSpeed, period, colour);
        System.out.printf("Planet Constructor\n");
    }
}
