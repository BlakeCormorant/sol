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
    public Sun(String name, double mass, double radius, double distance, double period, double minTempK, double maxTempK, Color colour){
        super(name, mass, radius, distance, period, minTempK, maxTempK, colour);
        System.out.printf("Sun Constructor\n");
    }
}



