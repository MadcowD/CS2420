package assignment9;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents a graph structure (a set of vertices each with a set of edges).
 * 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class Graph
{
	/**
	 * The graph underlying structure is a HashMap
	 * Holds a set of vertices (String name mapped to Vertex instance) 
	 */

	private HashMap<String, Vertex> vertices; 
	private boolean isDirected, isWeighted;

	/**
	 * Constructs an empty graph.
	 * 
	 * Instantiates a new HashMap structure to hold the graph's vertices
	 */
	public Graph()
	{
		vertices = new HashMap<String, Vertex>();
		isDirected = false;
		isWeighted = false;
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
	public List<String> dijkstrasShortestPath(String startName, String goalName)
	{
		if(!this.getWeighted())
			throw new UnsupportedOperationException("The graph is not weighted");



		//Set up:
		final int INFINITY = Integer.MAX_VALUE;//The infinity value

		HashMap<String,Vertex> vertices = this.getVertices(); // get all the vertices

		if(!vertices.containsKey(startName) || !vertices.containsKey(goalName))
			throw new UnsupportedOperationException("The graph does not contain either the starting vertex or the goal vertex");

		LinkedList<Vertex> iterateVertex = (LinkedList<Vertex>) this.listVertices();//List of the vertices that we can iterate through

		//Iterate through every vertex
		for(Vertex v : iterateVertex){
			v.setCostFromStart(INFINITY);
			for(Edge e : v.getEdges())//for every edge
				if(e.getWeight() < 0)
					throw new UnsupportedOperationException("Negative weight");//We don't want a negative weight for Dijkstra's
		}
		PriorityQueueHEAP<Vertex> queue = new PriorityQueueHEAP<Vertex>();//The priority queue
		vertices.get(startName).setCostFromStart(0);//Set start cost to 0
		queue.add(vertices.get(startName));//Enqueue the start

		//Main process
		while(!queue.isEmpty()){
			Vertex temp = queue.deleteMin();//Dequeue the first item
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
	 * Returns all the vertices of the graph as a linked list. 
	 * @return LinkedList<Vertex> list of the Vertex objects.
	 */
	public List<Vertex> listVertices(){
		List<Vertex> result = new LinkedList<Vertex>();
		Collection<Vertex> val = vertices.values();
		for(Vertex v : val){
			result.add(v);
		}
		return result;

	}

	/**
	 * Set the graph's directed-ness indicator
	 * 
	 * * @param directed - boolean indicating whether the graph will be directed 
	 */
	public void setDirected(boolean directed)
	{
		this.isDirected = directed;
	}

	/**
	 * Get the graph's directed-ness indicator
	 * 
	 * @return true if the graph is directed, false otherwise
	 */
	public boolean getDirected()
	{
		return this.isDirected;
	}	


	/**
	 * Set the graph weighted-ness indicator
	 * 
	 * @param weighted - boolean indicating whether the graph will be weighted 
	 */
	public void setWeighted(boolean weighted)
	{
		this.isWeighted = weighted;
	}

	/**
	 * Get the graph weighted-ness indicator
	 * 
	 * @return true if the graph is weighted, false otherwise
	 */
	public boolean getWeighted()
	{
		return this.isWeighted;
	}

	/**
	 * get the graphs list of vertices
	 * 
	 * @return the HashMap structure holding the graph's vertices 
	 */
	public HashMap<String, Vertex> getVertices()
	{
		return this.vertices;
	}

	/**
	 * Adds to the graph an edge from the vertex with name "name1" to the vertex with name "name2".
	 * If the graph is not directed also adds an edge from vertex with name "name2" to vertex with name "name1".
	 * If either vertex does not already exist in the graph, it is added.
	 * 
	 * @param name1 - name of the first vertex
	 * @param name2 - name of the other vertex
	 */
	public void addEdge(String name1, String name2)
	{
		Vertex vertex1;
		if (vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		else
		{
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
		}

		Vertex vertex2;
		if (vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else
		{
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
		}

		vertex1.addEdge(vertex2);
		if(!isDirected)
			vertex2.addEdge(vertex1);
	}

	/**
	 * Adds to the graph an edge from the vertex with name "name1" to the vertex with name "name2".
	 * If the graph is not directed also adds an edge from vertex with name "name2" to vertex with name "name1". 
	 * TIf either vertex does not already exist in the graph, it is added.
	 * The edge is associated with the "weight".
	 * 
	 * @param name1 - name of the first vertex
	 * @param name2 - name of the other vertex
	 * @param weight - the cost to traverse the edge
	 */
	public void addEdgeWeighted(String name1, String name2, int weight)
	{
		Vertex vertex1;
		if (vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		else
		{
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
		}

		Vertex vertex2;
		if (vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else
		{
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
		}

		vertex1.addEdgeWeighted(vertex2, weight);
		if(!isDirected)
			vertex2.addEdgeWeighted(vertex1, weight);
	}	


}
