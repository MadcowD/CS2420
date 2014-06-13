package assignment4;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import assignment4.AlgorithmTimer.TimeComplexity;

/**
 * Optimizes the insertion threshold of merge sort.
 * The class employs the AlgorithmTimer.Process (in its not static form).
 * Collects all of the data needed for analysis
 * @author William Guss
 * @author Maks Cegielski-Johnson
 *
 */
public class RecursiveSortingTimer{
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
				}, 100);
		
		//quick
		AlgorithmTimer quickTimer =
				new AlgorithmTimer("QUICKTIMER", new AlgorithmTimer.Process() {
					private ArrayList<Integer> data = new ArrayList<Integer>();

					public void generateData(int n, TimeComplexity complexity){
						data = RecursiveSortingUtility.generateCase(n, complexity);
					}

					public void run(int n) { RecursiveSortingUtility.quickSortDriver(data); }
					
					public void overhead(int n) {}
				}, 100); //higher averaging given faster base times
		
				//PERFORM EXPERIMENTS
		
		//Quick sort threshold
		
		
		
		
//		System.out.println("MERGE BEST THRESHOLD\n");
//		for(int i = 20; i <= 40; i+=1 ){
//			System.out.println("THRESHOLD " + i);
//			RecursiveSortingUtility.setMergeSortThreshold(i);
//			mergeTimer.test(1, 20001);
//		}
		
//		System.out.println("QUICK BEST THRESHOLD\n");
//		for(int i = 10; i <= 30; i+=1 ){
//			System.out.println("THRESHOLD " + i);
//			RecursiveSortingUtility.setQuickSortThreshold(i);
//			quickTimer.test(1, 20001);
//		}
		
		
		
//		//Pivots
//		
//		System.out.println("best PIVOT");
//		RecursiveSortingUtility.choose = 0;
//		quickTimer.test(1,20001);
//		
		//Comparison
		quickTimer.test(1, 20001);
		mergeTimer.test(1, 20001);
		
	}
}


