package assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import assignment6.AlgorithmTimer.TimeComplexity;

/**
 * A timing experiment construcitng a binary search tree.
 * @author William Guss
 *
 */
public class BuildExperiment extends Process {
	@Override
	public void generateData(int n, TimeComplexity complexity)
	{
		if(complexity == TimeComplexity.BEST)
			data = Process.Helper.permutedInts(n);
		else
			data = Process.Helper.ascendingInts(n);
	}
	
	@Override
	public void run(int n, TimeComplexity complexity) {
		
		
	}
	
	List<Integer> data = new ArrayList<Integer>();

}
