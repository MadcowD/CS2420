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
	protected String[] table;
	protected HashFunctor functor;
	protected double lambda;
	
	/**
	 * 
	 */
	public final boolean addAll(Collection<? extends String> items) 
	{
		//TODO
		return false;
	}

	/**
	 * 
	 */
	public final boolean containsAll(Collection<? extends String> items) 
	{
		//TODO
		return false;
	}
	
	public final double getLambda(){
		return lambda;
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
	
	public String[] toArray(){
		return this.table;
	}
}
