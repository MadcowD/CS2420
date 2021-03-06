package assignment9;

import java.util.*;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a binary search tree. 
 * 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class PriorityQueueBST<AnyType> 
{
	private BinarySearchTree<AnyType> BST;//The backing BST

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	public PriorityQueueBST() 
	{
		this.BST = new BinarySearchTree<AnyType>();
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator 
	 * (i.e., AnyType need not be Comparable).
	 */
	public PriorityQueueBST(Comparator<? super AnyType> cmp) 
	{
		this.BST = new BinarySearchTree<AnyType>(cmp);
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() 
	{
		return this.BST.size();
	}
	
	/**
	 * @return whether the BST is empty or not
	 */
	public boolean isEmpty(){
		return this.BST.isEmpty();
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() 
	{
		this.BST.clear();
	}

	/**
	 * (Runs in logarithmic time.)
	 * 
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 */
	public AnyType findMin() throws NoSuchElementException 
	{
		//Exception throw in BST class itself
		return this.BST.first();
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * (Runs in logarithmic time.)
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 */
	public AnyType deleteMin() throws NoSuchElementException 
	{
		//Exception throw in BST class itself
		AnyType item = this.BST.first();
		this.BST.remove(item);
		return item;
	}

	/**
	 * Adds an item to this priority queue. 
	 * (Runs in logarithmic time.) 
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) 
	{
		this.BST.add(x);
	}	

	/**
	 * Should simply call your BST toArrayList
	 * 
	 * @return Object array containing the PQs elements
	 */
	public Object[] toArray() 
	{    
		return BST.toArrayList().toArray();
	}	
}



