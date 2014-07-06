package assignment7;

import assignment7.AlgorithmTimer.TimeComplexity;
import assignment7.experiments.BFSExperiment;
import assignment7.experiments.DFSExperiment;
import assignment7.experiments.DijkExperiment;
import assignment7.experiments.TopoExperiment;

/**
 * Contains all of the experiments
 * @author Jesus Hernandez
 * @author William Guss
 */
public class GraphUtilTimer {
	public static void main (String[] args) {
		TimeComplexity[] complexities = new TimeComplexity[] {TimeComplexity.AVERAGE};
		int start = 100;
		int end = 1000;
		
		new AlgorithmTimer("DFSD",  	 new DFSExperiment(true),20).generateAnalysis(start, end, complexities);
		new AlgorithmTimer("DFS",  	 new DFSExperiment(false),200).generateAnalysis(10, 1000, complexities);
		new AlgorithmTimer("BFSD",  	 new BFSExperiment(true),20).generateAnalysis(start, end, complexities);
		new AlgorithmTimer("BFS",  	 new BFSExperiment(false),200).generateAnalysis(10, 1000, complexities);
		new AlgorithmTimer("TopD", 	new TopoExperiment(true),20).generateAnalysis(start, end, complexities);
		new AlgorithmTimer("Top",  	  new TopoExperiment(true),20).generateAnalysis(10, 1000, complexities);
		new AlgorithmTimer("DijD",  	  new DijkExperiment(true),75).generateAnalysis(start, end, complexities);
		new AlgorithmTimer("Dij",    	new DijkExperiment(true),200).generateAnalysis(10, 1000, complexities);
		
	}
}
