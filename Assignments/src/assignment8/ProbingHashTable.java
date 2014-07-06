package assignment8;

public class ProbingHashTable extends HashTable {

	public ProbingHashTable(int capacity, HashFunctor functor){
		this.tableSize = capacity;
		this.functor = functor;
		this.lambda = 0;
		this.table = new String[capacity];
	}


	/**
	 * 
	 */
	public boolean add(String item) {
		//Check if we need to rehash
		if(this.lambda > 0.5){
			this.rehash(); 
		}

		//TODO
		if(this.contains(item))//TODO
			return false;

		//Get hash value
		int hashVal = functor.hash(item);
		hashVal %= this.tableSize;

		//Helper insert method
		insert(item, hashVal, this.table);

		this.size++;//increment size
		this.lambda = (double)this.size/this.tableSize;//update lambda
		return true;
	}
	
	/**
	 * Helper method used in adding and rehashing
	 * @param item - item to be added
	 * @param hashVal - the item's hash value
	 * @param table - the table to be inserted to
	 */
	private void insert(String item, int hashVal, String[] table){
		int i = 1;//used for quadratic probing, such that hash + i^2
		int temp = hashVal;//insert the item at temp
		
		//Find a place to insert the item
		while(true){
			//If an empty slot, insert here
			if(table[temp] == null){
				table[temp] = item;
				return;
			}
			
			//Else:
			temp = hashVal + i*i;//Next spot using quadratic probing
			
			//Loop around
			if(temp > table.length)
				temp %= table.length;
			
			i++;//Increment quadratic probe
		}
		
	}

	/**
	 * Clears the Hash Table and resets the size.
	 */
	public void clear() {
		//Set everything to null
		for(int i = 0; i<this.table.length; i++)
			this.table[i] = null;
		this.size = 0;

	}

	@Override
	public boolean contains(String item) {
		int hash = functor.hash(item);
		hash %= table.length;
		int find = hash;
		int i = 1;
		while(table[find] != null){
			if(table[find].equals(item))
				return true;
			find = hash + i*i;
			if(find > this.table.length)
				find %= table.length;
			i++;
		}
		return false;
	}


	/**
	 * Helper method used to rehash a table if lambda > 0.5
	 */
	private void rehash(){
		int prime = this.tableSize;//create a prime number, make sure it is at least the current table size
		//Guarantee a prime twice as large as the current one
		while(prime < 2*this.tableSize)
			prime = this.nextPrime(prime);//continue to update prime
		
		//The replacement array
		String[] tempArr = new String[prime];
		
		//Move every item over to the new array
		for(int i = 0; i<this.tableSize; i++){
			String tempStr = this.table[i];//Set a temp string
			if(tempStr == null)//Don't do anything if the string is null
				continue;
			//Get a new hash value
			int hashVal = functor.hash(tempStr);
			hashVal %= prime;
			insert(tempStr, hashVal, tempArr);//Insert all the items in the correct position
		}
		
		//Set the pointers
		this.table = tempArr;
		this.tableSize = prime;
		
	}

	/**
	 * Helper method used to generate the next prime integer
	 * @param n - the lower bound, we want a prime larger than this
	 * @return - the next prime
	 */
	private int nextPrime(int n){
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

}
