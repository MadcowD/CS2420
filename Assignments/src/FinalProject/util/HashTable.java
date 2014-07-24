package FinalProject.util;


/**
 * An abstract class facilitating the implementation of a concrete hash table. 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class HashTable<T>
{
	protected int size;
	protected int tableSize;
	protected double lambda;
	private int collisions = 0;
	
	
	/**
	 * Returns the current lambda, useful for testing.
	 */
	public final double getLambda(){
		return lambda;
	}
	
	/**
	 * Returns the current table size, useful for testing.
	 */
	public final int getTableSize(){
		return tableSize;
	}

	/**
	 * If the HashTable is empty returns true, else returns false
	 */
	public final boolean isEmpty() 
	{
		return size == 0;
	}

	/**
	 * Returns the quantity of items in the HashTable
	 */
	public final int size() 
	{
		return size;
	}
	
	
	/**
	 * Helper method used to generate the next prime integer
	 * @param n - the lower bound, we want a prime larger than this
	 * @return - the next prime
	 */
	protected final static int nextPrime(int n){
		int p;
		if(n % 2 == 0)//Enable us to use the for-loop correctly in case of an even number
			n++;
		
		/*
		 * The upper bound is set by Bertrand's postulate,
		 * which states that if n > 3 is an integer,
		 * then there will always exist at least 
		 * one prime such that n < p < 2n - 2
		 */
		for(p = n; p < 2*n+2; p+=2){
			if(isPrime(p))
				return p;
		}

		return -1;//This should never happen, if it does we can detect an error
	}
	
	/**
	 * Helper method used to check if an integer is prime, returns True if the integer is prime, false otherwise.
	 */
	protected final static boolean isPrime(int n){
		if(n % 2 == 0)
			return false;
		//If p is a composite integer, then p has a divisor less than or equal to the sqrt(p)
		for(int i = 3; i<=Math.sqrt(n); i+=2){
			if(n%i == 0)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Increase the collision count by one.
	 */
	protected void incCollisions(){
		this.collisions++;
	}
	
	/**
	 * Clears the collision variable
	 */
	protected void clearCollisions(){
		this.collisions = 0;
	}
	
	/**
	 * Returns the current amount of collisions and clears the variable
	 *
	 */
	public int getCollisions() {
		int result = this.collisions;
		this.collisions = 0;
		return result;
	}

	private LinkedList<T>[] storage;//used to store all the data
	private HashFunction functor;
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor for a ChainingHashTable, creating an array of a prime size capacity (or next largest prime) of 
	 * Linked lists and using a given functor.
	 * @param capacity - a prime number, if the value is not prime then the next largest prime is used instead
	 * @param functor - a HashFunctor, used to hash the values into the table
	 */
	public HashTable(int capacity, HashFunction hasher) {
		if(!isPrime(capacity))
			capacity = nextPrime(capacity);
		
		//We would hope to assert that in the general case our table size maintains a primality directly equivalant to that of
		//the capacity specified. Therefore we define table size directly as the capacity.
		this.storage = (LinkedList<T>[]) new LinkedList[capacity];
		this.tableSize = capacity;
		this.size = 0;
		this.functor =hasher;
		this.lambda = 0;
	}
	
	public boolean add(T item) {
		if(lambda >= 0.5)
			this.rehash();
		
		int hashValue = functor.<T>hash(item) % tableSize;
		
		//Add a new list if non exists.
		if(storage[hashValue] == null){
			storage[hashValue] = new LinkedList<T>();
			storage[hashValue].addFirst(item);
			this.size++;
			this.lambda = (lambda*tableSize+1)/tableSize;
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
	
	
	public boolean contains(T item) {
		int hashValue = functor.<T>hash(item) % tableSize;
		if(storage[hashValue] == null)
			return false;
		else
			return storage[hashValue].contains(item);
	}
	
	/**
	 * Returns the array representation of the Hash Table
	 */
	public LinkedList<T>[] toArray() {
		return storage;
		
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Helper method used to rehash a table if lambda gets too large
	 */
	private void rehash() {
		int prime = nextPrime(this.tableSize*2);
		
		LinkedList<T>[] temp = this.storage;
		this.storage =  (LinkedList<T>[]) new LinkedList[prime];
		
		this.lambda = 0;
		this.tableSize = prime;
		this.size = 0;
		
		for(LinkedList<T> l : temp)
			if(l != null)
				for(T item : l)
					this.add(item);
	
	}
	
	@SuppressWarnings("unchecked")
	public void reset () {
		tableSize = 11;
		this.storage = (LinkedList<T>[]) new LinkedList[tableSize];
		this.size = 0;
		this.lambda = 0;
		
	}
}
