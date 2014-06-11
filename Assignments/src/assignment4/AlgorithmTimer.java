package assignment4;

import assignment4.AlgorithmTimer.TimeComplexity;

public final class AlgorithmTimer {
	
	/**
	 * The complexity on which the algorithm will function
	 */
	private TimeComplexity complexity;
	
	/**
	 * The algorithm to be timed.
	 */
	private Process algorithm;
	
	/**
	 * The sample size to be taken on each growth iteration.
	 */
	private long sampleSize;


	/**
	 * Creates a new AlgorithmTimer for a specified algorithm process.
	 * @param algorithm The algorithm to be called.
	 * @param sampleSize The number of samples that will be averaged when time is called.
	 */
	public AlgorithmTimer(Process algorithm, long sampleSize, TimeComplexity complexity){
		this.algorithm = algorithm;
		this.sampleSize = sampleSize;
		this.complexity = complexity;
	}
	
	/**
	 * Times the process at a given n
	 * @param n
	 * @return
	 */
	public long time(int n)
	{
		//Initializing timing variables before timing as to stablize cache.
		long startTime = 0;
		long runTime = 0;
		long overTime = 0;
		
		long netTime = 0;
		
		//Warmup
		warmup();
		
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
	public long time(int start, int end, int step){
		stabilize();
		
		
		long net = 0;
		for(int n = start; n <= end; n+= step){
			long cur = time(n);
			net+= cur;
			
			
//			System.out.println(n + "\t" + cur);
		}
		
		
		return net;
	}
	
	/**
	 * Moves algorithm byte-code/assemblies into the cache so that timing is uniform.
	 */
	private void warmup(){
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


