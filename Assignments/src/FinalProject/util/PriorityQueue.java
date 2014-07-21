package FinalProject.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a min heap. 
 * The min heap is implemented implicitly as an array.
 * 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class PriorityQueue<AnyType> 
{
	private AnyType[] array;//basing array
	private int currentSize;//how many items in the heap	
	private Comparator<? super AnyType> cmp; //the comparator

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue() 
	{
		array = (AnyType[]) new Object[10]; 
		currentSize = 0;			
		cmp = null;
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator 
	 * (i.e., AnyType need not be Comparable).
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(Comparator<? super AnyType> _cmp) 
	{
		array = (AnyType[]) new Object[10];
		currentSize = 0;		
		cmp = _cmp;
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() 
	{
		return this.currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	@SuppressWarnings("unchecked")
	public void clear() 
	{
		//just point the array at a new Object[], let garbage collect
		array = (AnyType[]) new Object[10]; 
		this.currentSize = 0;
	}

	/**
	 * Returns whether the queue is empty or not
	 */
	public boolean isEmpty(){
		return this.currentSize == 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException 
	{
		if(this.isEmpty())
			throw new NoSuchElementException();
		
		return array[1];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * (Runs in logarithmic time.)
	 * 
	 * @return The minimum item in the priority queue
	 * @throws NoSuchElementException if this priority queue is empty. 
	 */
	public AnyType deleteMin() throws NoSuchElementException 
	{
		if(this.isEmpty())
			throw new NoSuchElementException();

		AnyType minItem = this.findMin();//get the min item
		
		this.array[1] = array[this.currentSize--];//replace first item with the last item
		
		percolateDown(1);

		return minItem;
	}

	
	/**
	 * Helper method to percolate down, used for deleteMin
	 * @param hole
	 */
	private void percolateDown(int hole){
		int child;
		AnyType tmp = this.array[hole];

		for( ; hole*2 <= this.currentSize; hole = child){
			child = hole*2;
			if(child != this.currentSize && compare(this.array[child+1], this.array[child])< 0)
				child++;
			if(compare(this.array[child],tmp)<0)
				this.array[hole] = this.array[child];
			else
				break;
		}
		
		this.array[hole] = tmp;
	}

	/**
	 * Adds an item to this priority queue.
	 * If the underlying array becomes full, it gets doubled 
	 * (Runs in constant time, worst case logarithmic)
	 * 
	 * @param x - the item to be inserted
	 */
	public void add(AnyType x) 
	{
		if(currentSize == array.length - 1)
			this.enlargeArray(array.length*2 + 1);
		
		int hole = ++this.currentSize;
			
		//percolate up
		for(this.array[0] = x; compare(x, this.array[hole/2]) < 0; hole /= 2)
			this.array[hole] = this.array[hole/2];
		this.array[hole] = x;
		this.array[0] = null;			
	}
	
	/**
	 * Used to grow the array if it is too small
	 * @param size - the size to enlarge to
	 */
	@SuppressWarnings("unchecked")
	private void enlargeArray(int size){
		AnyType[] arr = (AnyType[])new Object[size];
		
		for(int i = 1; i<this.array.length; i++)
			arr[i] = this.array[i];
		
		this.array = arr;
		
	}
	


	/**
	 * Simply use the underlying array and loop through it 
	 *
	 * @return object array containing the pq elements
	 */
	public Object[] toArray() 
	{    
		Object[] result = new Object[this.currentSize];
		for(int i = 1; i<1+this.currentSize;  i++)
			result[i-1] = this.array[i];
		return result;
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 */
	@SuppressWarnings("unchecked")
	private int compare(AnyType lhs, AnyType rhs) 
	{
		if(lhs == null | rhs == null)
			return -1;
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) 
	{
		try 
		{
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for(int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if(((i*2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
				if(((i*2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
			}

			out.println("}");
			out.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}
}
