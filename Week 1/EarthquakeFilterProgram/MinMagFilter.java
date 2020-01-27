
/**
 * Returns quake entries that have magnitude higher than the minimal magnitude.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 11, 2020)
 */

public class MinMagFilter implements Filter {
    private double magMin;
    private String name;
    
    public MinMagFilter(double min, String filterName) { 
        magMin = min;
        name = filterName;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin;
    } 
    
    public String getName() {
        return name;
    }
}
