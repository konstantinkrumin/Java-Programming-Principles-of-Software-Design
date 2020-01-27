
/**
 * MarkovOne is used to generate random text that is numChars long. This text is generated
 * randomly by predicting the next character based on one character that follows somewhere in
 * the training text.
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (January 15, 2020)
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    public String toString() {
        return "MarkovModel of order 1";
    }
    
    public String getRandomText(int setSize){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for(int k=0; k < setSize-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            
            int indexFollows = myRandom.nextInt(follows.size());
            String next = follows.get(indexFollows);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
}
