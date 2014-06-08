package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * 
 * @author Maks
 * 
 * This class contains primarily merge sort and some quicksort algorithms for Arrays[], copied directly from my textbook.
 *
 */
public class ArrayHelp {

	public static void main(String[] args){


		int size = 8;
		Integer[] insert = permuted(size);
		Integer[] merge = new Integer[size];
		for(int i= 0; i < insert.length; i++){
			merge[i] = insert[i];
		}
		
		System.out.println(Arrays.toString(insert));
		
		mergeSort(insert);
		
		System.out.println(Arrays.toString(insert));
		
		
		
		//		System.out.println(Arrays.toString(unsorted));


		//		mergeSort(unsorted);

		//		System.out.println(Arrays.toString(unsorted));

	}



	public static <T extends Comparable<? super T>> void insert(T[] list){
		int j;
		for(int i = 1; i < list.length; i++){
			T val = list[i];
			for(j = i; j>0 && val.compareTo(list[j-1]) < 0; j--)
				list[j] = list[j-1];
			list[j] = val;
		}
	}

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

	
	
	
	
	
	public static <T extends Comparable<? super T>> void quickSort(T[] a){
		quickSort(a, 0, a.length -1);
	}
	
	
	public static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right){
		if(left < right){
			T pivot = a[a.length/2];
			
			int i = left, j = right - 1;
			for( ; ; ){
				while(a[++i].compareTo(pivot) < 0){}
				while(a[--j].compareTo(pivot) > 0){}
				if(i < j)
					swap(a, i, j);
				else
					break;
			}
			
			swap(a, i, right - 1);
			quickSort(a, left, i - 1);
			quickSort(a, i + 1, right);
		}
	}



	public static Integer[] ascending(int size){
		Integer[] result = new Integer[size];

		for(int i = 0; i < size; i++)
			result[i] = i;
		return result;
	}

	public static Integer[] permuted(int size){
		Random rng = new Random();
		Integer[] result = ascending(size);
		for(int i = 0; i<size; i++){
			swap(result,i,rng.nextInt(size));
		}
		return result;
	}

	private static <T> void swap(T[] list, int left, int right){
		T temp = list[left];
		list[left] = list[right];
		list[right] = temp;
	}

}
