package assignment8;

import java.util.LinkedList;

public class ChainingHashTable extends HashTable {
	private LinkedList<String>[] storage; 
	
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		this.functor = functor;
		
		if(!isPrime(capacity))
			capacity = nextPrime(capacity);
		
		//We would hope to assert that in the general case our table size maintains a primality directly equivalant to that of
		// the capacity specified. Therefore we define table size directly as the capacity.
		this.tableSize = capacity;
		
		storage = (LinkedList<String>[]) new LinkedList[capacity];
	}
	
	@Override
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

	@SuppressWarnings("unchecked")
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

	@Override
	public void clear() {
		for(int i = 0; i < tableSize; i++)
			if(storage[i] != null)
				storage[i].clear();
		size = 0;
		lambda = 0;
		
	}

	@Override
	public boolean contains(String item) {
		int hashValue = functor.hash(item) % tableSize;
		if(storage[hashValue] == null)
			return false;
		else
			return storage[hashValue].contains(item);
	}

	
	public LinkedList<String>[] toArray() {
		return storage;
		
	}
}
