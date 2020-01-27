
/**
 * MarkovFour is used to generate random text that is numChars long. This text is generated
 * randomly by predicting the next character based on four characters that follow somewhere in
 * the training text.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 16, 2020)
 */

import java.util.*;

public class MarkovFour extends AbstractMarkovModel {
    public String toString() {
        return "MarkovModel of order 4";
    }
    
    public String getRandomText(int setSize){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        
        for(int k=0; k < setSize-4; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            
            int indexFollows = myRandom.nextInt(follows.size());
            String next = follows.get(indexFollows);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
}
