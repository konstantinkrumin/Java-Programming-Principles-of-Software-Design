
/**
 * MarkovRunner is class that is used to run models based on markovWord and EfficientMarkovWord
 * classes. Using this class we can select an original text for our random text generation,
 * size of the text generated as well as we can set the seed value for the text generation for 
 * testing purposes. We can also set up how many words we want to use for the following word
 * random generation. Using more words will allow us to create a random text that will be more
 * cohesive, less random and more accurate representation of the original text.
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 22, 2020)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        
        MarkovWord markovWord = new MarkovWord(5);
        runModel(markovWord, st, 120, 844);
        
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        runModel(efficientMarkovWord, st, 120, 65);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
}
