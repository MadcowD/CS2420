package assignment7Maks;

import java.util.Random;

public class GraphTimer {

	public static void main(String[] args){



		final int DIJKSTRA = 1;
		final int BREADTH = 2;
		final int DEPTH = 3;
		final int TOP = 4;
		boolean cyclic = false;
		boolean acyclic = true;
		int[] sizes = new int[3];
		
		
		//EDIT:
		int timesToLoop = 10;
		int initial = 100;
		int limit = 500;
		int jump = 100;
		////////////////////

		
		sizes[0] = initial;
		sizes[1] = limit;
		sizes[2] = jump;
		
		
		System.out.println("\n \n DIJKSTRA \n");
		runExperiments(DIJKSTRA, timesToLoop, acyclic, sizes);
		System.out.println("\n \n BREADTH \n");
		runExperiments(BREADTH, timesToLoop, acyclic, sizes);
		System.out.println("\n \n DEPTH \n");
		runExperiments(DEPTH, timesToLoop, acyclic, sizes);
		System.out.println("\n \n TOPOLOGICAL \n");
//		runExperiments(TOP, timesToLoop, acyclic, sizes);
		
		

	}

	public static void runExperiments(int cases, int timesToLoop, boolean cycle, int[] args){
		long startTime;
		long endTime;
		Random rng = new Random();
		int rand1;
		int rand2;
		String start;
		String end;

		for(int size = args[0]; size <= args[1] ; size += args[2]){
			GraphUtil.generateGraphInDotFile("timing1.dot", size, 1, true, cycle, true);
			long sum = 0;
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1E9){}
			for(int t = 0; t < timesToLoop; t++){
				Graph graph = GraphUtil.buildGraphFromDotFile("timing1.dot");

				switch(cases){
				case 1:
					rand1 = rng.nextInt(size);
					rand2 = rng.nextInt(size);
					start = "v" + rand1;
					end = "v" + rand2;
					startTime = System.nanoTime();
					GraphUtil.dijkstrasShortestPath(graph, start, end);
					endTime = System.nanoTime();
					sum += (endTime - startTime);
					break;
				case 2:
					rand1 = rng.nextInt(size);
					rand2 = rng.nextInt(size);
					start = "v" + rand1;
					end = "v" + rand2;
					startTime = System.nanoTime();
					GraphUtil.breadthFirstSearch(graph, start, end);
					endTime = System.nanoTime();
					sum += (endTime - startTime);
					break;
				case 3:
					rand1 = rng.nextInt(size);
					rand2 = rng.nextInt(size);
					start = "v" + rand1;
					end = "v" + rand2;
					startTime = System.nanoTime();
					GraphUtil.depthFirstSearch(graph, start, end);
					endTime = System.nanoTime();
					sum += (endTime - startTime);
					break;
				case 4:
					startTime = System.nanoTime();
					GraphUtil.topologicalSort(graph);
					endTime = System.nanoTime();
					sum += (endTime - startTime);
					break;

				}
			}
			sum /= timesToLoop;
			System.out.println(size + "\t" + sum);
		}
	}

}
