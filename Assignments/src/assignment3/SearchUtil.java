package assignment3;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * 
 */
public class SearchUtil 
{
	/**
	 * Searches through a sorted list using a recursive binary search algorithm, returning true if the item is in the list, false otherwise
	 * 
	 * @param <T>	The type of elements contained in the list
	 * @param list	An ArrayList to search through. 
	 * 				This ArrayList is assumed to be sorted according 
	 * 				to the supplied comparator. This method has
	 * 				undefined behavior if the list is not sorted. 
	 * @param item	The item to search for
	 * @param cmp	Comparator for comparing Ts or a super class of T
	 * @return		true if "item" exists in "list", false otherwise
	 */
	public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp)
	{	
		int comparison= cmp.compare(list.get(list.size()/2), item);
		if(comparison != 0)
		{
			//If we go to the bottom of the tree and there is like roots that are unequal then its not in the list.
			if(list.size() <= 1)
				return false;
			
			if(comparison > 0){
				//take the left hand half of the list.
				ArrayList<T> left =  new ArrayList<T>( list.subList(0, list.size()/2));
				return binarySearch(left, item, cmp);
			}
			else
			{
				//take the right hand half of the list.
				ArrayList<T> right = new ArrayList<T>(list.subList( list.size()/2, list.size()));
				return binarySearch(right, item, cmp);
			}
			
		}
		else
			//The item is contained in the list
			return true;

	}
	
}
