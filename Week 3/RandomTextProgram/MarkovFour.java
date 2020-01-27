
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

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows (String key) {
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
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        
        for(int k=0; k < numChars-4; k++){
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
