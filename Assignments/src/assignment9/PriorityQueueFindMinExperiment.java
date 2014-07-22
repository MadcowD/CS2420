package assignment9;

import assignment9.AlgorithmTimer.TimeComplexity;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * FindMin timing experiment for Heap based PQ. 
 *
 */
public class PriorityQueueFindMinExperiment extends Process {
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
		pq.findMin();
		return 0;
	}

}
