
/**
 * EfficientMarkovWord class is used to create randomly generated text based on original text
 * and specified n-amount of words used to predict the following word. This class is using
 * hashmap to collect keys and corresponding following words that later are used to create 
 * a randomly generated text with getRandomText() method.
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (January 23, 2020)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> hashMap;
    
    public EfficientMarkovWord (int order) {
        myRandom = new Random();
        myOrder = order;
        hashMap = new HashMap<WordGram,ArrayList<String>> ();
    }
    
    public void setRandom (int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining (String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap () {
       for(int k=0; k <= myText.length - myOrder; k++){
            WordGram key = new WordGram(myText, k, myOrder);
            String next = "";
            if (k < myText.length - myOrder) {
                next = myText[k+myOrder];
            }
            
            if (! hashMap.containsKey(key)) {
                ArrayList<String> nextArr = new ArrayList<String> ();
                nextArr.add(next);
                hashMap.put(key, nextArr);
            } else {
                ArrayList<String> nextArr = hashMap.get(key);
                nextArr.add(next);
            }
        }
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
        return hashMap.get(kGram);
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = new ArrayList<String>();
            follows = getFollows(key);
            if (follows == null || follows.isEmpty() || follows.size() == 0) {
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
    
    public void printHashMapInfo () {
        int maxSize = -1;
        ArrayList<WordGram> maxSizeKeys = new ArrayList();
        
        for (WordGram key : hashMap.keySet()) {
            ArrayList<String> values = hashMap.get(key);
            //System.out.println("For key: \"" + key + "\" corresponding values are: " + values);
            
            if (values.size() > maxSize) {
                maxSize = values.size();
            }                   
        }
        
        for (WordGram key : hashMap.keySet()) {
            int valueSize = hashMap.get(key).size();
            
            if (valueSize == maxSize) {
                maxSizeKeys.add(key);
            }
        }
        System.out.println("Number of keys in the HashMap: " + hashMap.size());
        System.out.println("Size of the largest value in the HashMap: " + maxSize);
        System.out.println("Keys that have the maximum size value: " + maxSizeKeys);
    }
}
