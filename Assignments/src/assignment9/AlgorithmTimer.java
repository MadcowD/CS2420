package assignment9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


 /** A timing framework built for CS2420, because we are lazy. 
 * Essentially takes a functor (AlgorithmTimer.Process) and tests it using different
 * methods and complexities. Streamlines data export.
 * @author Maks Cegielski-Johnson
 * @author William Guss
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
	public void testVerbose(int start, int end){

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

			long lowVal = time(start, lowEnd, lowStep, complexity, null);
			System.out.println("TOTAL: " + lowVal + "\n");



			System.out.println("--[MID RANGE] @ " +  midEnd);

			long midVal = time(start, midEnd, midStep, complexity, null);
			System.out.println("TOTAL: " + midVal + "\n");



			System.out.println("--[HIGH RANGE] @ " + highEnd);

			long highVal = time(start, highEnd, highStep, complexity, null);
			System.out.println("TOTAL: " + highVal + "\n");

		}
	}


	/**
	 * Runs a full test and generates timing data based on the given process.
	 * @param start The beginning N to time
	 * @param end The last N to time (inclusive)
	 * @param complexities The complexities overwhich to run the analysis
	 */
	public void generateAnalysis(int start, int end, TimeComplexity[] complexities){

		//VARIABLES GIVEN DIFFERENT SUB TESTS>
		int lowEnd 		= (int) (PORTION_LOW*(end-start)	+start);
		int midEnd 		= (int) (PORTION_MID*(end-start)	+start);
		int highEnd 	= (int) (PORTION_HIGH*(end-start)	+start);

		int lowStep 	= (int) (STEP_LOW*(end-start));
		int midStep 	= (int) (STEP_MID*(end-start));
		int highStep 	= (int) (STEP_HIGH*(end-start));

		//make surrounding directories.
		new File("./data/" +name +"/").mkdirs();

		//The final analysis
		String fa = "------------------------------------------\r\n";
		fa += "\t\t" + name + "\r\n";
		fa+= "------------------------------------------\r\n";
		fa+= "\tTiming on: [" +start +", " + end +"] @ " + sampleSize + " samples.\r\n";
		
		
		for(TimeComplexity complexity : complexities)
		{
			String complexityDir = "./data/" +name +"/" + complexity.name() + "/";
			System.out.println("\n\n----------------" + complexity.name() + " COMPLEXITY----------------");
			fa += "-----"+ complexity.name() + " COMPLEXITY-----\r\n\r\n";
			new File(complexityDir).mkdirs();


//			System.out.println("--[LOW RANGE] @ " + lowEnd);
//
//			Hashtable<Integer,Long> lowData = new Hashtable<Integer,Long>();
//			long lowVal = time(start, lowEnd, lowStep, complexity, lowData);
//			writeMap(lowData, complexityDir + "low@" + lowEnd + ".txt");
//			System.out.println("TOTAL: " + lowVal + "\n");
//			fa+="\t[LOW RANGE] @" +lowEnd + ": "+ lowVal +"\r\n";
//
//
//
//			System.out.println("--[MID RANGE] @ " +  midEnd);
//
//			Hashtable<Integer,Long> midData = new Hashtable<Integer,Long>();
//			long midVal = time(start, midEnd, midStep, complexity, midData);
//			System.out.println("TOTAL: " + midVal + "\n");
//			writeMap(midData, complexityDir + "mid@" + midEnd+ ".txt");
//			fa+="\t[MID RANGE] @" +midEnd + ": "+ midVal +"\r\n";



			System.out.println("--[HIGH RANGE] @ " + highEnd);
			Hashtable<Integer,Long> highData = new Hashtable<Integer,Long>();
			long highVal = time(start, highEnd, highStep, complexity,highData);
			writeMap(highData, complexityDir + "high@" + highEnd + ".txt");
			System.out.println("TOTAL: " + highVal + "\r\n");
			fa+="\t[HIGH RANGE] @" +highEnd + ": "+ highVal +"\r\n";

		}
		
		//TODO: ADD MORE ANALYSIS TO THE DOCUMENT SUCH AS POLYNOMIAL OF BEST FIT.
		

		try {
			FileWriter w = new FileWriter("./data/" +name +"/analysis.txt");
			w.write(fa);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	/**
	 * Runs a full test and generates timing data based on the given process.
	 * RUns on all complexities.
	 * @param start The beginning N to time
	 * @param end The last N to time (inclusive)
	 */
	public void generateAnalysis(int start, int end){
		this.generateAnalysis(start, end, TimeComplexity.values());
	}


	public <K,V> void writeMap(Map<K,V> data, String fileLocation){

		try {
			FileWriter w = new FileWriter(fileLocation);
			for(K key : data.keySet())
				w.write(key.toString() + "\t" + data.get(key).toString() +"\r\n");

			w.close();
		} catch (IOException e) {
			e.printStackTrace();
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
			long code = algorithm.run(n,complexity);


			runTime = System.nanoTime();
			algorithm.overhead(n,complexity);

			overTime = System.nanoTime();
			
			// In the case the we are collecting numerical data such as hash collisons
			if(code == 0)
				netTime += (runTime- startTime);// - (overTime - runTime);
			else
				netTime += code;

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
	public long time(int start, int end, int step, TimeComplexity complexity, Map<Integer,Long> data){
		stabilize();


		long net = 0;
		for(int n = start; n <= end; n+= step){
			long cur = time(n, complexity);
			net+= cur;
			if(data!= null)
				data.put(n, cur);

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
			algorithm.run(1000, complexity);
			algorithm.overhead(1000, complexity);
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
}


