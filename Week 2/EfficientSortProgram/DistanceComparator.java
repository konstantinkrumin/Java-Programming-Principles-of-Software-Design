
/**
 * This class implements comparator interface and sorts the qearthquake data based on its
 * distance from the indicated location. 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 15, 2020)
 */

import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2. getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
}
 