package assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import assignment6.AlgorithmTimer.TimeComplexity;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 *	Our experiment for the remove method, testing both Javas TreeSet and our BST, using balanced trees and unbalanced trees created by sorted lists.
 */
public class BalanceRemoveExperiment {



	public class JavaRemove extends Process{

		List<Integer> data = new ArrayList<Integer>();
		TreeSet<Integer> bst = new TreeSet<Integer>();


		@Override
		public void run(int n, TimeComplexity complexity) {
			bst.removeAll(data);

		}

		public void generateData(int n, TimeComplexity complexity)
		{
			//Here we reset the binary search tree so that each iteration has the same initial state.
			bst = new TreeSet<Integer>();
			if(complexity == TimeComplexity.BEST){
				data = Process.Helper.permutedInts(n);
				Process.Helper.setRandom(new Random(420666));
			}
			else
				data = Process.Helper.ascendingInts(n);
			
			bst.addAll(data);
		}
	}

	
	public class BSTRemove extends Process{
		List<Integer> data = new ArrayList<Integer>();
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		@Override
		public void run(int n, TimeComplexity complexity) {
			bst.removeAll(data);

		}

		public void generateData(int n, TimeComplexity complexity)
		{
			//Here we reset the binary search tree so that each iteration has the same initial state.
			bst = new BinarySearchTree<Integer>();
			if(complexity == TimeComplexity.BEST){
				data = Process.Helper.permutedInts(n);
				Process.Helper.setRandom(new Random(420666));
			}
			else
				data = Process.Helper.ascendingInts(n);
			
			bst.addAll(data);
		}
		
	}





}
