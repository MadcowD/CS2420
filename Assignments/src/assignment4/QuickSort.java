package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * 
 * @author Maks
 * This class contains the quick sort algorithm
 */
public class QuickSort {



	public static void main(String[] args){
		ArrayList<Integer> unsorted = permuted(10);
		Integer[] arr = new Integer[10];
		int j = 0;
		for(Integer I : unsorted){
			arr[j] = I;
			j++;
		}

		System.out.println(Arrays.toString(arr));

		quickSort(arr);

		System.out.println(Arrays.toString(arr));

		//		System.out.println(unsorted);
		//		quickSortDriver(unsorted);
		//		System.out.println(unsorted);


	}

	public static <T extends Comparable<? super T>> void quickSort(ArrayList<T> list){

		quickSort(list, 0, list.size()-1);
	}

	public static <T extends Comparable<? super T>> void quickSort(ArrayList<T> list, int left, int right){

		if(left < right){
			T pivot = median(list, left, right);

			int i = left, j = right;
			while(true){
				while(list.get(++i).compareTo(pivot) < 0){}
				while(list.get(--j).compareTo(pivot) > 0){}
				if(i<j){
					swap(list, i, j);
				}
				else
					break;
			}

			swap(list, i, right);

			quickSort(list, left, i - 1);
			quickSort(list, i+1, right);

		}
		else{
			insertSort(list);
		}
	}



	private static <T extends Comparable<? super T>> T median(ArrayList<T> list, int left, int right){

		int center = (left + right)/2;

		if(list.get(center).compareTo(list.get(left)) < 0){
			swap(list, left, center);
		}
		if(list.get(right).compareTo(list.get(left)) < 0){
			swap(list, left, right);
		}
		if(list.get(right).compareTo(list.get(center)) < 0){
			swap(list, center, right);
		}

		swap(list, center, right-1);

		return list.get(right - 1);

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



	private static <T extends Comparable<? super T>> void swap(ArrayList<T> list, int left, int right){
		T temp = list.get(left);
		list.set(left, list.get(right));
		list.set(right, temp);
	}


////////////////////////////////////////////////ARRAYS///////////////////////////////////////////////////////////////////////////////////////

	public static <T extends Comparable<? super T>> void quickSort(T[] list){
		quickSort(list, 0, list.length-1);
	}

	private static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right){
		int CUTOFF = 5; //RIGHT NOW THIS IS NEEDED TO PREVENT ERROR
		if(left + CUTOFF <=  right){ 
			T pivot = median(a, left, right);


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
		else{
			insert(a);//QUICKSORT WON'T SORT WITHOUT INSERT :( THANKS A LOT BOOK
		}

	}

	private static <T extends Comparable<? super T>> T median(T[] list, int left, int right){
		int center = (left + right)/2;
		if(list[center].compareTo(list[left]) < 0)
			swap(list, left, center);
		if(list[right].compareTo(list[left]) < 0)
			swap(list, left, right);
		if(list[right].compareTo(list[center]) < 0)
			swap(list, center, right);

		swap(list, center, right - 1);
		return list[right-1];
	}

	private static <T extends Comparable<? super T>> void swap(T[] list, int left, int right){
		T temp = list[left];
		list[left] = list[right];
		list[right] = temp;
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


}
