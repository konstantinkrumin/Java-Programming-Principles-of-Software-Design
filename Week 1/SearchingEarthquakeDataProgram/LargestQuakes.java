
/**
 * Finds N-largest quakes
 * 
 * @ Konstantin Krumin 
 * @ Version : January 10, 2020
 */

import java.util.*;

public class LargestQuakes {
    public int indexOfLargest (ArrayList<QuakeEntry> quakeData) {
        double largestMagnitude = 0;
        int indexOfLargest = 0;
        
        for (int i=0; i < quakeData.size(); i++) {
            QuakeEntry quake = quakeData.get(i);
            double magnitude = quake.getMagnitude();
            
            if (magnitude > largestMagnitude) {
                largestMagnitude = magnitude;
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyList = new ArrayList<QuakeEntry>(quakeData);
        
        for (int i=0; i < howMany; i++) {
            int indexOfLargest = indexOfLargest(copyList);
            
            answer.add(copyList.get(indexOfLargest));
            copyList.remove(indexOfLargest);
        }
        return answer;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for " + list.size() + " quakes");
        
        int howMany = 50;
        System.out.println(howMany + " largest quakes: ");
        
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, howMany);
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
    } 
}
