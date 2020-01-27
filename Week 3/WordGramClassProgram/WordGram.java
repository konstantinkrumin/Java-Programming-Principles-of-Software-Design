
/**
 * The class WordGram is a class that represents words that have an ordering. It contains
 * multiple methods that are used in the Markov chain text generator. In this case the model
 * uses n-amount of words to predict the following word and to produce a randomly generated text.
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 22, 2020)
 */

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        if (myWords.length == 0) {
            return 0;
        }
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k=0; k < myWords.length; k++) {
            String string = myWords[k] + " ";
            ret += string;
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k < myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        String[] myWordsCopy = new String [this.length()];
        for (int k=0; k < myWordsCopy.length-1; k++) {
            myWordsCopy[k] = this.myWords[k+1];
        }
        myWordsCopy[myWordsCopy.length-1] = word;
        
        WordGram out = new WordGram(myWordsCopy, 0, myWordsCopy.length);
        return out;
    }
    
    public int hashCode () {
        return this.toString().hashCode();
    }
}