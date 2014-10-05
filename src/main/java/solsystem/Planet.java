package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Planet extends Body{
    public Planet(String bodyName, double bodyMass, double bodyOrbit, Color bodyColour, Coords<Double> startLocation,
                  Coords<Double> startVelocity){
        super(bodyName, bodyMass, bodyOrbit, bodyColour, startLocation, startVelocity);
        System.out.printf("Planet Constructor\n");
    }
}
