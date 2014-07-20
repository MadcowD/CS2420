package assignment9;

import java.util.ArrayList;
import java.util.List;

import assignment9.AlgorithmTimer.TimeComplexity;

public class PriorityQueueAddExperiment extends Process {
	PriorityQueueHEAP<Integer> pq = new PriorityQueueHEAP<Integer>();
	List<Integer> data = new ArrayList<Integer>();
	@Override
	public void generateData (int n, TimeComplexity complexity) {
		pq =  new PriorityQueueHEAP<Integer>();
		

		if(complexity.equals(TimeComplexity.BEST))
			data = Helper.ascendingInts(n);
		else if(complexity.equals(TimeComplexity.WORST))
			data = Helper.descendingInts(n);
		super.generateData(n, complexity);
	}
	
	
	@Override
	public long run (int n, TimeComplexity complexity) {
		for(Integer i : data){
			pq.add(i);
		}
		return 0;
	}

}
