package solsystem;

import java.awt.*;

/**
 * Created by BlakeCormorant on 04/11/14.
 */
public class Moon extends Body {

    private Planet planet;

    public Moon(String name, double mass, double radius, double distance, double period, double minTempK, double maxTempK,
                Color colour, Planet planet) {
        super(name, mass, radius, distance, period, minTempK, maxTempK, colour, planet);
        System.out.printf("Moon Constructor\n");

        this.planet = planet;
        CalculateStartingLocation();
    }

    @Override
    protected void CalculateStartingLocation(){
        System.out.printf("Moon: CalculateStartingLocation()...");
        location.x = this.distance + planet.GetDistance();
        location.y = (double)0.0;
    }


    public Planet GetPlanet(){
        return this.planet;
    }



}
