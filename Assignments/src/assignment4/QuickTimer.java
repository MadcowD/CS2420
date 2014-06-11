package assignment4;

import java.util.ArrayList;

import assignment4.AlgorithmTimer.TimeComplexity;

public class QuickTimer implements AlgorithmTimer.Process {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuickTimer timer = new QuickTimer();
		int n = 3;
		TimeComplexity complexity = TimeComplexity.AVERAGE;
		ArrayList<Integer> myData = RecursiveSortingUtility.generateCase(n, complexity);
		
		
		
		for(int i = 0; i<5; i++){
			ArrayList<Integer> copy = new ArrayList<Integer>();//copy of mydata so that it's the same list everytime. cuz paymon said so
			long time = 0;//For the averages
			for(int a = 0; a < n; i++){
				copy.add(myData.get(a));
			}
			timer.run(copy);
			
			
			//TODO ILL DO THIS L8R ALLI GAY TOR
			
			
			//TODO DELETE THAT COMMENT ^
			
			//TODO DON'T LOSE POINTS FOR DUMB COMMENTS AGAIN D:<
			
			
			
			
			
			
		}
		
	

	}

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
	 * Runs quick sort.
	 */
	@Override
	public void run(int n) {
		RecursiveSortingUtility.quickSortDriver(data);
	}

	/**
	 * TODO:
	 * THIS IS BASICALLY SO THAT WE HAVE THE SAME LIST EVERYTIME INSTEAD OF NEWLY GENERATED ONES
	 * @param list
	 */
	public <T extends Comparable<? super T>> void run(ArrayList<T> list){
		RecursiveSortingUtility.quickSortDriver(list);
	}

	@Override
	public void overhead(int n) {
		
		
	}
}
