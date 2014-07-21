package assignment9;

import assignment9.AlgorithmTimer.TimeComplexity;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * Class that runs all the different timing experiments. 
 *
 */
public class PriorityQueueTimer {

	public static void main(String[] args){

		TimeComplexity[] complexities = new TimeComplexity[] {TimeComplexity.AVERAGE};
		int averageTime = 10000;

		//Heap PQ add
		new AlgorithmTimer("pqAdd",
				new PriorityQueueAddExperiment(), averageTime).generateAnalysis(1, 10000,new TimeComplexity[] {TimeComplexity.WORST, TimeComplexity.BEST});
		//Heap PQ FindMin
		new AlgorithmTimer("pqFindMin",
			new PriorityQueueFindMinExperiment(), averageTime).generateAnalysis(1, 10000, complexities);
		//Heap PQ DeleteMin
		new AlgorithmTimer("pqDeleteMin",
			new PriorityQueueDeleteMinExperiment(), averageTime).generateAnalysis(1, 10000, complexities);
		//Heap Add for Comparison
		new AlgorithmTimer("heapCompareAdd",
				new BinaryHeapAndBSTExperiment("heap"), averageTime).generateAnalysis(1, 10000);
		//BST Add for Comparison
		new AlgorithmTimer("BSTCompareAdd",
				new BinaryHeapAndBSTExperiment("bst"), averageTime).generateAnalysis(1, 10000,  new TimeComplexity[] {TimeComplexity.AVERAGE, TimeComplexity.BEST});
		


	}

}
