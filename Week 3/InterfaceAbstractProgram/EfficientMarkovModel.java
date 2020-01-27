
/**
 * EfficientMarkovModel class is used to create randomly generated text based on original text
 * and specified n-amount of characters used to predict the following character. This class 
 * is using hashmap to collect keys and corresponding following characters that later are
 * used to create a randomly generated text with getRandomText() method.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 16, 2020)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int charsToPredict;
    private HashMap<String,ArrayList<String>> hashMap;
    
    public EfficientMarkovModel(int numChars) {
        charsToPredict = numChars;
        hashMap = new HashMap<String,ArrayList<String>> ();
    }
    
    @Override
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }
    
    public String toString() {
        return "EfficientMarkovModel class of order " + charsToPredict;
    }
    
    public void buildMap () {
        for(int k=0; k < myText.length()-charsToPredict; k++){
            String key = myText.substring(k, k+charsToPredict);
           
            if (! hashMap.containsKey(key)) {
                ArrayList<String> nextArr = new ArrayList<String> ();
                nextArr.add(myText.substring(k+charsToPredict, k+charsToPredict+1));
                
                hashMap.put(key, nextArr);
            } else if (hashMap.containsKey(key)) {
                ArrayList<String> nextArr = hashMap.get(key);
                nextArr.add(myText.substring(k+charsToPredict, k+charsToPredict+1));
            }
        }
    }
    
    public void printHashMapInfo () {
        int maxSize = 0;
        ArrayList<String> maxSizeKeys = new ArrayList();
        
        for (String key : hashMap.keySet()) {
            ArrayList<String> values = hashMap.get(key);
            //System.out.println("For key: " + key + " corresponding values are: " + values);
            
            if (values.size() > maxSize) {
                maxSize = values.size();
            }                   
        }
        
        for (String key : hashMap.keySet()) {
            int valueSize = hashMap.get(key).size();
            
            if (valueSize == maxSize) {
                maxSizeKeys.add(key);
            }
        }
        
        System.out.println("Number of keys in the HashMap: " + hashMap.size());
        
        System.out.println("Size of the largest value in the HashMap: " + maxSize);
        System.out.println("Keys that have the maximum size value: " + maxSizeKeys);
    }
    
    @Override
    public ArrayList<String> getFollows (String key) {
        return hashMap.get(key);
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
            
            if (follows == null || follows.size() == 0) {
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
