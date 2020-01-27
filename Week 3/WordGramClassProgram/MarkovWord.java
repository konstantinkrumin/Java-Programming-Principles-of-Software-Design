
/**
 * MarkovWord class is used to create randomly generated text based on the original text
 * and a specified n-amount of words used to predict the following word. It uses string arrays
 * and getFollows() method to collect the words that follow a specified key and then to create
 * a randomly generated text with getRandomText() method.
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (January 22, 2020)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord (int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom (int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining (String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf (String[] words, WordGram target, int start) {
        for (int k=start; k < words.length - myOrder; k++) {
            WordGram wg = new WordGram(words, k, myOrder);
            if (wg.equals(target)) {
                return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows (WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, kGram, pos);
            if (start == -1) {
                break;
            }
            if (start >= myText.length - 1) {
                break;
            }
            String next = myText[start + myOrder];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
}
