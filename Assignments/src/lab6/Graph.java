package lab6;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Represents a directed graph (a set of vertices and a set of edges).
 * 
 * Dijkstra's shortest-path algorithm is included.
 */
public class Graph
{
	// the graph underlying structure -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<String, Vertex> vertices; 
	private boolean isDirected, isWeighted;

	/**
	 * Constructs an empty graph.
	 */
	public Graph()
	{
		vertices = new HashMap<String, Vertex>();
		isDirected = false;
		isWeighted = false;
	}
	
	/**
	 * Set the graph's directed-ness indicator
	 */
	public void setDirected(boolean directed)
	{
		this.isDirected = directed;
	}
	
	/**
	 * Set the graph weighted-ness indicator
	 */
	public void setWeighted(boolean weighted)
	{
		this.isWeighted = weighted;
	}
	
	/**
	 * get the graphs list of vertices
	 * @return 
	 */
	public Collection<Vertex> getVertices(boolean weighted)
	{
		return this.vertices.values();
	}
	
	/**
	 * Adds to the graph an edge from the vertex with name "name1" to the vertex with name "name2". The edge is associated with the "weight". If either vertex does not already
	 * exist in the graph, it is added.
	 */
	public void addEdgeW(String name1, String name2, int weight)
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
	}

	/**
	 * Adds to the graph an edge from the vertex with name "name1" to the vertex with name "name2". The edge is associated with the "weight". If either vertex does not already
	 * exist in the graph, it is added.
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
	}

	public class Vertex 
	{
		private String name; 
		private Vertex cameFrom;
		private boolean isVisited;
		private LinkedList<Edge> adj; 

		public Vertex(String _name)
		{
			this.name = _name;
			this.cameFrom = null;
			this.isVisited = false;
			this.adj = new LinkedList<Edge>();
		}

		public void addEdge(Vertex otherVertex)
		{
			adj.add(new Edge(otherVertex));
		}
		
		public void addEdgeWeighted(Vertex otherVertex, int weight)
		{
			adj.add(new Edge(otherVertex, weight));
		}

		public Iterator<Edge> edges()
		{
			return adj.iterator();
		}		
		
		public LinkedList<Edge> getEdges()
		{
			return this.adj;
		}
		
		public String getName()
		{
			return name;
		}

		public void setVisited(boolean status)
		{
			this.isVisited = status;
		}

		public boolean getVisited()
		{
			return this.isVisited;
		}		

		public void setCameFrom(Vertex prev)
		{
			this.cameFrom = prev;
		}

		public Vertex getCameFrom()
		{
			return this.cameFrom;
		}

		public String toString()
		{
			String s = "Vertex " + name + " adjacent to ";
			
			Iterator<Edge> itr = adj.iterator();
			
			while (itr.hasNext())
				s += itr.next() + "  ";
			
			return s;
		}

		public class Edge
		{
			private Vertex otherEnd; // 2nd vertex in Edge
			private int weight;

			public Edge(Vertex _other)
			{
				this.otherEnd = _other;
			}
			
			public Edge(Vertex _other, int _weight)
			{
				this.weight = _weight;
				this.otherEnd = _other;
			}			

			public Vertex getOtherVertex()
			{
				return otherEnd;
			}

			public String toString()
			{
				return otherEnd.getName();
			}
		}
	}
}
