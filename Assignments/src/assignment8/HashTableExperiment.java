package assignment8;

import assignment8.AlgorithmTimer.TimeComplexity;
import assignment8.Process;

/**
 * The Hash Table experiment class, holds the different experiments with the constructor.
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 */
public class HashTableExperiment extends Process{
	private HashTable data;
	private boolean time;

	public HashTableExperiment(HashTable data, boolean time){
		this.data = data;
		this.time = time;
	}
	
	
	@Override
	public void generateData (int n, TimeComplexity complexity) {
		data.reset();
		super.generateData(n, complexity);
	}
	
	@Override
	public long run (int n, TimeComplexity complexity) {
		for(int i = 0; i < n; i++)
			data.add(HashTable.generateRandomString());
		
		if(!time)
			return data.getCollisions();
		else
			return 0;
	}
}
