package assignment8;

import java.util.*;

/**
 * An abstract class facilitating the implementation of a concrete hash table. 
 * 
 * @author Paymon Saebi 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public abstract class HashTable implements Set<String> 
{

	protected int size;
	protected int tableSize;
	protected HashFunctor functor;
	protected double lambda;
	private int collisions = 0;
	
	/**
	 * Adds every item from a given Collection to this HashTable, returns true if any item was added to this
	 * table, returns false if no items were added to this table.
	 */
	public final boolean addAll(Collection<? extends String> items) 
	{
		boolean result = false;
		for(String str : items)
			if(this.add(str))
				result = true;
		return result;
	}

	/**
	 * Checks whether this HashTable contains every item in a given collection, returns true if every item
	 * is contained in this table, false if an item is not.
	 */
	public final boolean containsAll(Collection<? extends String> items) 
	{
		for(String str : items)
			if(!this.contains(str))
				return false;
		return true;
	}
	
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

	//Random variable, used for generating random strings
	private static Random r = new Random();
	
	
	/**
	 * Generates a random string for testing and timing.
	 * @param length
	 * @return
	 */
	public static String generateRandomString(int length, int start, int end){
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < length; i++){
			sb.append((char)(r.nextInt(end-start)+start));
		}
		return sb.toString();
	}
	
	/**
	 * Generates a random string for testing and timing
	 * @return
	 */
	public static String generateRandomString(){
		return generateRandomString(r.nextInt(511), 1,255);
	}
	
	/**
	 * Generates a random string of only lower case alphabet characters for timing and testing
	 * @return
	 */
	public static String generateAlphanumericString(){
		return generateRandomString(r.nextInt(20), 97, 122);
		
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
	
	public abstract void reset();
}
