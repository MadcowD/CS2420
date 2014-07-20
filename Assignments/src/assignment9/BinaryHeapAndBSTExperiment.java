package assignment9;

import java.util.ArrayList;
import java.util.List;

import assignment9.AlgorithmTimer.TimeComplexity;
import assignment9.Process.Helper;

public class BinaryHeapAndBSTExperiment extends Process{

	private PriorityQueueHEAP<Integer> heap;
	private PriorityQueueBST<Integer> bst;
	boolean isHeap;
	List<Integer> data = new ArrayList<Integer>();
	
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
		if(isHeap)
			heap.clear();
		else
			bst.clear();

			if(complexity.equals(TimeComplexity.AVERAGE) || (!isHeap &&complexity.equals(TimeComplexity.BEST)))
				data = Helper.permutedInts(n);
			else if(complexity.equals(TimeComplexity.WORST))
					data = Helper.descendingInts(n);
			else if(complexity.equals(TimeComplexity.BEST) && isHeap)
				data = Helper.ascendingInts(n);
	}
	
	
	@Override
	public long run(int n, TimeComplexity complexity) {
		if(isHeap)
			for(int i : data)
				heap.add(i);
		else
			for(int i : data)
				bst.add(i);
		return 0;
	}
	
	@Override
	public void overhead (int n, TimeComplexity complexity) {
		if(isHeap);
		else;
		return;
	}
	
}
