package assignment4;

import assignment4.AlgorithmTimer.TimeComplexity;

/**
 * A timing framework built for CS2420, because we are lazy. 
 * Essentially takesa functor (AlgorithmTimer.Process) and tests it using different
 * methods and complexities. Streamlines data export.
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public final class AlgorithmTimer {
	public final static double PORTION_LOW	= 0.01;
	public final static double STEP_LOW		= PORTION_LOW/80.0;
	
	public final static double PORTION_MID	= 0.1;
	public final static double STEP_MID		= PORTION_MID/40.0;
	
	public final static double PORTION_HIGH = 1;
	public final static double STEP_HIGH	= PORTION_HIGH/20.0;
	
	
	/**
	 * The algorithm to be timed.
	 */
	private Process algorithm;
	
	/**
	 * The sample size to be taken on each growth iteration.
	 */
	private long sampleSize;
	
	/**
	 * The name of the timer
	 */
	private String name;


	/**
	 * Creates a new AlgorithmTimer for a specified algorithm process.
	 * @param algorithm The algorithm to be called.
	 * @param sampleSize The number of samples that will be averaged when time is called.
	 */
	public AlgorithmTimer(String name, Process algorithm, long sampleSize){
		this.algorithm = algorithm;
		this.sampleSize = sampleSize;
		this.name = name;
	}
	
	
	/**
	 * Runs a full test and generates timing data based on the given process.
	 * @param start The beginning N to time
	 * @param end The last N to time (inclusive)
	 */
	public void test(int start, int end){
		
		//VARIABLES GIVEN DIFFERENT SUB TESTS>
		int lowEnd 		= (int) (PORTION_LOW*(end-start)	+start);
		int midEnd 		= (int) (PORTION_MID*(end-start)	+start);
		int highEnd 	= (int) (PORTION_HIGH*(end-start)	+start);

		int lowStep 	= (int) (STEP_LOW*(end-start));
		int midStep 	= (int) (STEP_MID*(end-start));
		int highStep 	= (int) (STEP_HIGH*(end-start));
		
		
		System.out.println("------------------------------------------");
		System.out.println("\t\t" + name);
		System.out.println("------------------------------------------");
		System.out.print("\tTiming on: [" +start +", " + end +"] @ " + sampleSize + " samples.");
		
		for(TimeComplexity complexity : TimeComplexity.values())
		{
			System.out.println("\n\n----------------" + complexity.name() + " COMPLEXITY----------------");
			
			
			System.out.println("--[LOW RANGE] @ " + lowEnd);
			
			long lowVal = time(start, lowEnd, lowStep, complexity);
			System.out.println("TOTAL: " + lowVal + "\n");
			
			
			
			System.out.println("--[MID RANGE] @ " +  midEnd);
			
			long midVal = time(start, midEnd, midStep, complexity);
			System.out.println("TOTAL: " + midVal + "\n");
			
			
			
			System.out.println("--[HIGH RANGE] @ " + highEnd);
			
			long highVal = time(start, highEnd, highStep, complexity);
			System.out.println("TOTAL: " + highVal + "\n");

		}
	}
	
	
	/**
	 * Times the process at a given n
	 * @param n
	 * @return
	 */
	public long time(int n, TimeComplexity complexity)
	{
		//Initializing timing variables before timing as to stablize cache.
		long startTime = 0;
		long runTime = 0;
		long overTime = 0;
		
		long netTime = 0;
		
		//Warmup
		warmup(complexity);
		
		//Takes the average time of computing the algorithm's process
		for(int i = 0; i < sampleSize; i++){
			
			//Generate data
			algorithm.generateData(n, complexity);
			
			startTime = System.nanoTime();
			algorithm.run(n);
			
			runTime = System.nanoTime();
			algorithm.overhead(n);
			
			overTime = System.nanoTime();
			netTime += (runTime- startTime) - (overTime - runTime);
			
		}
		
		netTime /= sampleSize;
		
		return netTime;
	}
	
	/**
	 * Times the the algorithm from a starting n to an ending n and prints growth.
	 * @param start The starting point of growth timing.
	 * @param end The inclusive ending point of growth timing.
	 * @param step The amount by which the algorithm should step in collecting data.
	 */
	public long time(int start, int end, int step, TimeComplexity complexity){
		stabilize();
		
		
		long net = 0;
		for(int n = start; n <= end; n+= step){
			long cur = time(n, complexity);
			net+= cur;
			
			System.out.println(n +"\t"+ cur);
		}
		
		
		return net;
	}
	
	/**
	 * Moves algorithm byte-code/assemblies into the cache so that timing is uniform.
	 */
	private void warmup(TimeComplexity complexity){
		for(int i = 0; i < sampleSize; i++){
			//run the algorithm once to enter the data into the cache
			algorithm.generateData(1000, complexity);
			algorithm.run(1000);
			algorithm.overhead(1000);
		}
	}
	
	/**
	 * Stabilizes the thread for timing.
	 */
	private void stabilize(){
		//Stabalize thread.
		long t = System.nanoTime();
		while(System.nanoTime() - t < 2E9);
	}
	
	//---------------------------------------------------------------------
	
	public enum TimeComplexity{
		WORST,
		AVERAGE,
		BEST
	}
	
	
	//---------------------------------------------------------------------
	
	/**
	 * The process interface in which the algorithm will be run.
	 * @author wguss & cegielski
	 *
	 */
	public interface Process{
		/**
		 * 
		 * @param The number of elements to process with the algorithm.
		 * @param The complexity of the data to be generated
		 */
		public void generateData(int n, TimeComplexity complexity);
		
		
		/**
		 * Runs a process at a given N.
		 * @param n The number of elements to process with the algorithm.
		 */
		public void run(int n);
		
		
		/**
		 * Specifies the overhead of running a process so that only the algorithm is timed.
		 * The number of elements to process with the algorithm.
		 */
		public void overhead(int n);
	}
}


