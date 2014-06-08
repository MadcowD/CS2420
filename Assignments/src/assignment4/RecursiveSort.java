package assignment4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Maks
 * This class contains merge sort algorithms
 *
 */
public class RecursiveSort {



	public static void main(String[] args){

	
		int size = 10;
		Integer[] arr = new Integer[size];

		
		ArrayList<Integer> list = permuted(size);
		int j = 0;
		for(Integer i : list){
			arr[j] = i;
			j++;
		}
		
		
		
		System.out.println(list);
		mergeSort(list);
		System.out.println(list);
		
		
		
		
//		System.out.println("ArrayList of size " + size + " before sorting: " + list);
//		System.out.println("Array of size " + size + " before sorting: " + Arrays.toString(arr));
			
//		cache();
//		long startArr = System.nanoTime();
//		mergeSort(arr);
//		long endArr = System.nanoTime();
//		cache();
//		long startList = System.nanoTime();
//		mergeSort(list);
//		long endList = System.nanoTime();
		
		
//		System.out.println("ArrayList sorting took " + (endList - startList) + " nano seconds and looks like " + list);
//		System.out.println("Array sorting took " + (endArr - startArr) + " nano seconds and looks like " + Arrays.toString(arr));
		
		
//		
//		System.out.println("ArrayList sorting took " + (endList - startList));
//		System.out.println("Array sorting took " + (endArr - startArr));


	}


	public static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> list){
		ArrayList<T> temp = new ArrayList<T>(list.size());
		for(int i = 0; i<list.size(); i++){
			temp.add(null);
		}
		mergeSortRecursive(list, temp, 0, list.size() - 1);
	}

	public static <T extends Comparable<? super T>> void mergeSortRecursive(ArrayList<T> list, ArrayList<T> temp, int start, int end){
		if(start < end){
			int mid = (start + end)/2;
			mergeSortRecursive(list, temp, start, mid);
			mergeSortRecursive(list, temp, mid+1, end);
			merge(list, temp, start, mid+1, end);
		}
	}

	public static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> temp, int start, int mid, int end){
		int right = mid-1;
		int numberElements = end - start + 1;
		int tempPos = start;
		//temp.add(list.get(start++));
		//temp.set(tempPos++, list.get(start++));

		while(start <= right && mid<=end)
			if(list.get(start).compareTo(list.get(mid)) <= 0)
				temp.set(tempPos++, list.get(start++));
			else
				temp.set(tempPos++, list.get(mid++));

		while(start <= right)
			temp.set(tempPos++, list.get(start++));
		while(mid <= end)
			temp.set(tempPos++, list.get(mid++));

		for(int i = 0; i<numberElements; i++,end--){
			list.set(end, temp.get(end));
		}
		
	}

	
	public static <T extends Comparable <? super T>> void insertSort(ArrayList<T> list){
		
		int j;
		for(int i = 0; i<list.size(); i++){
			T val = list.get(i);
			for(j = i; j>0 && val.compareTo(list.get(j-1))<0;j--)
				list.set(j, list.get(j-1));
			list.set(j, val);
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	public static <T extends Comparable<? super T>> void mergeSort(T[] list){
		T[] temp = (T[]) new Comparable[list.length];
		mergeSort(list, temp, 0, list.length - 1);
	}

	private static <T extends Comparable<? super T>> void mergeSort(T[] list, T[] temp, int left, int right){
		if(left < right){
			int center = (left + right)/2;
			mergeSort(list, temp, left, center);
			mergeSort(list, temp, center+1, right);
			merge(list, temp, left, center+1, right);
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] a, T[] temp, int leftPos, int rightPos, int rightEnd){

		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;


		while(leftPos <= leftEnd && rightPos <= rightEnd)
			if(a[leftPos].compareTo(a[rightPos]) <=0)
				temp[tmpPos++] = a[leftPos++];
			else
				temp[tmpPos++] = a[rightPos++];

		while(leftPos <= leftEnd)
			temp[tmpPos++] = a[leftPos++];
		while(rightPos <= rightEnd)
			temp[tmpPos++] = a[rightPos++];

		for(int i = 0; i<numElements; i++, rightEnd--)
			a[rightEnd] = temp[rightEnd];
	}

	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void cache(int n){
		for(int i = 0; i<n; i++){
			
		}
	}

	public static ArrayList<Integer> ascending(int size){
		ArrayList<Integer> result= new ArrayList<>();

		for(int i = 0; i < size; i++)
			result.add(i);
		return result;
	}

	public static ArrayList<Integer> permuted(int size){
		Random rng = new Random();
		ArrayList<Integer> result = ascending(size);
		for(int i = 0; i<size; i++){
			swap(result,i,rng.nextInt(size));
		}
		return result;
	}

	private static void swap(ArrayList<Integer> list, int left, int right){
		int temp = list.get(left);
		list.set(left, list.get(right));
		list.set(right, temp);
	}

}
