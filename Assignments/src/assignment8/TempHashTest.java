package assignment8;

import java.util.Arrays;

public class TempHashTest {


	public static void main(String[] args){

		int hashType = 1; //0 for probing, 1 for chaining

		if(hashType == 0){
			ProbingHashTable h = new ProbingHashTable(11, new FairHashFunctor());
			h.add("one");
			print(h.lambda);
			h.add("two");
			print(h.lambda);
			h.add("for");
			h.add("nor");
			h.add("wow");
			h.add("sweet");
			print(h.lambda);
			print(Arrays.toString(h.toArray()));
			h.add("big");
			h.add("ok");
			h.add("a");
			print(h.lambda);
			print(Arrays.toString(h.toArray()));
		}
		else if(hashType == 1){
			ChainingHashTable h = new ChainingHashTable(11, new FairHashFunctor());
			h.add("one");
			h.add("two");
			h.add("four");
			h.add("three");
			h.add("five");
			h.add("six");
			h.add("seven");
			h.add("eight");
			h.add("nine");
			h.add("ten");
			h.add("eleven");
			h.add("twelve");
			h.add("thirteen");
			h.add("fourteen");
			h.add("fifteen");
			h.add("sixteen");
			h.add("seventeen");
			h.add("eighteen");
//			h.add("nineteen");//These push lambda over limit
//			h.add("twenty");
			
			for(int i = 0; i < 100; i++)
				h.add(HashTable.generateAlphanumericString());
			print(Arrays.toString(h.toArray()));
			print(h.getLambda());
			print(2*h.tableSize-h.tableSize/2.5);
		}
		ChainingHashTable good = new ChainingHashTable(100, new FairHashFunctor());
		ChainingHashTable fair = new ChainingHashTable(100, new GoodHashFunctor());
		int cA = 0;
		for(int i = 0; i <  1000000; i++){
			String lolz = HashTable.generateAlphanumericString();
			  good.add(lolz);
			  fair.add(lolz);
		}
		System.out.println(good.getCollisions());
		System.out.println(fair.getCollisions());
	


	}

	/**
	 * Lazy print statement
	 * @param d - AnyType that want printed
	 */
	public static <T> void print(T d){
		System.out.println(d);
	}
}
