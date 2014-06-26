package assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import assignment6.AlgorithmTimer.TimeComplexity;

/**
 * A timing experiment construcitng a binary search tree using varying datatypes.
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public class BuildExperiment extends Process {
	
	/**
	 * Generates data for the building experiment.
	 * In the best case, the data is randomly assembled (permuted) so that adding new elements
	 * only takes O(h) where h is the height of the tree == O(log n).
	 * In the worst cases (the default case is assumed here), the data is assembled in either
	 * ascending or descending order (we have chosen ascending) such that adding an element will be
	 * O(n)
	 */
	@Override
	public void generateData(int n, TimeComplexity complexity)
	{
		//Here we reset the binary search tree so that each iteration has the same initial state.
		bst = new BinarySearchTree<Integer>();
		if(complexity == TimeComplexity.BEST)
			data = Process.Helper.permutedInts(n);
		else
			data = Process.Helper.ascendingInts(n);
	}
	
	/**
	 * This runs the experiment by adding all of the data to the binary search tree.
	 */
	@Override
	public void run(int n, TimeComplexity complexity) {
			bst.addAll(data);
	}
	
	List<Integer> data = new ArrayList<Integer>();
	BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

}
