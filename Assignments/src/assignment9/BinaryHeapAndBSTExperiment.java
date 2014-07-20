package assignment9;

import java.util.ArrayList;

import assignment9.AlgorithmTimer.TimeComplexity;
import assignment9.Process.Helper;

public class BinaryHeapAndBSTExperiment extends Process{

	private PriorityQueueHEAP<Integer> heap;
	private PriorityQueueBST<Integer> bst;
	boolean isHeap;
	ArrayList<Integer> numbers;
	
	public BinaryHeapAndBSTExperiment(String data){
		if(data.equals("heap")){
			heap = new PriorityQueueHEAP<Integer>();
			isHeap = true;
		}
		else{
			bst = new PriorityQueueBST<Integer>();
			isHeap = false;
		}			
	}
	
	public void generateData(int n, TimeComplexity complexity){
		super.generateData(n, complexity);
		numbers = (ArrayList<Integer>) Helper.permutedInts(n);
	}
	
	
	@Override
	public long run(int n, TimeComplexity complexity) {
		if(isHeap)
			for(int i : numbers)
				heap.add(i);
		else
			for(int i : numbers)
				bst.add(i);
		return 0;
	}

	
}
