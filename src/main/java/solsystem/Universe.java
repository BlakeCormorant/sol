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

/**
 * Universe contains everything 08/10/14.
 * @author Blake Cormorant
 */
public class Universe {
    private List<Planet> planetList = new ArrayList();
    private Sun sun;

    public Universe(){
        sun = new Sun("Sol", 1000000, 1392684, 0, 0, 0, Color.white);
        // Creating the planets here
        planetList.add(new Planet("Mercury", 0.16601, 4878, 69817, 108943, 47.9, Color.yellow));
        planetList.add(new Planet("Venus", 2.4478383, 12104, 108943, 107476, 35.0, Color.green));
        planetList.add(new Planet("Earth", 3.00348959632, 12756, 152098, 147098, 29.8, Color.blue));
        planetList.add(new Planet("Mars", 0.3227151, 6787, 249232, 206655, 24.1, Color.red));
        planetList.add(new Planet("Jupiter", 954.79194, 142800, 816002, 740680, 13.1, Color.orange));
        planetList.add(new Planet("Saturn", 285.8860, 120000, 1503509, 1349824, 9.6, Color.yellow));
        planetList.add(new Planet("Uranus", 43.66244, 51200, 3006318, 2734998, 6.8, Color.green));
        planetList.add(new Planet("Neptune", 51.51389, 48600, 4537040, 4459753, 5.4, Color.blue));

    }

    public void DrawPlanets(Graphics2D g2d, Screen screen){
        // Iterator
        Coords<Integer> screenPos;
        Iterator<Planet> pli = planetList.iterator();
        while(pli.hasNext()){
            Planet p = pli.next();
            g2d.setColor(p.color);
            //g2d.setColor(pli.next().color);
            screenPos = screen.CalculateScreenPos(p.GetLocation());
            g2d.fillOval(screenPos.x, screenPos.y, 3, 3);
        }
    }

    public void UpdatePlanetPositions(){

    }
}
