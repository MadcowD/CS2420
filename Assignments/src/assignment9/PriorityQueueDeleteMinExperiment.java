package assignment9;

import assignment9.AlgorithmTimer.TimeComplexity;

public class PriorityQueueDeleteMinExperiment extends Process {
	PriorityQueueHEAP<Integer> pq = new PriorityQueueHEAP<Integer>();
	
	@Override
	public void generateData (int n, TimeComplexity complexity) {
		pq.clear();
		for(Integer i : Helper.ascendingInts(n))
			pq.add(i);
		
		super.generateData(n, complexity);
	}
	
	
	@Override
	public long run (int n, TimeComplexity complexity) {
		pq.deleteMin();
		return 0;
	}

}
