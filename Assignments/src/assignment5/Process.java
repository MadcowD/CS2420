package assignment5;

import assignment5.AlgorithmTimer.TimeComplexity;

/**
 * The process interface in which the algorithm will be run.
 * @author William Guss
 * @author Maks Cegielski-Johnson
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
	public abstract void run(int n);
	//TODO: HAVE RUN PROCESS TAKE A COMPLEXITY (MORE VERSITILE FOR TIMING DATA STRUCTURES)
	
	/**
	 * Specifies the overhead of running a process so that only the algorithm is timed.
	 * The number of elements to process with the algorithm.
	 */
	public void overhead(int n)
	{ /*DEFAULT: DO NOTHING*/ }

}