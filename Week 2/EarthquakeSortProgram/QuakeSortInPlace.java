
/**
 * Various sorting methods are implemented in this class such as selection sort by magnitude
 * (low to high), selection sort by largest depth (high to low), bubble sort by magnitude (low to high),
 * bubble sort by magnitude with check (low to high; stops the loop when list is sorted),
 * selection sort by magnitude with check (low to high; stops the loop when list is sorted)
 * 
 * 
 * @ Konstantin Krumin
 * @ Version: 1.0 (January 13, 2020)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        for (int i=0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }        
    }
    
    public int getLargestDepth (ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        
        for (int i=from+1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth (ArrayList<QuakeEntry> in) {
        for (int i=0; i < in.size(); i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    public void onePassBubbleSort (ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i=1; i < quakeData.size()-numSorted; i++) {
            QuakeEntry firstElem = quakeData.get(i-1);
            QuakeEntry secondElem = quakeData.get(i);
        
            if (secondElem.getMagnitude() < firstElem.getMagnitude()) {
                quakeData.set(i-1, secondElem);
                quakeData.set(i, firstElem);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort (ArrayList<QuakeEntry> in) {
        int numSorted = 0;
        while (numSorted < in.size()) {
            onePassBubbleSort(in, numSorted);
            numSorted += 1;
        }
    }
    
    public boolean checkInSortedOrder (ArrayList<QuakeEntry> quakes) {
        boolean answer = true;
        
        for (int i=1; i < quakes.size(); i++) {
            QuakeEntry firstElem = quakes.get(i-1);
            QuakeEntry secondElem = quakes.get(i);
            
            if (firstElem.getMagnitude() > secondElem.getMagnitude()) {
                answer = false;
                break;
            }
        }
        return answer;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck (ArrayList<QuakeEntry> in) {
        int numSorted = 0;
        while (numSorted < in.size()) {
            onePassBubbleSort(in, numSorted);
            boolean checkIfSorted = checkInSortedOrder(in);
            
            numSorted += 1;
            
            if (checkIfSorted == true) {
                break;
            }
        }
        System.out.println("Numbers of passes needed to sort the elements: " + numSorted);
    }
    
    public void sortByMagnitudeWithCheck (ArrayList<QuakeEntry> in) {
        int numSorted = 0;
        for (int i=0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);   
            
            boolean checkIfSorted = checkInSortedOrder(in);
            numSorted += 1;
            
            if (checkIfSorted == true) {
                break;
            }
        }
        System.out.println("numbers of passes needed to sort the elements: " + numSorted);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);  
       
        System.out.println("read data for " + list.size() + " quakes");    
        
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
    }
}
