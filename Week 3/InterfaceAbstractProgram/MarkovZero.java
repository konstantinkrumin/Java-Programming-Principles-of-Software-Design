
/**
 * MarkovZero class is used to generate random text that is numChars long and it generates each
 * letter by randomly choosing a letter from the training text.
 * 
 * @ Author: Duke Software
 * @ Version: 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {
    public String toString() {
        return "MarkovModel of order 0";
    }
    
    public String getRandomText(int setSize){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < setSize; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        
        return sb.toString();
    }
}
