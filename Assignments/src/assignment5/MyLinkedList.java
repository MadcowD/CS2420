package assignment5;

import java.util.NoSuchElementException;

/**
 * Represents a generic doubly linked list.
 * 
 * @author Paymon Saebi 
 * @author Maks Cegielski-Johnson
 * @author William Guss 
 * 
 * Description: A collection structure based of a doubly linked list, similar to the LinkedList method by Java 
 * 
 * @param <E> - the type of elements contained in the linked list
 */
public class MyLinkedList<E> implements List<E> 
{
	//Instance variables
	int size;
	Node head = new Node(null);
	Node tail = new Node(null);
	

	/**
	 * Constructor.  Creates a blank linked list.
	 */
	public MyLinkedList() 
	{
		size = 0;
		head.next = tail;
		tail.prev = head;

		head.prev = null; //asserts that nothing is before head
		tail.next = null; //asserts that nothing is after tail
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
		n.next = head.next;
		head.next.prev = n;
		n.prev = head;
		head.next = n;
		size++;
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
		n.prev = tail.prev;
		n.next = tail;
		tail.prev.next = n;
		tail.prev = n;
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range.
	 * O(N) for a doubly-linked list.
	 */
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException();
		int count;
		Node temp;
		if(index > size/2){
			count = size;
			temp = tail;
			while(--count >= index)
				temp = temp.prev;
		}
		else{
			count = -1;
			temp = head;
			while(++count <= index){
				temp = temp.next;
			}
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
	public E getFirst() throws NoSuchElementException 
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
	public E getLast() throws NoSuchElementException 
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
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		int count;
		Node temp;
		//Occurs if index is towards the end of the list
		if(index > size/2){
			count = size;
			temp = tail;
			while(--count >= index)
				temp = temp.prev;
			return temp.data;
		}
		
		//This occurs if index <= size/2
		count = -1;
		temp = head;
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
	public E removeFirst() throws NoSuchElementException
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
	public E removeLast() throws NoSuchElementException
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
	public E remove(int index) throws IndexOutOfBoundsException
	{		
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		Node temp;
		int count;
		if(index > size/2){
			count = size;
			temp = tail;
			while(--count >= index)
				temp = temp.prev;
		}
		else{
			count = -1;
			temp = head;
			while(++count <= index){
				temp = temp.next;
			}
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
		Node temp = head.next;
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
		Node temp = head.next;
		int index = 0;
		while(temp.next != null){
			if(temp.data.equals(element))
				return index;
			index++;
			temp = temp.next;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	public int lastIndexOf(E element) 
	{		
		Node temp = tail.prev;
		int index = size-1; // Since we're looking for the last item, start from the back
		while(index >= 0){
			if(temp.data.equals(element))
				return index;
			temp = temp.prev;
			index--;
		}
		return -1;
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
		head.next = tail;
		tail.prev = head;
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
		while(i<size){
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
