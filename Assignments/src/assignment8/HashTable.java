package assignment8;

import java.util.*;

/**
 * An abstract class facilitating the implementation of a concrete hash table. 
 * 
 * @author Paymon Saebi 
 * @author 
 * @author 
 */
public abstract class HashTable implements Set<String> 
{
	/**
	 * FILL IN MEMBER VARIABLES!
	 * 
	 * Any member variables that you would maintain in both your QuadProbeHashTable and
	 * your ChainingHashTable (such as, say, a size variable) probably belong here in
	 * the parent class. Should the variable(s) be public, private, or protected?
	 */
	protected int size;
	protected int tableSize;
	protected HashFunctor functor;
	protected double lambda;//TODO ARE THESE PROTECTED OR PRIVATE
	
	/**
	 * 
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
	 * 
	 */
	public final boolean containsAll(Collection<? extends String> items) 
	{
		for(String str : items)
			if(!this.contains(str))
				return false;
		return true;
	}
	
	public final double getLambda(){
		return lambda;
	}
	
	public final int getTableSize(){
		return tableSize;
	}

	/**
	 * 
	 */
	public final boolean isEmpty() 
	{
		return size == 0;
	}

	/**
	 * 
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
	public final static int nextPrime(int n){
		boolean isPrime = false; //helper boolean
		//The next prime
		int p;
		
		/*
		 * The upper bound is set by Bertrand's postulate,
		 * which states that if n > 3 is an integer,
		 * then there will always exist at least 
		 * one prime such that n < p < 2n - 2
		 */
		for(p = n+2; p < 2*n; p+=2){
			//If p is a composite integer, then p has a divisor less than or equal to the sqrt(p)
			for(int i = 3; i<=Math.sqrt(p); i+=2){
				if(p%i == 0){
					isPrime = false;
					break;
				}else
					isPrime = true;
			}
			if(isPrime)
				break;
		}
		return p;
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public final static boolean isPrime(int n){
		if(n % 2 == 0)
			return false;
		//If p is a composite integer, then p has a divisor less than or equal to the sqrt(p)
		for(int i = 3; i<=Math.sqrt(n); i+=2){
			if(n%i == 0)
				return false;
		}
		
		return true;
	}
}
