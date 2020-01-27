
/**
 * IMarkovModel is an interface that is implemented in both MarkovWord and EfficientMarkovWord
 * classes.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 22, 2020)
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);

}
