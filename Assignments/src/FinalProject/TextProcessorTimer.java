package FinalProject;

import FinalProject.timing.AlgorithmTimer;
import FinalProject.timing.AlgorithmTimer.TimeComplexity;
import FinalProject.timing.Process;

public class TextProcessorTimer {
	public static void main(String[] args){
		int sampleSize = 10;
		final boolean verbose = false;
		TextProcessor.initializeComponents("wordstats1.txt");
		
		new AlgorithmTimer("5a", new Process(){
			String a = "";
			public void generateData(int n, TimeComplexity complexity) {
				a="";
				for(int i =0; i < n; i++)
					a+= "a";
			};
			
			@Override
			public long run(int n, TimeComplexity complexity) {
				TextProcessor.spellcheckWord(a, verbose);
				return 0;
			}
			
		}, 	sampleSize).generateAnalysis(1, 1000,new TimeComplexity[] {TimeComplexity.BEST});
	}

}
