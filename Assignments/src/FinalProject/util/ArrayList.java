package FinalProject.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * This class Implements the Collection interface using an array as storage. The array must grow as needed.
 * All methods should be implemented as defined by the Java API, unless otherwise specified in the assignment.
 * 
 * It is your job to fill in the missing implementations and to header comment for this class. Every method should have
 * comments (Javadoc-style prefered, but not necessary). The comments should at least indicate what the method
 * does, what the arguments mean, and what the returned value is. It should specify any special cases that the method
 * handles. Any code that is not self-explanatory should be in-line commented.
 *
 * @param <E> - generic type placeholder
 */
public class ArrayList<E> implements Collection<E> 
{
	E data[]; // Storage for the items in the collection
	int size; // Keep track of how many items we hold

	/**
	 *  Creates a new ArrayBasedCollection with an initial size of 0
	 */ 
	@SuppressWarnings("unchecked")  
	public ArrayList()
	{

		size = 0;
		// There is no clean way to convert between E and Object, the text book discusses this.
		// We can't instantiate an array of unknown type E, so we must create an Object array, and type-cast
		data = (E[]) new Object[10]; // Start with an initial capacity of 10
	}
		
	/**
	 *  Private grow method used by the add function, if the data array is too small will enlarge the 
	 *  data array by a factor of two and then copy all the elements over
	 */ 
	@SuppressWarnings("unchecked")
	private void grow()
	{
		// This is a helper function specific to ArrayCollection
		// Doubles the size of the data storage array, retaining its current contents.
		// You will need to use something similar to the code in the constructor above to create a new array.
		
		E[] newData = (E[]) new Object[data.length*2];
		for(int i = 0; i < data.length; i++)
			newData[i] = data[i];
		
		this.data = newData;
		//QED.
	}	
	
	
        /**
	 *
	 * To string method that returns data[] as a String array, used primarily for testing
	 *
	 */
	public String toString(){
		return Arrays.toString(data);
		
	}
	
	
	/**
	 * Will add the argument to the array if it is 
	 * not already in the list, returns true if the item 
	 * was successfully added, false if the item is 
	 * already contained in the list
	 */
	public boolean add(E arg0) 
	{

		//if arg0 is already in the array return false
		if(this.contains(arg0))
			return false;

		if(size == data.length)
			grow();
		data[size++] = arg0;
		
		
		return true;
	}
	
	/**
	 *  Will add every item from a collection to the list, returning true
	 *  if any items were added, false otherwise
	 */ 
	public boolean addAll(Collection<? extends E> arg0) 
	{
		boolean itemsAdded = false;//If any item is added to the list this boolean is set to true
		for(E item : arg0){
			itemsAdded =  this.add(item);
		}
		
		return itemsAdded;
	}

	/**
	 *  Will remove every item from the list, setting the size to zero
	 */ 
	public void clear() 
	{
		for(int i = 0; i < size; i++)
			data[i] = null;
		size = 0;
	}

	/**
	 *  Checks whether the given argument is contained in the list, returning true if it is, false otherwise
	 */ 
	public boolean contains(Object arg0) 
	{
		for(int i = 0; i < size; i++)//Searches through the list at O(n)
			if(data[i].equals(arg0))
				return true;
		return false;
	}

	/**
	 *  Checks whether the list contains every item in the collection argument, returns true if every item is in the array collection,
	 *  false otherwise
	 */ 
	public boolean containsAll(Collection<?> arg0) 
	{
		for(Object item : arg0)
			if(!this.contains(item))
				return false;
		return true; 
	}

	/**
	 * **WE NEEDED THIS FOR TESTING AND FOR EASE OF USE**
	 * 
	 * Returns the <E> item at the given index i
	 * @param i - index
	 * @return - <E> item at index
	 */
	public E get(int i){
		if(i<size && i>=0){
			return data[i];
		}
		return null;
	}
	
	/**
	 * **USED FOR TESTING**
	 * 
	 * returns the length of the base data array that holds all the elements
	 * @return length of data
	 */
	public int getBounds(){
		return this.data.length;
	}
	
	/**
	 *  Returns true if the collection is empty, false otherwise
	 */ 
	public boolean isEmpty() 
	{
		return size == 0;
	}

	/**
	 *  Generic iterator for <E> that uses the ArrayBasedIterator() contained within the class to iterate through elements
	 */ 
	public Iterator<E> iterator() 
	{
		return new ArrayBasedIterator();
	}

	/**
	 *  Removes a given argument from the collection, 
	 *  returns true if the item was successfully removed, false if the item was not contained in the list
	 */ 
	public boolean remove(Object arg0) 
	{
		for(int i = 0; i < size; i++)
			if(data[i].equals(arg0)){
				if(i == size-1)
					data[i] = null;
				for(int j = i; j < size-1; j++){
					data[j] = data[j+1];//Shifting all the elements to the right of the removed item to the left
					data[j+1] = null;//setting the previously shifted element to null
				}
				size--;
				return true;
			}
		
		return !!!true; // >:) hehehe 
	}

	/**
	 *  Removes every element that is in the union of the ArrayBasedCollection this and the argument collection,
	 *  returns true if any element was removed, false if no elements removed
	 */ 
	public boolean removeAll(Collection<?> arg0) 
	{
		boolean removed = false;
		Iterator<E> i = this.iterator();
		
		while(i.hasNext()){
			E element = i.next();
			if(arg0.contains(element)){
				i.remove();
				removed = true;
			}
		}
		
		return removed;
	}

	/**
	 *  Removes every element that is not in the intersection of the argument collection and the ArrayBasedCollection
	 */ 
	public boolean retainAll(Collection<?> arg0) 
	{
		boolean removed = false;
		Iterator<E> i = this.iterator();
		
		while(i.hasNext()){
			E element = i.next();
			if(!arg0.contains(element)){
				i.remove();
				removed = true;
			}
		}
		
		return removed;
	}

	/**
	 *  Returns the number of items in the ArrayBasedCollection
	 */ 
	public int size() 
	{
		return size;
	}
	
	/**
	 *  Returns a new Object[] array that contains all the items in the collection, 
	 */ 
	public Object[] toArray() 
	{
		Object[] result = new Object[size];
		
		for(int i = 0; i < size; i++)
			result[i] = data[i];
		
		
		return result;
	}

	/** 
	 * This follows from the Collection :) --Not required-- dont grade-- pls --- pls
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] arg0) 
	{
		//Don't implement this method (unless you want to).
		// It must be here to complete the Collection interface.
		// We will not test this method.
	

		T[] results = (T[]) new Object[size];
		
		
		for(int i = 0; i < size; i++){
			results[i] = (T)data[i];
			if(arg0.length < size)
				arg0[i] = (T)data[i];
		}

		
		
		return results;
			
	}  
  

   
	/**
	 *  Iterator for the ArrayBasedCollection class
	 */ 
	private class ArrayBasedIterator implements Iterator<E>
	{
		int index;
		boolean removedNext;
	   
		public ArrayBasedIterator()
		{
			index = -1;//not zero because the first element to be returned is data[0]
			removedNext = false;//depends on whether an item was removed already(True) or not (False)
		}	
		
		/**
		 * Returns true if the next item in the collection is not empty/null, otherwise returns false
		 */
		public boolean hasNext() 
		{
			if(data.length > index+1 && data[index+1] != null)
				return true;
			return false;
		}
		
		/**
		 * Returns the next item<E> in the iteration, throws an exception if there is no item
		 */
		public E next() 
		{
			if(hasNext()){
				removedNext= false;
				return data[++index];
			}
			else
				throw new NoSuchElementException();
		}
		
		/**
		 * Removes the last iterated item in the collection, throws an exception if an item was already removed or
		 * if there was no item yet.
		 */
		public void remove() 
		{
			if(!removedNext){
				removedNext =  true;
				ArrayList.this.remove(data[index--]);
			}
			else
				throw new IllegalStateException();
			
		}	
	}
}
