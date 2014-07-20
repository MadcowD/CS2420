package assignment9;

import java.util.ArrayList;

import assignment9.AlgorithmTimer.TimeComplexity;

public class BinaryHeapRemoveExperiment extends Process {

	private PriorityQueueHEAP<Integer> data;
	
	public BinaryHeapRemoveExperiment(){
		data = new PriorityQueueHEAP<Integer>();
	}
	
	public void generateData(int n, TimeComplexity complexity){
		data.clear();
		ArrayList<Integer> numbers = (ArrayList<Integer>) Helper.permutedInts(n);
		for(int i : numbers)
			data.add(i);
		super.generateData(n, complexity);
		
	}
	
	
	@Override
	public long run(int n, TimeComplexity complexity) {
		for(int i = 0; i < n; i++)
			data.deleteMin();
		
		return 0;
	}

}
