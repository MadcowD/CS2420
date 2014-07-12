package assignment8;

import java.util.LinkedList;

/**
 * Data Structure implementing a HashTable using separate chaining. Has methods for adding and checking if an
 * item is contained in the table.
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 */
public class ChainingHashTable extends HashTable {
	
	private LinkedList<String>[] storage;//used to store all the data
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor for a ChainingHashTable, creating an array of a prime size capacity (or next largest prime) of 
	 * Linked lists and using a given functor.
	 * @param capacity - a prime number, if the value is not prime then the next largest prime is used instead
	 * @param functor - a HashFunctor, used to hash the values into the table
	 */
	public ChainingHashTable(int capacity, HashFunctor functor) {
		if(!isPrime(capacity))
			capacity = nextPrime(capacity);
		
		//We would hope to assert that in the general case our table size maintains a primality directly equivalant to that of
		//the capacity specified. Therefore we define table size directly as the capacity.
		this.storage = (LinkedList<String>[]) new LinkedList[capacity];
		this.tableSize = capacity;
		this.functor = functor;
		this.size = 0;
		this.lambda = 0;
	}
	
	public boolean add(String item) {
		if(lambda >= 0.5)
			this.rehash();
		
		int hashValue = functor.hash(item) % tableSize;
		
		//Add a new list if non exists.
		if(storage[hashValue] == null){
			storage[hashValue] = new LinkedList<String>();
			storage[hashValue].addFirst(item);
			this.size++;
			this.lambda = (double)this.size/(double)this.tableSize;
			return true;
		}
		//Otherwise let's check for uniqueness and then add if uniqueness perserved.
		else
		{
			if(storage[hashValue].contains(item))
				return false;
			else
			{
				this.incCollisions();
				storage[hashValue].addFirst(item);
				this.size++;
				this.lambda = (double)this.size/(double)this.tableSize;
				return true;
			}
		}

	}


	public void clear() {
		for(int i = 0; i < tableSize; i++)
			if(storage[i] != null)
				storage[i].clear();
		this.size = 0;
		this.lambda = 0;
		this.getCollisions();
		
	}


	public boolean contains(String item) {
		int hashValue = functor.hash(item) % tableSize;
		if(storage[hashValue] == null)
			return false;
		else
			return storage[hashValue].contains(item);
	}

	/**
	 * Returns the array representation of the Hash Table
	 */
	public LinkedList<String>[] toArray() {
		return storage;
		
	}
	

	@SuppressWarnings("unchecked")
	/**
	 * Helper method used to rehash a table if lambda gets too large
	 */
	private void rehash() {
		int prime = nextPrime(this.tableSize*2);
		
		LinkedList<String>[] temp = this.storage;
		this.storage =  (LinkedList<String>[]) new LinkedList[prime];
		
		this.lambda = 0;
		this.tableSize = prime;
		this.size = 0;
		
		for(LinkedList<String> l : temp)
			if(l != null)
				for(String item : l)
					this.add(item);

	}
}
