package assignment8;

public class ProbingHashTable extends HashTable {
	
	private String[] table;

	/**
	 * Constructor for a ProbingHashTable, creating an array of a prime size capacity (or next largest prime) and using
	 * a given functor.
	 * @param capacity - a prime number, if the value is not prime then the next largest prime is used instead
	 * @param functor - a HashFunctor, used to hash the values into the table
	 */
	public ProbingHashTable(int capacity, HashFunctor functor){
		if(!isPrime(capacity))
			capacity = nextPrime(capacity);
		
		this.tableSize = capacity;
		this.functor = functor;
		this.lambda = 0;
		this.table = new String[capacity];
	}


	/**
	 * Adds the given string item to the set. Returns true if the item was successfully added, false if the set already contains the item and therefore was not added
	 */
	public boolean add(String item) {
		//Check if we need to rehash
		if(this.lambda > 0.5){
			this.size = 0;//need this for insert function so size is accurate
			this.rehash();
		}
		//Get hash value
		int hashVal = functor.hash(item) % this.tableSize;

		//Helper insert method
		return insert(item, hashVal, this.table);
	}
	
	/**
	 * Helper method used in adding and rehashing
	 * @param item - item to be added
	 * @param hashVal - the item's hash value
	 * @param table - the table to be inserted to
	 */
	private boolean insert(String item, int hashVal, String[] table){
		int i = 1;//used for quadratic probing, such that hash + i^2
		int temp = hashVal;//insert the item at temp
		
		//Find a place to insert the item
		while(true){
			//If an empty slot, insert here
			if(table[temp] == null){
				table[temp] = item;
				this.size++;//increment size
				this.lambda = (double)this.size/this.tableSize;//update lambda
				return;
			}
			
			if(table[temp].equals(item))//check if the table contains item already
				return false;
			
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
	
	/**
	 * Returns the array representation of the Hash Table
	 */
	public String[] toArray(){
		return this.table;
	}

	/**
	 * Contains method for the ProbingHashTable, returns true if the item is found in the table, returns false if not
	 */
	public boolean contains(String item) {
		int final HASH = functor.hash(item) % this.tableSize;//don't want HASH to change for this item
		int find = HASH;//find is the probing value
		int i = 1;
		while(table[find] != null){
			if(table[find].equals(item))
				return true;
			find = HASH + i*i;
			if(find > this.table.length)//loop around
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
			prime = nextPrime(prime);//continue to update prime
		
		//The replacement array
		String[] tempArr = new String[prime];
		
		//Move every item over to the new array
		for(int i = 0; i<this.tableSize; i++){
			String tempStr = this.table[i];//Set a temp string
			if(tempStr != null){
				//Get a new hash value
				int hashVal = functor.hash(tempStr);
				hashVal %= prime;
				insert(tempStr, hashVal, tempArr);//Insert all the items in the correct position
			}
		}
		
		//Set the pointers
		this.table = tempArr;
		this.tableSize = prime;
		
	}

	

}
