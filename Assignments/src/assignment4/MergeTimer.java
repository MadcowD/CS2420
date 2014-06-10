package assignment4;

import java.util.ArrayList;

import assignment4.AlgorithmTimer.TimeComplexity;

public class MergeTimer implements AlgorithmTimer.Process{

	
	public static void main(String[] args){
		
		TimeComplexity complexity = TimeComplexity.AVERAGE;
		
		AlgorithmTimer timer = new AlgorithmTimer(new MergeTimer(), 700, complexity);
		
		
		for(int i = 20; i <= 80; i+= 1){
			RecursiveSortingUtility.setQuickSortThreshold(i);
//			System.out.println(i);
			System.out.println(timer.time(1000,10000,1000));
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	private ArrayList<Integer> data = new ArrayList<Integer>();
	@Override
	public void generateData(int n, TimeComplexity complexity) {
		data = RecursiveSortingUtility.generateCase(n, complexity);
	}

	@Override
	public void run(int n) {
		RecursiveSortingUtility.quickSortDriver(data);
	}

	@Override
	public void overhead(int n) {
		// TODO Auto-generated method stub
		
	}

}
