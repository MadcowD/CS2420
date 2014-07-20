package assignment9;

import assignment9.AlgorithmTimer.TimeComplexity;

public class PriorityQueueTimer {

	public static void main(String[] args){

		TimeComplexity[] complexities = new TimeComplexity[] {TimeComplexity.AVERAGE};
		int averageTime = 10000;

		
//		new AlgorithmTimer("pqAdd",
//				new PriorityQueueAddExperiment(), averageTime).generateAnalysis(1, 10000,new TimeComplexity[] {TimeComplexity.WORST, TimeComplexity.BEST});
//		
		new AlgorithmTimer("pqFindMin",
			new PriorityQueueFindMinExperiment(), averageTime).generateAnalysis(1, 10000, complexities);
		
//		new AlgorithmTimer("pqDeleteMin",
//			new PriorityQueueDeleteMinExperiment(), averageTime).generateAnalysis(1, 10000, complexities);
//		
//		new AlgorithmTimer("heapCompareAdd",
//				new BinaryHeapAndBSTExperiment("heap"), averageTime).generateAnalysis(1, 10000);
//		
//		new AlgorithmTimer("BSTCompareAdd",
//				new BinaryHeapAndBSTExperiment("bst"), averageTime).generateAnalysis(1, 10000,  new TimeComplexity[] {TimeComplexity.AVERAGE, TimeComplexity.BEST});
		


	}

}
