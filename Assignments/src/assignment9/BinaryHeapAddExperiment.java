package assignment9;

import java.util.ArrayList;

import assignment9.AlgorithmTimer.TimeComplexity;

public class BinaryHeapAddExperiment extends Process {

	private PriorityQueueHEAP<Integer> data;
	private boolean time;

	private ArrayList<Integer> numbers; 
	
	public BinaryHeapAddExperiment(PriorityQueueHEAP<Integer> d){
		data = d;
	}
	
	public void generateData(int n, TimeComplexity complexity){
		data.clear();
		super.generateData(n, complexity);
		numbers = (ArrayList<Integer>) Helper.permutedInts(n);
	}
	@Override
	public long run(int n, TimeComplexity complexity) {
		for(int i : numbers){
			data.add(i);
		}
		
		return 0;
	}
	
	
}
