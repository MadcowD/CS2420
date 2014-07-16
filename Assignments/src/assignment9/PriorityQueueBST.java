package assignment9;

import java.util.*;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a binary search tree. 
 * 
 * @author Paymon Saebi
 * @author
 * @author
 */
public class PriorityQueueBST<AnyType> 
{
	private BinarySearchTree<AnyType> BST;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	public PriorityQueueBST() 
	{
		//TODO
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator 
	 * (i.e., AnyType need not be Comparable).
	 */
	public PriorityQueueBST(Comparator<? super AnyType> cmp) 
	{
		//TODO
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() 
	{
		//TODO
		return 0;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() 
	{
		//TODO
	}

	/**
	 * (Runs in logarithmic time.)
	 * 
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 */
	public AnyType findMin() throws NoSuchElementException 
	{
		//TODO
		return null;
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * (Runs in logarithmic time.)
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 */
	public AnyType deleteMin() throws NoSuchElementException 
	{
		//TODO
		return null;
	}

	/**
	 * Adds an item to this priority queue. 
	 * (Runs in logarithmic time.) 
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) 
	{
		//TODO
	}	

	/**
	 * Should simply call your BST toArrayList
	 * 
	 * @return Object array containing the PQs elements
	 */
	public Object[] toArray() 
	{    
		//TODO
		return null;
	}	
}



