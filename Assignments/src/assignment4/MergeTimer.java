package assignment4;

import java.util.ArrayList;

/**
 * 
 * @author Owner
 * This class contains timing for Merge, Insert, and Quick using ArrayLists and Arrays[]
 */
public class MergeTimer {




	public static void main(String[] args){
		
		ArrayTimer(500, 20000, 250);
	}
	
	

	public static void ArrayTimer(int size, int limit, int timestoloop){

		for(int n = size; n<=limit; n+=500){
			long totalTime = 0;
			for(int i = 0; i<timestoloop; i++){

				Integer[] arr = ArrayHelp.permuted(n); 				//Array
//				ArrayList<Integer> list = RecursiveSort.permuted(n); //ArrayList
				
				
				
				RecursiveSort.cache(1000);
				long start = System.nanoTime();
//				RecursiveSort.mergeSort(list);              //ArrayList
//				RecursiveSort.mergeSort(arr);				//Array
//				RecursiveSort.insertSort(list);				//ArrayList insert
//				QuickSort.quickSort(list);					//ArrayList quick
				QuickSort.quickSort(arr);
//				QuickSort.insertSort(list);					//ArrayList quickInsert
//				QuickSort.insert(arr);
				
				long end = System.nanoTime();

				totalTime += (end - start);
			}
			
			System.out.println(n + "\t" + (totalTime/timestoloop));
		}
	}
}