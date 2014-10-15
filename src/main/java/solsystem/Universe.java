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
        sun = new Sun("Sol", 1000000, 1392684, 0, 0, 0, 1, 5778, 5779, Color.white);
        // Creating the planets here
        planetList.add(new Planet("Mercury", 0.16601, 4878, 69817, 108943, 47.9, (365.25*0.2408), 90, 740, Color.yellow));
        planetList.add(new Planet("Venus", 2.4478383, 12104, 108943, 107476, 35.0, (365.25*0.6152), 729, 730, Color.green));
        planetList.add(new Planet("Earth", 3.00348959632, 12756, 152098, 147098, 29.8, 365.25, 185, 331, Color.blue));
        planetList.add(new Planet("Mars", 0.3227151, 6787, 249232, 206655, 24.1, (365.25*1.8809), 186, 268, Color.red));
        planetList.add(new Planet("Jupiter", 954.79194, 142800, 816002, 740680, 13.1, (365.25*11.862), 288, 293, Color.orange));
        planetList.add(new Planet("Saturn", 285.8860, 120000, 1503509, 1349824, 9.6, (365.25*29.458), 95, 330, Color.yellow));
        planetList.add(new Planet("Uranus", 43.66244, 51200, 3006318, 2734998, 6.8, (365.25*84.01), 76, 77, Color.green));
        planetList.add(new Planet("Neptune", 51.51389, 48600, 4537040, 4459753, 5.4, (365.25*164.79), 73, 74, Color.blue));

    }

    public void DrawEverything(Graphics2D g2d, Screen screen){
        DrawSuns(g2d, screen);
        DrawPlanets(g2d, screen);
    }

    private void DrawBody(Graphics2D g2d, Screen screen, Body body){
        Coords<Integer> screenPos;
        double drawSize;
        double minBodySize = 2;
	    double minSunTemp = 1200;
        double noShowSize = 0.001;

        drawSize = 2 * body.GetDiameter() / screen.GetZoom();
        if((body.GetMinTempK() < minSunTemp) && (drawSize < noShowSize))
            return;
        if(drawSize < minBodySize)
            drawSize = minBodySize;
        g2d.setColor(body.color);
        screenPos = screen.CalculateScreenPos(body.location);
        g2d.fillOval(screenPos.x - (int)(drawSize/2.0), screenPos.y - (int)(drawSize/2.0), (int)drawSize, (int)drawSize);

    }

    private void DrawPlanets(Graphics2D g2d, Screen screen){
        // Iterator
        Iterator<Planet> pli = planetList.iterator();

        while(pli.hasNext()){
            Planet p = pli.next();
            DrawBody(g2d, screen, p);
        }
    }

    private void DrawSuns(Graphics2D g2d, Screen screen){
        DrawBody(g2d, screen, sun);
    }

    public void DrawCursorInfo(Graphics2D g2d, Screen screen, Point mousePosition){
        // Draw some text
        // Convert mousePosition to Universe position
	    Coords<Double> mouseUniverseLocation = screen.GetUniverseCoords(mousePosition);
        //Planet nearestPlanet = GetNearestPlanet(mouseUniverseLocation);
        Planet nearestPlanet = GetNearest(mouseUniverseLocation, planetList);
        /*
        g2d.setColor(Color.white);
        String cursorStr = String.format("(%.2f,%.2f)",
                mouseUniverseLocation.x,
                mouseUniverseLocation.y);
        g2d.drawString(cursorStr, mousePosition.x, mousePosition.y); // or GetX()?
        */
        DrawPlanetInfo(g2d, screen, nearestPlanet);
    }

    private void DrawPlanetInfo(Graphics2D g2d, Screen screen, Planet planet){
        String info;
        int x = 10;
        int y = 10;
        int inc = 10;
        Coords<Integer> screenLoc;
        Point screenPoint = new Point();

        g2d.setColor(Color.lightGray);
        g2d.drawString(planet.name, x, y+=inc);
        info = String.format("Location: %.2f,%.2f", planet.location.x, planet.location.y);
        g2d.drawString(info, x, y+=inc);
        //Draw select box
        screenLoc = screen.CalculateScreenPos(planet.location);
        screenPoint.setLocation(screenLoc.x, screenLoc.y);
        DrawSelectSquare(g2d, screenPoint);
    }

    public Planet GetNearest(Coords<Double> universeLocation){
        // Loop through elements
        return GetNearest(universeLocation, planetList);
    }

    private <T extends Body> T GetNearest(Coords<Double> universeLocation, List<T> tList){
        // Loop through elements
        Iterator<T> tli = tList.iterator();
        double smallestDistance = 10000000;
        T b; // = pli.next();
        T body = tList.get(0); // That should get the first planet
        double dist;

        while(tli.hasNext()){
            b = tli.next();
            dist = Distance(universeLocation, b.location);
            //System.out.printf("Distance:%f", dist);
            if(dist < smallestDistance){
                body = b;
                smallestDistance = dist;
            }
        }
        //System.out.printf("Found planet: %s", planet.name);
        return body;
    }

    public double Distance(Coords<Double> a, Coords<Double> b){
        double x = (b.x - a.x) * (b.x - a.x);
        double y = (b.y - a.y) * (b.y - a.y);
        return Math.sqrt(x+y);
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

    public void DrawSelectSquare(Graphics2D g2d, Point screenPosition){
        int halfBoxSize = 10;
        g2d.setColor(Color.darkGray);
        g2d.drawRect(screenPosition.x-halfBoxSize, screenPosition.y-halfBoxSize, 2*halfBoxSize, 2*halfBoxSize);
    }



}
