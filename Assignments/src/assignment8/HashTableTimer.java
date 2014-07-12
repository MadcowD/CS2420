package assignment8;

import assignment8.AlgorithmTimer.TimeComplexity;

/**
 * Our timing class, runs all the experiments from HashTableExperiment using the timing framework.
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 */
public class HashTableTimer {
	public static void main(String[] args){
		TimeComplexity[] complexities = new TimeComplexity[] {TimeComplexity.AVERAGE};
		int averageTime = 50;
		

		//EXPERIMENT 1
		//Test the bad hash functor
		new AlgorithmTimer("hashBadCOL",
			new HashTableExperiment(new ChainingHashTable(11, new BadHashFunctor()), false), 5)
			.generateAnalysis(1, 10000, complexities);
		new AlgorithmTimer("hashBad",
			new HashTableExperiment(new ChainingHashTable(11, new BadHashFunctor()), true), averageTime)
		.generateAnalysis(1, 10000, complexities);

		//Test the mediocre (fair) hash functor
		new AlgorithmTimer("hashFairCOL",
			new HashTableExperiment(new ChainingHashTable(11, new FairHashFunctor()), false), 5)
			.generateAnalysis(1, 10000, complexities);
		new AlgorithmTimer("hashFair",
			new HashTableExperiment(new ChainingHashTable(11, new FairHashFunctor()), true), averageTime)
		.generateAnalysis(1, 10000, complexities);
		
	
		//Test the good hash functor
		new AlgorithmTimer("hashGoodCOL",
			new HashTableExperiment(new ChainingHashTable(11, new GoodHashFunctor()), false), 5)
			.generateAnalysis(1, 10000, complexities);
		new AlgorithmTimer("hashGood",
			new HashTableExperiment(new ChainingHashTable(11, new GoodHashFunctor()), true), averageTime)
		.generateAnalysis(1, 10000, complexities);
		
		
		//EXPERIMENT 2
		//Test the chaining hash table
		new AlgorithmTimer("tableChainingCOL",
			new HashTableExperiment(new ChainingHashTable(11, new GoodHashFunctor()), false), 5)
			.generateAnalysis(1, 10000, complexities);
		new AlgorithmTimer("tableChaining",
			new HashTableExperiment(new ChainingHashTable(11, new GoodHashFunctor()), true), averageTime)
		.generateAnalysis(1, 10000, complexities);
		
		//Run the analysis on the probing hashtable
		new AlgorithmTimer("tableProbingCOL",
			new HashTableExperiment(new ProbingHashTable(11, new GoodHashFunctor()), false), 5)
			.generateAnalysis(1, 10000, complexities);
		new AlgorithmTimer("tableProbing",
			new HashTableExperiment(new ProbingHashTable(11, new GoodHashFunctor()), true), averageTime)
		.generateAnalysis(1, 10000, complexities);
		
		
		
	}
}
