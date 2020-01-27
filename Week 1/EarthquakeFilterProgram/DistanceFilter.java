
/**
 * Returns quake entries that have distance to the location under maximal distance.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 11, 2020)
 */

public class DistanceFilter implements Filter {
    private Location location;
    private float distMax;
    private String name;
    
    public DistanceFilter (Location loc, float max, String filterName) {
        location = loc;
        distMax = max;
        name = filterName;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) / 1000.0 < distMax;
    }
    
    public String getName() {
       return name;
    }
}
