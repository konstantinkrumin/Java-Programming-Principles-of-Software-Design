
/**
 * This class implements comparator interface and sorts the qearthquake data based on its
 * magnitude from low to high. 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 14, 2020)
 */

import java.util.*;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }
}
