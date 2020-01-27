
/**
 * Returns quake entries that are between indicated minimum and maximum depth.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 11, 2020)
 */

public class DepthFilter implements Filter {
    private double depthMin;
    private double depthMax;
    private String name;
    
    public DepthFilter (double min, double max, String filterName) {
        depthMin = min;
        depthMax = max;
        name = filterName;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        return qe.getDepth() >= depthMin && qe.getDepth() <= depthMax;
    }
    
    public String getName() {
        return name;
    }
}
