package assignment5;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a generic doubly linked list.
 * 
 * @author Paymon Saebi 
 * @author Maks Cegielski-Johnson
 * @author William Guss 
 * 
 * Description:
 * 
 * @param <E> - the type of elements contained in the linked list
 */
public class MyLinkedList<E> implements List<E> 
{
	//Instance variables
	int size;
	Node head;
	Node tail;
	
	/**
	 * Constructor.  Creates a blank linked list.
	 */
	public MyLinkedList() 
	{
		size = 0;
		head.next = null;
		tail.prev = null;
		
		head.prev = null;
		tail.next = null;
	}
	
	/**
	 * @param element - The element to add at the beginning of the list.
	 *  
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	public void addFirst(E element) 
	{
			Node n = new Node(element);
			n.next = head.next; //n points to the next item
			head.next.prev = n.next; //the next item points back at n
			n.prev = head; //n points back at head
			head.next = n;//finally, head points to n
			size++;
			if(size == 1)
				tail.prev = n;
			
	}
	
	/**
	 * @param element - The element to add at the end of the list.
	 * 
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	public void addLast(E o) 
	{
		Node n = new Node(o);
		n.next = tail;//n points to tail
		n.prev = tail.prev;//n points back to the item tail pointed to
		tail.prev.next = n;//the item that pointed to tail now points at n
		tail.prev = n;//the tail points back at n
		size++;
		if(size == 1)
			head.next = n;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range.
	 * O(N) for a doubly-linked list.
	 */
	public void add(int index, E element)
	{
		if(index >= size)
			throw new IndexOutOfBoundsException();
		int count = -1;
		Node temp = head;
		while(++count <= index){
			temp = temp.next;
		}
		
		Node n = new Node(element);
		n.next = temp;
		temp.prev.next = n;
		n.prev = temp.prev;
		temp.prev = n;
		size++;
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	public E getFirst() 
	{
		if(size == 0)
			throw new NoSuchElementException("This list is empty");
		return head.next.data;		
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	public E getLast() 
	{
		if(size == 0)
			throw new NoSuchElementException("This list is empty");
		return tail.prev.data;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range.
	 * O(N) for a doubly-linked list.
	 */
	public E get(int index)
	{
		if(index >= size)
			throw new IndexOutOfBoundsException();
		int count = -1;
		Node temp = head;
		while(++count <= index){
			temp = temp.next;
		}
		return temp.data;
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	public E removeFirst()
	{		
		if(size == 0)
			throw new NoSuchElementException();
		E temp = head.next.data;
		head.next.next.prev = head;
		head.next = head.next.next;
		size--;
		return temp;		
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	public E removeLast()
	{		
		if(size == 0)
			throw new NoSuchElementException();
		E temp = tail.prev.data;
		tail.prev.prev.next = tail;
		tail.prev = tail.prev.prev;
		size--;
		return temp;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range.
	 * O(N) for a doubly-linked list.
	 */
	public E remove(int index)
	{		
		if(index >= size)
			throw new IndexOutOfBoundsException();
		Node temp = head;
		int count = -1;
		while(++count <= index){
			temp = temp.next;
		}
		E t = temp.data;
		temp.next.prev = temp.prev;
		temp.prev.next = temp.next;
		size--;
		return t;
	}
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present
	 * Returns true if the element was found and removed, false otherwise
	 * O(N) for a doubly-linked list.
	 */
	public boolean remove(E element) 
	{
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
			if(temp.data.equals(element)){
				temp.next.prev = temp.prev;
				temp.prev.next = temp.next;
				size--;
				return true;
				
			}
		}
		return false;
	}

	/**
	 * Returns true if this list contains the specified element
	 * or false if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	public boolean contains(E element) 
	{
		Node temp = head;
		while(temp.next != null){
			if(temp.data.equals(element))
				return true;
			temp = temp.next;
		}	
		return false;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	public int indexOf(E element) 
	{		
		Node temp = head;
		int index = -1;
		while(temp.next != null){
			if(temp.data.equals(element))
				return index;
			index++;
			temp = temp.next;
		}
		return index;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	public int lastIndexOf(E element) 
	{		
		Node temp = tail;
		int index = size;
		while(temp.prev != null){
			if(temp.data.equals(element))
				return index;
			temp = temp.prev;
			index--;
		}
		return index;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	public int size() 
	{
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	public boolean isEmpty() 
	{
		return size == 0;
	}
	
	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	public void clear() 
	{
		head.next = null;
		tail.prev = null;
		size = 0;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	public Object[] toArray() 
	{				
		Object[] result = new Object[size];
		Node temp = head;
		int i = 0;
		while(temp.next != null){
			temp = temp.next;
			result[i] = temp.data;
			i++;
		}
		
		return result;
	}

	private class Node 
	{		
		E data;
		Node next;
		Node prev;
		
		public Node(E element)
		{
			data = element;
			
		}		
	}		
}
