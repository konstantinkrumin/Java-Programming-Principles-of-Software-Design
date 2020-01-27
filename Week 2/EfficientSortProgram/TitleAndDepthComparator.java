
/**
 * This class implements comparator interface and sorts the qearthquake data based on its
 * title alphabetically and if some items have the same title they are sorted by depth from
 * low to high.
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (January 14, 2020)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        if (q1.getInfo().compareTo(q2.getInfo()) < 0) {
            return -1;
        } else if (q1.getInfo().compareTo(q2.getInfo()) > 0) {
            return 1;
        } else {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
    }
}
