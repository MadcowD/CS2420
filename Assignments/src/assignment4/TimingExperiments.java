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
public class TimingExperiments{
	public static void main(String[] args){
		// merge
		AlgorithmTimer mergeTimer = 
				new AlgorithmTimer("MERGETIMER", new AlgorithmTimer.Process(){
					private ArrayList<Integer> data = new ArrayList<Integer>();

					public void generateData(int n, TimeComplexity complexity){
						data = RecursiveSortingUtility.generateCase(n, complexity);
					}

					public void run(int n) { RecursiveSortingUtility.mergeSortDriver(data); }

					public void overhead(int n) {}
				}, 2000);
		
		//quick
		AlgorithmTimer quickTimer =
				new AlgorithmTimer("QUICKTIMER", new AlgorithmTimer.Process() {
					private ArrayList<Integer> data = new ArrayList<Integer>();

					public void generateData(int n, TimeComplexity complexity){
						data = RecursiveSortingUtility.generateCase(n, complexity);
					}

					public void run(int n) { RecursiveSortingUtility.quickSortDriver(data); }
					
					public void overhead(int n) {}
				}, 1000); //higher averaging given faster base times
		
				//PERFORM EXPERIMENTS
		
	
		RecursiveSortingUtility.setQuickSortThreshold(30);
		
		System.out.println("BEST PIVOT");
		quickTimer.test(1,20001);
		
	}
}


