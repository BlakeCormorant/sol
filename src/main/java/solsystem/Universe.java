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
    private ArrayList<Planet> planetList = new ArrayList();
    private ArrayList<Moon> moonList = new ArrayList();
    private Sun sun;
    private Planet planet;

    public Universe(){
        sun = new Sun("Sol", 1000000, 1392684, 0, 1, 5778, 5779, Color.white);
        // Creating the planets here
        planetList.add(new Planet("Mercury", 330104, 2439, 57909, (365.25*0.2408), 90, 740, Color.yellow));
        planetList.add(new Planet("Venus", 4867320, 6051, 108209, (365.25*0.6152), 729, 730, Color.green));
        planetList.add(new Planet("Earth", 5972190, 6371, 149598, 365.25, 185, 331, Color.blue));
        planetList.add(new Planet("Mars", 641693, 3389, 227944, (365.25*1.8809), 186, 268, Color.red));
        planetList.add(new Planet("Jupiter", 1898130000, 69911, 778341, (365.25*11.862), 288, 293, Color.orange));
        planetList.add(new Planet("Saturn", 568319000, 58232, 1426666, (365.25*29.458), 95, 330, Color.yellow));
        planetList.add(new Planet("Uranus", 86810300, 25362, 2870658, (365.25*84.01), 76, 77, Color.green));
        planetList.add(new Planet("Neptune", 102410000, 24622, 4498396, (365.25*164.79), 73, 74, Color.blue));

        //name, mass, radius, distance (1000 km), period
        // Adding moons here
        planet = planetList.get(2); // Earth
        moonList.add(new Moon("Earth's Moon", 0.0123*5972190, 1737.5, 384.4, 27.322, -233+273.15, 123+273.15, Color.lightGray, planet));

        planet = planetList.get(3); // Mars
        moonList.add(new Moon("Phobos", 0.0123*5972190, 11.1, 58.9, 0.32, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Deimos", 0.0123*5972190, 6.2, 23.5, 1.3, -233+273.15, 123+273.15, Color.pink, planet));

        planet = planetList.get(4); // Jupiter
        moonList.add(new Moon("Io", 1, 1821.6, 421.8, 1.7, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Europa", 1, 1560, 417, 3.55, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Ganymede", 1, 2631, 1070, 7.15, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Callisto", 1, 2410, 1170, 16.7, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Amalthea", 1, 83, 112.7, 0.5, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Himalia", 1, 53, 11461, 250.6, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Elara", 1, 43, 11741, 259.6, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Pasiphae", 1, 30, 23624, 743.6, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Sinope", 1, 19, 23939, 758.9, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Lysithea", 1, 18, 11717, 259.2, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Carme", 1, 23, 23404, 734, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Anake", 1, 14, 21276, 629.77, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Leda", 1, 10, 11165, 240.9, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Thebe", 1, 49.3, 221.9, 0.675, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Adrastea", 1, 8.2, 129, 0.298, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Metis", 1, 21.5, 128, 0.295, -233+273.15, 123+273.15, Color.gray, planet));
        moonList.add(new Moon("Callirrhoe", 1, 4.3, 24102, 758.77, -233+273.15, 123+273.15, Color.gray, planet));

    }

    public void DrawEverything(Graphics2D g2d, Screen screen){
        DrawSuns(g2d, screen);
        DrawPlanets(g2d, screen);
        DrawMoons(g2d, screen);
    }

    private void DrawBody(Graphics2D g2d, Screen screen, Body body){
        Coords<Integer> screenPos;
        double drawSize;
        double minBodySize = 2;
	    double minSunTemp = 1200;
        double noShowSize = 0.001;

        drawSize = 2 * body.GetRadius() / screen.GetZoom();
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

    private void DrawMoons(Graphics2D g2d, Screen screen){
        Iterator<Moon> mi = moonList.iterator();

        while(mi.hasNext()){
            Moon m = mi.next();
            DrawBody(g2d, screen, m);
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

    public void UpdatePlanetPositions(double timeSlice){
        // Use an equation
        // Iterator
        double x;
        double y;
        double t = timeSlice;

        Iterator<Planet> pli = planetList.iterator();
        while(pli.hasNext()){
            Planet p = pli.next();
            p.location.x = p.GetDistance() * Math.cos(2 * Math.PI * t / p.GetPeriod());
            p.location.y = p.GetDistance() * Math.sin(2 * Math.PI * t / p.GetPeriod());
            //System.out.printf("%s (%f, %f)", p.name, p.location.x, p.location.y);
            //System.out.printf("Aphelion: %f, t: %f, period: %f, cos: %f", p.GetAphelion(), t, p.GetPeriod(), Math.cos(2 * Math.PI * t / p.GetPeriod()));
        }

        Iterator<Moon> mi = moonList.iterator();
        while(mi.hasNext()){
            Moon m = mi.next();
            m.location.x = m.GetPlanet().location.x + (m.GetDistance() * Math.cos(2 * Math.PI * t / m.GetPeriod()));
            m.location.y = m.GetPlanet().location.y + (m.GetDistance() * Math.sin(2 * Math.PI * t / m.GetPeriod()));
        }
    }

    public void DrawSelectSquare(Graphics2D g2d, Point screenPosition){
        int halfBoxSize = 10;
        g2d.setColor(Color.darkGray);
        g2d.drawRect(screenPosition.x-halfBoxSize, screenPosition.y-halfBoxSize, 2*halfBoxSize, 2*halfBoxSize);
    }



}
