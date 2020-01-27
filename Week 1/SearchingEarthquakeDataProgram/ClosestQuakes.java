
/**
 * Find N-closest quakes
 * 
 * @ Konstantin Krumin 
 * @ Version : January 10, 2020
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, 
    int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyList = new ArrayList<QuakeEntry>(quakeData);
        
        for (int k=0; k < howMany; k++) {
            int minIndex = 0;
            
            for (int j=1; j < copyList.size(); j++) {
                QuakeEntry quake = copyList.get(j);
                Location location = quake.getLocation();
                
                if (location.distanceTo(current) < copyList.get(minIndex).getLocation().distanceTo(current)) {
                    minIndex = j;
                }
            }
            
            answer.add(copyList.get(minIndex));
            copyList.remove(copyList.get(minIndex));
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());

        Location jakarta = new Location(-6.211,106.845);
        int howMany = 3;

        ArrayList<QuakeEntry> close = getClosest(list, jakarta, howMany);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000, entry);
        }
        System.out.println("number found: " + close.size());
    }
}
