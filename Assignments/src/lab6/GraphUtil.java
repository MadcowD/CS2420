package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Utility class containing methods for operating on graphs.
 * The class also provides routines for generating and building graphs.
 * 
 * @author Paymon Saebi
 */
public class GraphUtil
{
	public static void main(String[] args)
	{
		GraphUtil.generateGraphInDotFile("randDAWG.dot", 60, true, true, true);

		Graph myGraph = GraphUtil.buildGraphFromDotFile("randDAWG.dot");
	}

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
	public static void generateGraphInDotFile(String filename, int vertexCount, boolean directed, boolean acyclic, boolean weighted)
	{
		PrintWriter out = null;

		try
		{
			out = new PrintWriter(filename);
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}

		String[] vertex = new String[vertexCount];
		Random rng = new Random();

		String edgeOp = "--";

		if (directed)
		{
			out.print("di");
			edgeOp = "->";
		}

		out.println("graph G {");

		for (int i = 0; i < vertexCount; i++)
			vertex[i] = "v" + i;

		if (acyclic)
		{
			if(!directed)
				return;

			boolean[] goneTo = new boolean[vertexCount];
			for (int i = 0; i < vertexCount-2; i++) // randomly connect the vertices using 2 * |V| edges
			{
				int max = rng.nextInt(vertexCount-i-1);
				
				if(max == 0){
					goneTo[i] = false;
					continue;
				}
				else{goneTo[i] = true;}
				
				for(int j = 1; j < max; j++){
					out.print("\t" + vertex[i] + edgeOp + vertex[i+j]);
				

					if (weighted)
						out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");
				
					out.print("\n");
				}
				
			}

			for(int i = 0; i < goneTo.length; i++){
				if(goneTo[i]){
					for(int j = 0; j < i; j++)
						if(goneTo[j] == false)
						{
								out.print("\t" + vertex[i] + edgeOp + vertex[j]);
	
								if (weighted)
									out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");
								out.print("\n");
							
							
						}
				}
				else if(i!= vertexCount-1){
					out.print("\t" + vertex[i] + edgeOp + vertex[vertexCount-1]);
					
					if (weighted)
						out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");
					out.print("\n");
						
				}
			}
			

		}
		else
		{
			for (int i = 0; i < vertexCount; i++) // randomly connect the vertices using 2 * |V| edges
			{
				out.print("\t" + vertex[i] + edgeOp + vertex[rng.nextInt(vertexCount*10)%vertexCount]);

				if (weighted)
					out.print(" [label=" + rng.nextInt(vertexCount * 10) + "]");

				out.print("\n");
			}
		}

		out.println("}");
		out.close();
	}

	/**
	 * Builds a graph according to the edges specified in the given DOT file (e.g., "a -- b" or "a -> b"). Accepts directed ("digraph") or undirected ("graph") graphs.
	 * 
	 * Accepts many valid DOT files (see examples posted with assignment). --accepts \\-style comments --accepts one edge per line or edges terminated with ; --does not accept
	 * attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 */
	public static Graph buildGraphFromDotFile(String filename)
	{
		// creates a new, empty graph 
		Graph g = new Graph();

		Scanner s = null;
		try
		{
			s = new Scanner(new File(filename)).useDelimiter(";|\n");
		} 
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
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
				g.setDirected(true); // Denotes that graph is directed 
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
			if (line.indexOf("graph") >= 0)
			{
				g.setDirected(false); // Denotes that graph is undirected 
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

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals(""))
					continue;

				if (weighted)
				{
					String[] substring3 = substring2[1].split("=");
					int weight = Integer.parseInt(substring3[1].replace("]", "").trim());
					g.addEdgeW(vertex1, vertex2, weight);
				} else
					g.addEdge(vertex1, vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = s.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		return g;
	}
}