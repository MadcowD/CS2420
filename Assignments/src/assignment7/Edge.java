package assignment7;

/**
 * Represents a directed edge for graph vertices.
 * 
 * @author Paymon Saebi
 */
public class Edge
{
	/**
	 * The vertex being pointed to by this edge, providing a way to reference it
	 */
	private Vertex otherEnd; 
	private int weight;

	/**
	 * Constructs an edge with one vertex at one end of it
	 * 
	 * @param _other - the vertex being pointed to by this edge
	 */
	public Edge(Vertex _other)
	{
		this.otherEnd = _other;
		this.weight = 0;
	}
	
	/**
	 * Constructs an edge with one vertex at one end of it and associates a weight with it
	 * 
	 * @param _other - the vertex being pointed to by this edge
	 * @param _weight - the cost associated with encountering this
	 */
	public Edge(Vertex _other, int _weight)
	{
		this.weight = _weight;
		this.otherEnd = _other;
	}			

	/**
	 * Getter method for the vertex being pointed to by this edge
	 * 
	 * @return the vertex being point to by this edge
	 */
	public Vertex getOtherVertex()
	{
		return otherEnd;
	}
	
	/**
	 * getter method for the cost associated with encountering this edge
	 * 
	 * @return the weight asscosuated with this edge
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * Generates a verbose string with the weight of this edge and name of the vertex's pointed to by it
	 * 
	 * @return the string representing this edge
	 */
	@Override
	public String toString()
	{
		return "Esge with weight " + this.weight + " connected to " + otherEnd.getName();
	}
}