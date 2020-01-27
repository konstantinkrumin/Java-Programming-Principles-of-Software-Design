
/**
 * EarthQuakeClient class contains various filtering methods for the earthquake data.
 * 
 * @ Konstantin Krumin 
 * @ Version : January 10, 2020
 */

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, 
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            Location currentLocation = qe.getLocation();
            double currentDistance = currentLocation.distanceTo(from) / 1000.0;
            if (currentDistance < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, 
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            
            if (depth > maxDepth && depth < minDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, 
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            
            if (where.equals("start") && title.startsWith(phrase)) {
                answer.add(qe);
            } else if (where.equals("end") && title.endsWith(phrase)) {
                answer.add(qe);
            } else if (where.equals("any") && title.contains(phrase)) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
     
        ArrayList<QuakeEntry> list = parser.read(source);
        
        double magnitude = 5.0;
        ArrayList<QuakeEntry> filteredMagList = filterByMagnitude(list, magnitude);
        
        System.out.println("read data for " + list.size() + " quakes");
        for (QuakeEntry qe : filteredMagList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredMagList.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for " + list.size() + " quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city = new Location(38.17, -118.82);

        double distMax = 1000.0;
        ArrayList<QuakeEntry> filteredByDistList = filterByDistanceFrom(list, distMax, city);
        for (QuakeEntry qe : filteredByDistList) {
            double distInKms = qe.getLocation().distanceTo(city) / 1000.0;
            System.out.println(distInKms + " " + qe.getInfo());
        }
        System.out.println("Found " + filteredByDistList.size() + " quakes that match that criteria");
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for " + list.size() + " quakes");
        
        double minDepth = -2000.0;
        double maxDepth = -4000.0;
        ArrayList<QuakeEntry> filteredByDepthList = filterByDepth(list, minDepth, maxDepth);
        
        System.out.println("Find quakes with depth between " + maxDepth + " and " + minDepth);
        for (QuakeEntry qe : filteredByDepthList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredByDepthList.size() + " quakes that match that criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for " + list.size() + " quakes");
        
        //String phrase = "Alaska";
        //String where = "end";
        
        String phrase = "Can";
        String where = "any";
        
        //String phrase = "Quarry Blast";
        //String where = "start";
        
        ArrayList<QuakeEntry> filteredByPhraseList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredByPhraseList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredByPhraseList.size() 
        + " quakes that match " + phrase + " at " + where);
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
}
