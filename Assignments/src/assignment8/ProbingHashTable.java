package assignment8;

/**
 * Data Structure implementation of a HashTable using Quadratic probing. Has methods for adding and checking if an item
 * is contained in the table. 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 */
public class ProbingHashTable extends HashTable {
	
	private String[] table;//used to store all the data

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


	public void clear() {
		//Set everything to null
		for(int i = 0; i<this.table.length; i++)
			this.table[i] = null;
		this.size = 0;

	}



	public boolean contains(String item) {
		final int HASH = functor.hash(item) % this.tableSize;//don't want HASH to change for this item
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
	 * Returns the array representation of the Hash Table
	 */
	public String[] toArray(){
		return this.table;
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
				return true;
			}
			
			if(table[temp].equals(item))//check if the table contains item already
				return false;
			
			//Else:
			temp = hashVal + i*i;//Next spot using quadratic probing
			this.incCollisions();
			//Loop around
			if(temp > table.length)
				temp %= table.length;
			
			i++;//Increment quadratic probe
		}
		
	}
	
	/**
	 * Helper method used to rehash a table if lambda gets too large
	 */
	private void rehash(){
		int prime = this.tableSize * 2;
		prime = nextPrime(prime);//create a prime number atleast twice as large as the size of the table
		
		this.clearCollisions();
		
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
