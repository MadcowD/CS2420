package assignment7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import assignment7.AlgorithmTimer.TimeComplexity;

/**
 * The process interface in which the algorithm will be run.
 * @author Jesus Hernandez
 * @author William Guss
 *
 */
public abstract class Process{
	/**
	 * 
	 * @param The number of elements to process with the algorithm.
	 * @param The complexity of the data to be generated
	 */
	public void generateData(int n, TimeComplexity complexity)
	{ /*DEFAULT: DO NOTHING*/ }


	/**
	 * Runs a process at a given N.
	 * @param n The number of elements to process with the algorithm.
	 */
	public abstract void run(int n, TimeComplexity complexity);
	//TODO: HAVE RUN PROCESS TAKE A COMPLEXITY (MORE VERSITILE FOR TIMING DATA STRUCTURES)
	
	/**
	 * Specifies the overhead of running a process so that only the algorithm is timed.
	 * The number of elements to process with the algorithm.
	 */
	public void overhead(int n, TimeComplexity complexity)
	{ /*DEFAULT: DO NOTHING*/ }
	
	
	
	/**
	 * A helper class constructed for future process and data generation.
	 * @author William Guss
	 * @author Maks Cegielski-Johnson
	 */
	public static class Helper{
		private static Random rng = new Random();
		
		/**
		 * Swaps elements by position within a generic list.
		 * @param list The list in which the transformation will occurr.
		 * @param index1 The first index.
		 * @param index2 The second index.
		 */
		public static <T> void listSwap(List<T> list, int index1, int index2){
			T temp = list.get(index1);
			list.set(index1, list.get(index2));
			list.set(index2, temp);
		}
		
		
		//------------------------------------------------
		// INTEGER LISTS
		//------------------------------------------------
		
		
		/**
		 * Generates ascending integers for computation.
		 * @param n The order (cardinality) of the integer set to be returned.
		 * @return The integer set filled with n ascending ints.
		 */
		public static List<Integer> ascendingInts(int n){
			ArrayList<Integer> temp = new ArrayList<Integer>(n);
			
			for(int i = 0; i < n; i++)
				temp.add(i);
			
			return temp;
		}
		
		/**
		 * Generates descending integers for computation.
		 * @param n The order (cardinality) of the integer set to be returned.
		 * @return The integer set filled with n descending ints.
		 */
		public static List<Integer> descendingInts(int n){
			ArrayList<Integer> temp = new ArrayList<Integer>(n);
			
			for(int i = n; i > 0; i++)
				temp.add(i);
			
			return temp;
		}
		
		public static List<Integer> permutedInts(int n)
		{
			List<Integer> temp = ascendingInts(n);
			
			for(int i = 0; i < n; i++)
				listSwap(temp, i, rng.nextInt(n));
			
			return temp;
		}
		

	}

}