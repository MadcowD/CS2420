package assignment7.experiments;

import assignment7.AlgorithmTimer.TimeComplexity;
import assignment7.Graph;
import assignment7.GraphUtil;
import assignment7.Process;

/**
 * @author Jesus Hernandez
 * @author William Guss
 *
 */
public class BFSExperiment extends Process {
	private Graph data;
	private boolean densityGrowth;
	private boolean acyclic = false;
	private boolean weighted = true;
	
	
	/**
	 * Instantiates the experiment with a given type of data generation.
	 * This accounts for both possible cases of complexity growth (density and vertex cardinality).
	 */
	public BFSExperiment(boolean densityGrowth){
		this.densityGrowth = densityGrowth;
	}

	/**
	 * Generates data based on the given densityGrowth,.
	 */
	@Override
	public void generateData (int n, TimeComplexity complexity) {
		
		//If we are accounting for density growth we note that using 10 vertices, we shall increase the scalar density of edges.
		if(densityGrowth)
			 GraphUtil.generateGraphInDotFile("timing.dot", 10, n, true, acyclic, weighted);
		
		//Counter case to the prior.
		else
			 GraphUtil.generateGraphInDotFile("timing.dot", n, 2, true, acyclic, weighted);
		
		
		data = GraphUtil.buildGraphFromDotFile("timing.dot");
	}
	
	/**
	 * Runs the breadth first search experiment.
	 */
	@Override
	public void run (int n, TimeComplexity complexity) {
		int size = data.getVertices().size();
		try{
		GraphUtil.breadthFirstSearch(data, "v"+0, "v"+(size-1));
		} catch(Exception e){}
	}

}
