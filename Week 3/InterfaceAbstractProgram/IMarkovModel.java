
/**
 * IMarkovModel is an interface that is implemented in the AbstractMarkovModel class.
 * 
 * 
 * @ Konstantin Krumin 
 * @ Version: 1.0 (January 16, 2020)
 */

public interface IMarkovModel {
    public void setRandom(int seed);
    
    public void setTraining(String text);
    
    public String getRandomText(int setSize);
}
