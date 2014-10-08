package solsystem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.lang.Double;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import java.lang.Math;

/**
 * Universe contains everything 08/10/14.
 * @author Blake Cormorant
 */
public class Universe {
    private List<Planet> planetList = new ArrayList();
    private Sun sun;

    public Universe(){
        sun = new Sun("Sol", 1000000, 1392684, 0, 0, 0, 1, Color.white);
        // Creating the planets here
        planetList.add(new Planet("Mercury", 0.16601, 4878, 69817, 108943, 47.9, (365.25*0.2408), Color.yellow));
        planetList.add(new Planet("Venus", 2.4478383, 12104, 108943, 107476, 35.0, (365.25*0.6152), Color.green));
        planetList.add(new Planet("Earth", 3.00348959632, 12756, 152098, 147098, 29.8, 365.25, Color.blue));
        planetList.add(new Planet("Mars", 0.3227151, 6787, 249232, 206655, 24.1, (365.25*1.8809), Color.red));
        planetList.add(new Planet("Jupiter", 954.79194, 142800, 816002, 740680, 13.1, (365.25*11.862), Color.orange));
        planetList.add(new Planet("Saturn", 285.8860, 120000, 1503509, 1349824, 9.6, (365.25*29.458), Color.yellow));
        planetList.add(new Planet("Uranus", 43.66244, 51200, 3006318, 2734998, 6.8, (365.25*84.01), Color.green));
        planetList.add(new Planet("Neptune", 51.51389, 48600, 4537040, 4459753, 5.4, (365.25*164.79), Color.blue));

    }

    public void DrawEverything(Graphics2D g2d, Screen screen){
        DrawSuns(g2d, screen);
        DrawPlanets(g2d, screen);
    }

    private void DrawPlanets(Graphics2D g2d, Screen screen){
        // Iterator
        Coords<Integer> screenPos;
        Iterator<Planet> pli = planetList.iterator();
        while(pli.hasNext()){
            Planet p = pli.next();
            g2d.setColor(p.color);
            //g2d.setColor(pli.next().color);
            screenPos = screen.CalculateScreenPos(p.location);
            g2d.fillOval(screenPos.x, screenPos.y, 3, 3);
        }
    }

    private void DrawSuns(Graphics2D g2d, Screen screen){
        // Iterator
        Coords<Integer> screenPos;
        g2d.setColor(sun.color);
        screenPos = screen.CalculateScreenPos(sun.location);
        g2d.fillOval(screenPos.x, screenPos.y, 10, 10);

    }

    public void UpdatePlanetPositions(long timeSlice){
        // Use an equation
        // Iterator
        double x;
        double y;
        double t = timeSlice;

        Iterator<Planet> pli = planetList.iterator();
        while(pli.hasNext()){
            Planet p = pli.next();
            p.location.x = p.GetAphelion() * Math.cos(2 * Math.PI * t / p.GetPeriod());
            p.location.y = p.GetPerihelion() * Math.sin(2 * Math.PI * t / p.GetPeriod());
            //System.out.printf("%s (%f, %f)", p.name, p.location.x, p.location.y);
            //System.out.printf("Aphelion: %f, t: %f, period: %f, cos: %f", p.GetAphelion(), t, p.GetPeriod(), Math.cos(2 * Math.PI * t / p.GetPeriod()));
        }
    }
}
