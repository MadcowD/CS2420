package assignment4;

import java.util.ArrayList;
import java.util.Random;

public class QuickSort1{
	
	public static void main(String[] args) {
		ArrayList<Integer> uns = permuted(20);
		
		System.out.println(uns);
		quickSort(uns);
		System.out.println(uns);

		
	}




	public static <T extends Comparable<? super T>> void quickSort(ArrayList<T> list){
		if(list.size() <= 1)
			return;

		quickSort(list, 0, list.size() - 1);
	}

	private static <T extends Comparable<? super T>> void quickSort(ArrayList<T> list, int left, int right){

		if(right-left <= 0)
			return;
		
		int split = split(list,left,right);
		quickSort(list, left, split - 1);
		quickSort(list, split+1, right);


	}


	private static <T extends Comparable<? super T>> int split(ArrayList<T> list, int low, int high){

		int left = low + 1;
		int right = high;
		T pivot = list.get(low);

		for(;;){
			while(left <= right){
				if(list.get(left).compareTo(pivot) < 0)
					left++;
				else
					break;
			}

			while(right > left){
				if(list.get(right).compareTo(pivot) > 0)
					right--;
				else
					break;
			}

			if(left >= right)
				break;
			
			swap(list, left, right);
			left++; right--;
		}
		list.set(low, list.get(left-1));
		list.set(left-1, pivot);
		
		return left-1;
	}
	
	private static <T> void swap(ArrayList<T> list, int left, int right){
		T temp = list.get(left);
		list.set(left, list.get(right));
		list.set(right, temp);
	}
	
	
	private static ArrayList<Integer> ascending(int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i<size; i++)
			result.add(i);
		
		return result;
	}
	
	public static ArrayList<Integer> permuted(int size){
		Random rng = new Random();
		ArrayList<Integer> list = ascending(size);
		
		for(int i = 0; i < size; i++){
			swap(list, i, rng.nextInt(size));
		}
		
		return list;
	}

	
}