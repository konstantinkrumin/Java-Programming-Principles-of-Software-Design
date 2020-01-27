
/**
 * Returns quake entries that either start, contain, or end with a specified phrase.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 11, 2020)
 */

public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;
    
    public PhraseFilter (String cWhere, String cPhrase, String filterName) {
        where = cWhere;
        phrase = cPhrase;
        name = filterName;
    }
    
    public boolean satisfies (QuakeEntry qe) {
        if (where == "start") {
            return qe.getInfo().startsWith(phrase);
        } else if (where == "end") {
            return qe.getInfo().endsWith(phrase);
        } else if (where == "any") {
            return qe.getInfo().contains(phrase);
        } else {
            return false;
        }
    }
    
    public String getName() {
       return name;
    }
}
