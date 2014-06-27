package assignment6;

import assignment6.AlgorithmTimer.TimeComplexity;

public class BSTtimer {

	public static void main(String[] args){
		long r = 0x666420666L;
		
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
		buildExp.generateAnalysis(1, 100000,complexities);
		bAddJava.generateAnalysis(1, 100000,complexities);
		bAddCust.generateAnalysis(1, 100000,complexities);
		bRemJava.generateAnalysis(1, 100000,complexities);
		bRemCust.generateAnalysis(1, 100000,complexities);
		bContJava.generateAnalysis(1, 100000,complexities);
		bContCust.generateAnalysis(1, 100000,complexities);
		
		
	}
	
}
