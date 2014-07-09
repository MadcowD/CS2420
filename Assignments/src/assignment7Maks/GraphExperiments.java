package assignment7;

import java.util.Arrays;
import java.util.HashMap;

public class GraphExperiments {
	
	
	public static void main(String[] args){
		
	
		
		
		int size = 10;
		GraphUtil.generateGraphInDotFile("testGen.dot", size, 1, true, true, false); //directed acyclic unweighted
		int errorCount = 0;
		Graph testGen = GraphUtil.buildGraphFromDotFile("testGen.dot");
		HashMap<String, Vertex> vertices = testGen.getVertices();
		
		for(int i = 0; i < size; i++){
			String key = "v" + i;
			if(!vertices.containsKey(key)){
				System.out.println("Graph does not contain " + key);
				errorCount++;
			}
			
		}
		
		if(errorCount == 0)
			System.out.println("No errors!");
		else
			System.out.println(Arrays.toString(GraphUtil.returnArray()));
		
		
		
//		GraphUtil.topologicalSort(testGen);
	}


}
