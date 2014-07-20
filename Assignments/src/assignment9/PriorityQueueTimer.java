package assignment9;

import assignment9.AlgorithmTimer.TimeComplexity;

public class PriorityQueueTimer {

	public static void main(String[] args){

		TimeComplexity[] complexities = new TimeComplexity[] {TimeComplexity.AVERAGE};
		int averageTime = 100;

//		new AlgorithmTimer("heapAdd", 
//				new BinaryHeapAddExperiment(),
//				averageTime).generateAnalysis(1, 100000, complexities);
		
		new AlgorithmTimer("heapRemove",
				new BinaryHeapRemoveExperiment(), averageTime).generateAnalysis(1, 100000,complexities);
		
		new AlgorithmTimer("heapCompareAdd",
				new BinaryHeapAndBSTExperiment("heap"), averageTime).generateAnalysis(1, 10000, complexities);
		
		new AlgorithmTimer("BSTCompareAdd",
				new BinaryHeapAndBSTExperiment("bst"), averageTime).generateAnalysis(1, 10000, complexities);
		


	}

}
