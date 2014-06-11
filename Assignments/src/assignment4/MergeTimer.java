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
public class MergeTimer implements AlgorithmTimer.Process {
	public static void main(String[] args){
		TimeComplexity complexity = TimeComplexity.AVERAGE;
		
		AlgorithmTimer timer = 
				new AlgorithmTimer(new MergeTimer(), 2000, complexity);
		

		int end = 10000;
		int start = 1000;
		int step = 1000;
		
		timer.time(12);
		
		System.out.println(timer.time(1024));
		System.out.println(timer.time(16384));
		
		RecursiveSortingUtility.generateAverageCase(8);

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


