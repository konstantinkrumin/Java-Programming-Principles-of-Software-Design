
/**
 * MarkovRunnerWithInterface is class that is used to run markov models and EfficientMarkovModel
 * classes. Using this class we can select an original text for our random text generation,
 * size of the text generated as well as we can set the seed value for the text generation for 
 * testing purposes. We can also set up how many characters we want to use for the following 
 * characters random generation. Using more characters will allow us to create a random text 
 * that will be more cohesive, less random and more accurate representation of the original 
 * text.
 * 
 * 
 * @ Author: Duke Software
 * @ Version: 1.0
 */
 
import edu.duke.*; 
 
public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        
        System.out.println(markov.toString());
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        int size = 500;
        int seed = 542;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        
        EfficientMarkovModel mFive = new EfficientMarkovModel(5);
        runModel(mFive, st, size, seed);
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
