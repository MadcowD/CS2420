package assignment3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * @author Paymon Saebi
 * @author
 * @author
 * 
 * ArrayBasedCollection timing experiments.
 */
public class ArrayBasedCollectionTimer 
{	
	/**
	 *
	 */
	public static void main(String[] args)
	{		
		// TODO add any preparations needed for the timing experiment

		timer();//Done
	}

	/**
	 *
	 */
	public static void timer()
	{



		//TIME SORT


		//Its a hashtable if we were going to save it to a csv contac card format #table
		Hashtable<Integer, Double> sortTimes = GrowthSort(1000,20000, 4000);

		//TIME BINARY


	}

	/// <summary>
	/// Creates a dictionary of points representing growth rates of the algorithm
	/// </summary>
	/// <param name="range">The range ( n to dispaly the growth rate)</param>
	/// <returns></returns>
	public static Hashtable<Integer, Double> GrowthSort(int start, int range, int averageCount)
	{
		Random rnd = new Random();
		Hashtable<Integer, Double> NTable = new Hashtable<Integer, Double>();


		//Go through the range
		for (int n = start; n < range+1; n+=500)
		{
			//Take the average algorithm time at range r
			long algorithmTime = 0;
			for (int a = 0; a < averageCount; a++)
			{
				//Timing
				ArrayBasedCollection<Integer> arr = new ArrayBasedCollection<Integer>();
				arr.addAll(ascendingInts(n));


				int warm = 0;
				while (warm < 10000)
				{ warm++; /*Let the thread warm up.*/ System.nanoTime(); }

				long warmUptime = System.nanoTime();

				//Run the algorithm
				arr.toSortedList(new IntegerComparator());
				long sortTime = System.nanoTime();
				//OVERHEAD

				for(int i = 1; i<n; i++){
					int j;
					for(j = i-1; j>=0; j--);
				}

				long overHead = System.nanoTime();
				
				//Calculate time
				algorithmTime += (sortTime - warmUptime) - (overHead - sortTime);

			}
			System.out.println(n + "\t" + (double) algorithmTime/averageCount);
			NTable.put(n ,(double) (algorithmTime/averageCount));
		}
		
		return NTable;
	}
	
	
	/// <summary>
	/// Creates a dictionary of points representing growth rates of the algorithm
	/// </summary>
	/// <param name="range">The range ( n to dispaly the growth rate)</param>
	/// <returns></returns>
	public Hashtable<Integer, Double> GrowthContains(int range, int averageCount)
	{
		Random rnd = new Random();
		Hashtable<Integer, Double> NTable = new Hashtable<Integer, Double>();


		//Go through the range
		for (int n = 0; n < range; n++)
		{
			System.out.println("N:" + n);
			//Take the average algorithm time at range r
			long algorithmTime = 0;
			for (int a = 0; a < averageCount; a++)
			{
				//Timing
				ArrayBasedCollection<Integer> arr = new ArrayBasedCollection<Integer>();
				arr.addAll(randomInts(n));
				
				
				Integer element = arr.get(arr.size()-1);

				int start = 0;
				while (start < 10000)
				{ start++; /*Let the thread warm up.*/ System.nanoTime(); }

				long warmUptime = System.nanoTime();

				//Run the algorithm
				arr.contains(element);
				long sortTime = System.nanoTime();
				//OVERHEAD


				long overHead = System.nanoTime();

				//Calculate time
				algorithmTime += (sortTime - warmUptime) - (overHead - sortTime);

			}

			NTable.put(n ,(double) (algorithmTime/averageCount));
		}

		return NTable;
	}

	/// <summary>
	/// Creates a dictionary of points representing growth rates of the algorithm
	/// </summary>
	/// <param name="range">The range ( n to dispaly the growth rate)</param>
	/// <returns></returns>
	public Hashtable<Integer, Double> GrowthBinary(int range, int averageCount)
	{
		Random rnd = new Random();
		Hashtable<Integer, Double> NTable = new Hashtable<Integer, Double>();


		//Go through the range
		for (int n = 0; n < range; n++)
		{
			System.out.println("N:" + n);
			//Take the average algorithm time at range r
			long algorithmTime = 0;
			for (int a = 0; a < averageCount; a++)
			{
				//Timing
				ArrayBasedCollection<Integer> arr = new ArrayBasedCollection<Integer>();
				arr.addAll(randomInts(n));
				
				
				Integer element = arr.get(arr.size()-1);

				int start = 0;
				while (start < 10000)
				{ start++; /*Let the thread warm up.*/ System.nanoTime(); }

				long warmUptime = System.nanoTime();

				//Run the algorithm
				//SearchUtil.binarySearch(arr.toSortedList(new IntegerComparator()), element, new IntegerComparator())
				long sortTime = System.nanoTime();
				//OVERHEAD


				long overHead = System.nanoTime();

				//Calculate time
				algorithmTime += (sortTime - warmUptime) - (overHead - sortTime);

			}

			NTable.put(n ,(double) (algorithmTime/averageCount));
		}

		return NTable;
	}



	// Generate an array of random integers [0..size - 1]
	public static ArrayList<Integer> randomInts(int size)
	{
		Random rand = new Random();
		ArrayList<Integer> retval = new ArrayList<Integer>();
		for(int i=0; i < size; i++)
			retval.add(rand.nextInt(size));
		return retval;
	}
	public static ArrayList<Integer> permuteInts(int size)
	{
		Random rand = new Random();
		int retval[] = ascendingInts(size, 1);
		for(int i=0; i < size; i++)
			swap(retval, i, rand.nextInt(size));
		
		ArrayList<Integer> retvaasdl = new ArrayList<Integer>();
		for(int i = 0; i < size;i++)
			retvaasdl.add(retval[i]);
		return retvaasdl;
	}

	// Generate an array of ascending integers
	public static ArrayList<Integer> ascendingInts(int size)
	{
		int retval[] = new int[size];
		for(int i=0; i < size; i++)
			retval[i] = i;
		ArrayList<Integer> retvaasdl = new ArrayList<Integer>();
		for(int i = 0; i < size;i++)
			retvaasdl.add(retval[i]);
		return retvaasdl;
	}
	
	// Generate an array of ascending integers
	public static int[] ascendingInts(int size, int x)
	{
		int retval[] = new int[size];
		for(int i=0; i < size; i++)
			retval[i] = i;
		return retval;
	}

	// Generate an array of descending integers
	public static ArrayList<Integer> descendingInts(int size)
	{
		int retval[] = new int[size];
		for(int i=0; i< size; i++)
			retval[i] = size - i - 1;
		ArrayList<Integer> retvaasdl = new ArrayList<Integer>();
		for(int i = 0; i < size;i++)
			retvaasdl.add(retval[i]);
		return retvaasdl;
	} 

	// Swaps two items in the given array
	public static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void printArray(int[] nums)
	{
		for(int k=0; k < nums.length; k++)
			System.out.print(nums[k] + " ");	
		System.out.println();
	}

	public static int[] copyArray(int[] nums)
	{
		int retval[] = new int[nums.length];		
		for(int i = 0; i < nums.length; i++)
			retval[i] = nums[i];		
		return retval;
	}

	// Determines if an array is sorted
	public static boolean isSorted(int[] nums)
	{
		for(int i=0; i < nums.length-1; i++)
			if(nums[i] > nums[i+1])
				return false;
		return true;
	}
}
