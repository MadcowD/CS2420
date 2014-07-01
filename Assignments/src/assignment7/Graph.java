package assignment7;

import java.util.HashMap;

/**
 * Represents a graph structure (a set of vertices each with a set of edges).
 * 
 * @author Paymon Saebi
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
