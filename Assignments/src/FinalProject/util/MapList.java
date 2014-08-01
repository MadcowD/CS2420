package FinalProject.util;


import java.util.Arrays;

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
public class MapList<E, V>
{
	KeyValuePair<E,V> data[]; // Storage for the items in the collection
	int size; // Keep track of how many items we hold

	/**
	 *  Creates a new ArrayBasedCollection with an initial size of 0
	 */ 
	@SuppressWarnings("unchecked")  
	public MapList()
	{

		size = 0;
		// There is no clean way to convert between E and Object, the text book discusses this.
		// We can't instantiate an array of unknown type E, so we must create an Object array, and type-cast
		data = (KeyValuePair<E, V>[]) new Object[10]; // Start with an initial capacity of 10
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

		KeyValuePair<E, V>[] newData = (KeyValuePair<E, V>[]) new Object[data.length*2];
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
	public boolean put(E arg0, V value) 
	{

		//if arg0 is already in the array return false
		for(int i = 0; i < size; i++)
			if(data[i].Key.equals(arg0))
			{
				data[i].Value = value;
				return true;
			}

		if(size == data.length)
			grow();
		data[size++] = new KeyValuePair(arg0, value);


		return true;
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
	public boolean containsKey(E arg0) 
	{
		for(int i = 0; i < size; i++)//Searches through the list at O(n)
			if(data[i].Key.equals(arg0))
				return true;
		return false;
	}

	public boolean contains(E arg0, V value){
		for(int i = 0; i < size; i++)//Searches through the list at O(n)
			if(data[i].Key.equals(arg0))
				if(data[i].Value.equals(value))
					return true;
		return false;
	}

	public boolean contains(V value){
		for(int i = 0; i < size; i++)//Searches through the list at O(n)

			if(data[i].Value.equals(value))
				return true;
		return false;
	}



	public V get(E arg0){
		for(int i = 0; i < size; i++)
			if(data[i].Key.equals(arg0))
				return data[i].Value;

		return null;
	}

	/**
	 * **WE NEEDED THIS FOR TESTING AND FOR EASE OF USE**
	 * 
	 * Returns the <E> item at the given index i
	 * @param i - index
	 * @return - <E> item at index
	 */
	public E keyOf(V arg0){
		for(int i = 0; i < size; i++)
			if(data[i].Value.equals(arg0))
				return data[i].Key;

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
	 *  Removes a given argument from the collection, 
	 *  returns true if the item was successfully removed, false if the item was not contained in the list
	 */ 
	public V remove(E arg0) 
	{
		V retV = null;
		for(int i = 0; i < size; i++)
			if(data[i].Key.equals(arg0)){
				retV = data[i].Value;
				if(i == size-1){

					data[i] = null;
				}
				else
				{
					for(int j = i; j < size-1; j++){
						data[j] = data[j+1];//Shifting all the elements to the right of the removed item to the left
						data[j+1] = null;//setting the previously shifted element to null
					}

				}
				size--;

			}

		return retV;
	}

	
	public void foreach(Action<E, V> actionToDo){
		for(int i = 0; i < size; i++){
			actionToDo.run(data[i].Key, data[i].Value);
		}
		
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



}
