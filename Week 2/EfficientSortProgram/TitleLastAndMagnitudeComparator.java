
/**
 * This class implements comparator interface and sorts the qearthquake data based on its
 * title's last word alphabetically and if some items have the same title they are sorted by
 * magnitude from low to high.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 15, 2020)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare (QuakeEntry q1, QuakeEntry q2) {
        String q1String = q1.getInfo().toString();
        String lastWord1 = q1String.substring(q1String.lastIndexOf(" ")+1);
        
        String q2String = q2.getInfo().toString();
        String lastWord2 = q2String.substring(q2String.lastIndexOf(" ")+1);
        
        if (lastWord1.compareTo(lastWord2) < 0) {
            return -1;
        } else if (lastWord1.compareTo(lastWord2) > 0) {
            return 1;
        } else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}
