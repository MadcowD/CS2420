package assignment4;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import assignment4.AlgorithmTimer.TimeComplexity;

/**
 * Optimizes the insertion threshold of merge sort.
 * The class employs the AlgorithmTimer.Process (in its not static form).
 * Essentially optimization occurs by changing the thresholding point according to a binary search method
 * minimizing time. TODO: Employ eulers first derivative method. 
 * @author wguss & cegielski
 *
 */
public class MergeOptimization implements AlgorithmTimer.Process {
	public static void main(String[] args){
		TimeComplexity complexity = TimeComplexity.AVERAGE;
		
		AlgorithmTimer timer = 
				new AlgorithmTimer(new MergeOptimization(), 1000, complexity);
		
		int end = 2000;
		int start = 1;
		int step = 50;
		
		
		//run optimization
		HashMap<Integer, Long> times = new HashMap<Integer,Long>();
		
		//Add initial data.
		RecursiveSortingUtility.setMergeSortThreshold(0);
		times.put(0, timer.time(start, end, step));
		
		RecursiveSortingUtility.setMergeSortThreshold(33);
		times.put(33, timer.time(start, end, step));
		
		RecursiveSortingUtility.setMergeSortThreshold(66);
		times.put(66, timer.time(start, end, step));
		
		RecursiveSortingUtility.setMergeSortThreshold(100);
		times.put(100, timer.time(start, end, step));
		
		//Binary search optimization for even functions 
		SimpleEntry<Integer, Long> lowest = new SimpleEntry<Integer, Long>(-1,(long) 1E20);
		SimpleEntry<Integer, Long> second = new SimpleEntry<Integer, Long>(-1,(long) 1E20);
		
		do{
			for(Integer key : times.keySet()){
				if(times.get(key).compareTo(lowest.getValue()) < 0)
					lowest = new SimpleEntry<Integer, Long>(key, times.get(key));
				else if(times.get(key).compareTo(second.getValue()) < 0)
					second = new SimpleEntry<Integer, Long>(key, times.get(key));
			
				
				//Now calculate the median
				int threshold = (lowest.getKey() + second.getKey())/2;
				
				//if the threshold is higher, adapt and choose a value that is not intuitive (explore secondary tree)
				if(times.containsKey(threshold))
					threshold*=2;
				
				RecursiveSortingUtility.setMergeSortThreshold(threshold);
				long timeNet = timer.time(start,end,step);
				
				System.out.println("\nCurrent lowest: " + lowest.getKey());
				System.out.println("\nCurrent threshold to explored: " + threshold);
			
			}
		}
		//Do while the absolute value (distance) of the difference between lowest and second is more that 5
		while(Math.abs(second.getKey() - lowest.getKey()) > 5);
		
		System.out.println("Calculated lowest threshold:\n" + lowest.getKey());
	}
	
	
	
	
	//----------------------------------------------------------------------
	
	/**
	 * The list which is to be filled with case testing data.
	 */
	private ArrayList<Integer> data = new ArrayList<Integer>();
	
	
	/**
	 * Generates the data for a given n
	 */
	@Override
	public void generateData(int n, TimeComplexity complexity){
		data = RecursiveSortingUtility.generateCase(n, complexity);
	}
	
	/**
	 * Runs merge sort.
	 */
	@Override
	public void run(int n) {
		RecursiveSortingUtility.mergeSortDriver(data);
	}

	@Override
	public void overhead(int n) {
		
		
	}
}


