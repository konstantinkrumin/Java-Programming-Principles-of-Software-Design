
/**
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 10 ,2020) 
 */

public interface Filter {
    
    public boolean satisfies(QuakeEntry qe);
    
    public String getName();
}
