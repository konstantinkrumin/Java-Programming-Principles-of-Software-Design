
/**
 * AbstractMarkovModel is an abstract class that is used in all related markov models.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 16, 2020)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows (String key) {
        ArrayList<String> answer = new ArrayList<String> ();
        int keyLength = key.length();
        
        for (int i=0; i < myText.length()-keyLength; i++) {
            if (myText.substring(i, i+keyLength).equals(key)) {
                String next = myText.substring(i+keyLength, i+keyLength+1);
                answer.add(next);
            }
        }
        
        return answer;
    }
 
    abstract public String getRandomText(int setSize);
}
