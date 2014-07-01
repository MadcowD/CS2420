package assignment6;

import assignment6.AlgorithmTimer.TimeComplexity;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 *	Creates, holds, and runs all our experiments in one class. Printing out the values to console and .txt file. 
 */
public class BSTtimer {

	public static void main(String[] args){
		long r = 0x666420666L; //our random seed
		
		//CREATE OUR EXPERIMENTS
		AlgorithmTimer buildExp = new AlgorithmTimer("custBSTBUILD", new BuildExperiment(), 1000);
		
		AlgorithmTimer bAddJava = new AlgorithmTimer("bAddJava",
				new  BalanceAddExperiment().new JavaExperiment(), 1000);
		AlgorithmTimer bAddCust = new AlgorithmTimer("bAddCust",
				new  BalanceAddExperiment().new BSTExperiment(), 1000);
		
		AlgorithmTimer bRemJava = new AlgorithmTimer("bRemJava",
				new  BalanceRemoveExperiment().new JavaRemove(), 1000);
		AlgorithmTimer bRemCust = new AlgorithmTimer("bRemCust",
				new  BalanceRemoveExperiment().new BSTRemove(), 1000);
		
		AlgorithmTimer bContJava = new AlgorithmTimer("bContJava",
				new  BalanceContainsExperiment().new JavaContains(), 1000);
		AlgorithmTimer bContCust = new AlgorithmTimer("bContCust",
				new  BalanceContainsExperiment().new BSTContains(), 1000);

		TimeComplexity[] complexities = new TimeComplexity[] {TimeComplexity.BEST, TimeComplexity.WORST};
		
		//RUN THOSE EXPERIMENTS
		buildExp.generateAnalysis(1, 100000,complexities);//BST Build Experiment
		bAddJava.generateAnalysis(1, 100000,complexities);//TreeSet add() experiment
		bAddCust.generateAnalysis(1, 100000,complexities);//BST add() experiment
		bRemJava.generateAnalysis(1, 100000,complexities);//TreeSet remove() experiment
		bRemCust.generateAnalysis(1, 100000,complexities);//BST remove() experiment
		bContJava.generateAnalysis(1, 100000,complexities);//TreeSet contains() experiment
		bContCust.generateAnalysis(1, 100000,complexities);//BST contains() experiment
		
		
	}
	
}
