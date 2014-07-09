package assignment7;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 * Utility class containing methods for operating on graphs.
 * 
 * Depth-first-search routine - to find a path between two vertices in a graph
 * Breadth-first-search routine - to find the shortest path between two vertices in a graph
 * Dijkstra's cheapest path routine - to find the cheapest path between two vertices in a graph
 * Topological sort - to produce a topologically sorted list of all vertices in a graph
 * Generating random graphs routine - to generate parameterized random graph for testing
 * Building graphs from file routine - to create and build a graph object from a valid dot file  
 * 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author Choun Votha 
 */
public class GraphUtil
{
	/**
	 * Performs a depth-first search of a graph to determine a path from a start vertex to an goal vertex. 
	 * (See Lecture 18 for the algorithm.)
	 *
	 * @param graph - The graph object to be traversed
	 * @param start - Name of the starting vertex in the path
	 * @param goal  - Name of the ending vertex in the path
	 * @return a list of the vertices that make up a path path from the vertex with the name startName (inclusive)
	 *         to the ending vertex with the name goalName (inclusive) 
	 * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName 
	 */
	public static List<String> depthFirstSearch(Graph graph, String startName, String goalName)
	{				
		HashMap<String,Vertex> vertices = graph.getVertices(); // get all the vertices

		if(!vertices.containsKey(startName) || !vertices.containsKey(goalName))
			throw new UnsupportedOperationException("The graph does not contain either the starting vertex or the goal vertex");

		List<String> list = new LinkedList<String>(); // temp to store cameFroms

		//Recurive Step
		dfsRecursive(graph, vertices, startName, goalName);

		//Final Step:
		if(vertices.get(goalName).getCameFrom() == null)
			return list;

		list.add(vertices.get(goalName).getName());//add the goal to cameFrom
		Vertex temp = vertices.get(goalName);//set a temp Vertex as the goal
		while(!temp.equals(vertices.get(startName))){//While the temp is not equal to the start
			list.add(0, temp.getCameFrom().getName());//add them in reverse order to get the travel path
			temp = temp.getCameFrom();//switch the temp pointer
		}

		return list;
	}

	/**
	 * The recursive method for depthFirstSearch. Stores the path in the cameFrom value for every Vertex.
	 * @param graph - The graph that we are traversing
	 * @param vertices - A map of all the vertices in the graph
	 * @param start - the current vertex the algorithm is on
	 * @param goal - the goal vertex
	 */
	private static void dfsRecursive(Graph graph, HashMap<String, Vertex> vertices, String start, String goal){
		vertices.get(start).setVisited(true);//set the current vertex to visited

		if(vertices.get(start).equals(vertices.get(goal))){//return if we have reached the goal
			return;
		}

		LinkedList<Edge> neighbors = vertices.get(start).getEdges();//Get all the neighbors of the current vertex
		for(Edge e : neighbors)//For every adjacent edge
			if(!e.getOtherVertex().getVisited()){//if the vertex has not been visited
				e.getOtherVertex().setCameFrom(vertices.get(start)); //set the cameFrom to the current vertex
				e.getOtherVertex().setVisited(true);//set visited
				//Recurse again
				dfsRecursive(graph, vertices, e.getOtherVertex().getName(), goal);
			}
	}

	/**
	 * Performs a breadth-first search on a graph to determine the shortest path from a start vertex to an goal vertex. 
	 * (See Lecture 18 for the algorithm.)
	 * 
	 * @param graph - The graph object to be traversed
	 * @param start - Name of the starting vertex in the path
	 * @param goal  - Name of the ending vertex in the path
	 * @return a list of the vertices that make up the shortest path from the vertex with the name startName (inclusive)
	 *         to the ending vertex with the name goalName (inclusive)
	 * @throws UnsupportedOperationException if there are no vertices in the graph with the names startName or goalName 
	 */
	public static List<String> breadthFirstSearch(Graph graph, String start, String goal)
	{		
		HashMap<String,Vertex> vertices = graph.getVertices(); // get all the vertices
		if(!vertices.containsKey(start) || !vertices.containsKey(goal)){
			//			System.out.println("Goal: " + goal + "  " + vertices.containsKey(goal));
			throw new UnsupportedOperationException("The graph does not contain either the starting vertex or the goal vertex");

		}
		LinkedList<Vertex> queue = new LinkedList<Vertex>();//The queue
		vertices.get(start).setVisited(true);//Set the beginning to visited
		queue.addFirst(vertices.get(start));//Enqueue the start


		//Main process:
		while(!queue.isEmpty()) { //While the queue is not empty, loop:
			Vertex temp = queue.pop();//Dequeue to a temp
			if(temp.equals(vertices.get(goal)))//If the temp is the goal we are done.
				break;
			LinkedList<Edge> edges = temp.getEdges();//List of all the adjacent edges
			for(Edge e: edges)
				if(!e.getOtherVertex().getVisited()){
					e.getOtherVertex().setVisited(true);
					e.getOtherVertex().setCameFrom(temp);
					queue.addLast(e.getOtherVertex());//enqueue the adjacent vertex
				}
		}

		//Set up the result
		List<String> list = new LinkedList<String>();//the list we are returning

		if(vertices.get(goal).getCameFrom() == null)
			return list;

		list.add(vertices.get(goal).getName());//add the goal to cameFrom
		Vertex temp = vertices.get(goal);//set a temp Vertex as the goal
		while(!temp.equals(vertices.get(start))){//While the temp !equal the start
			list.add(0, temp.getCameFrom().getName());//add them in reverse order to get the travel path
			temp = temp.getCameFrom();//switch the temp pointer
		}
		return list;
	}

	/**
	 * Performs Dijkstra's routine on a weighted graph to determine the cheapest path from start vertex to a goal vertex.
	 * (See Lecture 19 for the algorithm.)
	 * 
	 * Uses Java's PriorityQueue class to find the "unvisited vertex with smallest distance from start". 
	 * See the API for PriorityQueue, and ask the course staff if you need help.
	 * 
	 * @param graph - The graph object to be traversed
	 * @param start - Name of the starting vertex in the path
	 * @param goal  - Name of the ending vertex in the path
	 * @return a list of the vertices that make up the cheapest path from the starting vertex (inclusive) to the 
	 *         ending vertex (inclusive) based on weight associated with the edges between the graphs vertices
	 * @throws UnsupportedOperationException if the graph is not weighted, or there are no vertices in the graph
	 *         with the names startName or goalName 
	 */
	public static List<String> dijkstrasShortestPath(Graph graph, String startName, String goalName)
	{
		if(!graph.getWeighted())
			throw new UnsupportedOperationException("The graph is not weighted");



		//Set up:
		final int INFINITY = Integer.MAX_VALUE;//The infinity value

		HashMap<String,Vertex> vertices = graph.getVertices(); // get all the vertices

		if(!vertices.containsKey(startName) || !vertices.containsKey(goalName))
			throw new UnsupportedOperationException("The graph does not contain either the starting vertex or the goal vertex");

		LinkedList<Vertex> iterateVertex = (LinkedList<Vertex>) graph.listVertices();//List of the vertices that we can iterate through

		//Iterate through every vertex
		for(Vertex v : iterateVertex){
			v.setCostFromStart(INFINITY);
			for(Edge e : v.getEdges())//for every edge
				if(e.getWeight() < 0)
					throw new UnsupportedOperationException("Negative weight");//We don't want a negative weight for Dijkstra's
		}
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();//The priority queue
		vertices.get(startName).setCostFromStart(0);//Set start cost to 0
		queue.add(vertices.get(startName));//Enqueue the start

		//Main process
		while(!queue.isEmpty()){
			Vertex temp = queue.poll();//Dequeue the first item
			if(temp.equals(vertices.get(goalName)))//We found the goal, so break;
				break;
			temp.setVisited(true);

			for(Edge e : temp.getEdges()){//for every adjacent edge
				if(e.getOtherVertex().getCostFromStart() > temp.getCostFromStart() + e.getWeight()){//If the cost of start of the other vertex is greater than the current cost of start and weight of the vertex
					queue.add(e.getOtherVertex());//enqueue the other vertex
					e.getOtherVertex().setCameFrom(temp);//change the came from
					e.getOtherVertex().setCostFromStart(temp.getCostFromStart() + e.getWeight());//change the cost from start to the new cost
				}
			}
		}

		//Prepare result
		List<String> list = new LinkedList<String>();




		Vertex temp = vertices.get(goalName);//set a temp Vertex as the goal
		if(temp.getCameFrom() == null)
			return list;
		list.add(temp.getName());//add the goal to cameFrom
		while(!temp.equals(vertices.get(startName))){//While the temp !equal the start
			list.add(0, temp.getCameFrom().getName());//add them in reverse order to get the travel path
			temp = temp.getCameFrom();//switch the temp pointer
		}
		return list;
	}

	/**
	 * Performs a topological sort of the vertices in a directed acyclic graph. 
	 * (See Lecture 19 for the algorithm.)
	 * 
	 * @param graph - The graph object to be traversed
	 * @return a list of the vertex names in topologically sorted order
	 * @throws UnsupportedOperationException if the graph is undirected, or it is cyclic.
	 */
	public static List<String> topologicalSort(Graph graph)
	{
		if(!graph.getDirected())
			throw new UnsupportedOperationException("Graph is undirected");

		int counter = 0;//To determine whether the graph is cyclic
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		LinkedList<Vertex> vertices = new LinkedList<Vertex>();
		List<String> result = new LinkedList<String>(); //ASK A TA ABOUT RETURNING A LIST VS ARRAYLIST VS LINKEDLIST TODO
		vertices = (LinkedList<Vertex>) graph.listVertices();//Get a list of every vertex
		for(Vertex v : vertices)
			if(v.getInDegree() == 0)//Enqueue every vertex with an in degree of zero
				queue.addFirst(v);


		//Main process
		while(!queue.isEmpty()){
			Vertex v = queue.poll();
			result.add(v.getName());//Add the vertex to the result
			counter++;//Checks for cycles

			for(Edge e : v.getEdges()){
				e.getOtherVertex().decInDegree();
				if(e.getOtherVertex().getInDegree() == 0){
					queue.add(e.getOtherVertex());
				}
			}
		}

		if(counter != graph.getVertices().size())
			throw new UnsupportedOperationException("This graph contains a cycle");

		return result;
	}

	public static Vertex[] returnArray(){
		return vertex;
	}

	private static Vertex[] vertex;

	/**
	 * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b"). 
	 * Accepts directed ("digraph") or undirected ("graph") graphs.
	 * 
	 * Accepts many valid DOT files (see examples posted with assignment). 
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ;\ 
	 * --accepts label attributes (e.g., [label = "a label"]) for weights
	 * 
	 * @param filename - name of the DOT file
	 */
	public static void generateGraphInDotFile(String filename, int vertexCount, int edgeDensity, boolean directed, boolean acyclic, boolean weighted)
	{
		PrintWriter out = null;

		try
		{
			out = new PrintWriter(filename);
		} 
		catch (Exception e)
		{
			System.out.print("Unable to utilize the graph .dot file name: ");
			System.err.println(e.getMessage());
		}

		//		Vertex[] 
		vertex = new Vertex[vertexCount];
		Random rng = new Random();

		String edgeOp = "--";

		if (directed)
		{
			out.print("di");
			edgeOp = "->";
		}

		out.println("graph G {");

		for (int i = 0; i < vertexCount; i++)
			vertex[i] = new Vertex("v" + i);

		int rand1 = rng.nextInt(vertexCount);
		int rand2 = rng.nextInt(vertexCount);

		if (acyclic)
			for (int i = 0; i < edgeDensity * vertexCount; i++)
			{
				rand1 = rng.nextInt(vertexCount);
				rand2 = rng.nextInt(vertexCount);
				int x = 2;


				for(Edge e : vertex[rand1].getEdges())
					while(e.getOtherVertex().equals(vertex[rand2]))
						rand2 = rng.nextInt(vertexCount);

				while (rand2 <= rand1)
				{
					rand1 = rng.nextInt(vertexCount);
					rand2 = rng.nextInt(vertexCount);

					for(Edge e : vertex[rand1].getEdges())
						while(e.getOtherVertex().equals(vertex[rand2]))
							rand2 = rng.nextInt(vertexCount);
					if(rand2 <= rand1)
						rand2 = rng.nextInt(vertexCount);
				}				 

				vertex[rand1].addEdge(vertex[rand2]);

				out.print("\t" + vertex[rand1].getName() + edgeOp + vertex[rand2].getName());

				if (weighted)
					out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

				out.print("\n");
				
				
//				for(int in = 0; in < vertexCount; in++){
//					if(vertex[in].getEdges().size() == 0){
//						do{
//							
//							rand1 = in + rng.nextInt(vertexCount - in + 1);
//
//							System.out.println("loop");
//						}while(vertex[in].equals(vertex[rand1]));
//
//						vertex[in].addEdge(vertex[rand1]);
//						
//						out.print("\t" + vertex[in].getName() + edgeOp + vertex[rand1].getName());
//
//						if (weighted)
//							out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");
//
//						out.print("\n");
//					}
//				}

				for(Vertex v : vertex){
					if(v.getEdges().size() == 0){

						do{
							rand1 = rng.nextInt(vertexCount);
						}
						while(v.equals(vertex[rand1]));
						vertex[rand1].addEdge(v);



						out.print("\t" + vertex[rand1].getName() + edgeOp + v.getName());

						if (weighted)
							out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

						out.print("\n");
					}
				}

			}
		else
			for (int i = 0; i < edgeDensity * vertexCount; i++) 
			{
				rand1 = rng.nextInt(vertexCount);
				rand2 = rng.nextInt(vertexCount);

				while (rand2 == rand1)
				{
					rand2 = rng.nextInt(vertexCount);
				}
				out.print("\t" + vertex[rand1].getName() + edgeOp + vertex[rand2].getName());

				if (weighted)
					out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

				out.print("\n");


				for(Vertex v : vertex){
					if(v.getEdges().size() == 0){

						do{
							rand1 = rng.nextInt(vertexCount);
						}
						while(v.equals(vertex[rand1]));
						v.addEdge(vertex[rand1]);



						out.print("\t" + v.getName() + edgeOp + vertex[rand1].getName());

						if (weighted)
							out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

						out.print("\n");
					}
				}
			}

		out.println("}");
		out.close();
	}

	/**
	 * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b"). 
	 * Accepts directed ("digraph") or undirected ("graph") graphs.
	 * 
	 * Accepts many valid DOT files (see examples posted with assignment). 
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 */
	public static Graph buildGraphFromDotFile(String filename)
	{
		Graph g = new Graph();

		Scanner s = null;
		try
		{
			s = new Scanner(new File(filename)).useDelimiter(";|\n");
		} 
		catch (Exception e)
		{
			System.out.print("Unable to utilize the graph .dot file: ");
			System.err.println(e.getMessage());
		}

		// Determine if graph is directed or not (i.e., look for "digraph id {" or "graph id {")
		String line = "", edgeOp = "";

		while (s.hasNext())
		{
			line = s.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0)
			{
				g.setDirected(true); 
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
			if (line.indexOf("graph") >= 0)
			{
				g.setDirected(false);
				edgeOp = "--";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}

		line = s.next();
		boolean weighted = line.contains("label");

		if (weighted)
			g.setWeighted(true);

		// Look for edge operators -- (or ->) and determine the left and right vertices for each edge.
		while (s.hasNext())
		{
			String[] substring2 = null;
			String[] substring = line.split(edgeOp);

			if (weighted)
			{
				substring2 = line.split(" ");
				substring = substring2[0].split(edgeOp);
			}

			for (int i = 0; i < substring.length - 1; i += 2)
			{
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				if (vertex1.equals(""))
					continue;

				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals(""))
					continue;

				if (weighted)
				{
					String[] substring3 = substring2[1].split("=");
					int weight = Integer.parseInt(substring3[1].replace("]", "").trim());
					g.addEdgeWeighted(vertex1, vertex2, weight);
				} else
					g.addEdge(vertex1, vertex2);
			}

			if (substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = s.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		return g;
	}
}