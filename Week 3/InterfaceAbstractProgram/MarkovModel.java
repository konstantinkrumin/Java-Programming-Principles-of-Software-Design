
/**
 * MarkovModel is used to generate random text that is numChars long. This text is generated
 * randomly by predicting the next character based on n characters that follow somewhere in
 * the training text. An integer should be passed in with the constructor to specify the number
 * of characters to use to predict the next character.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 16, 2020)
 */

import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int charsToPredict;
    
    public MarkovModel(int numChars) {
        charsToPredict = numChars;
    }
    
    public String toString() {
        return "MarkovModel of order " + charsToPredict;
    }
    
    public String getRandomText(int setSize){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-charsToPredict);
        String key = myText.substring(index, index+charsToPredict);
        sb.append(key);
        
        for(int k=0; k < setSize-charsToPredict; k++){
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
