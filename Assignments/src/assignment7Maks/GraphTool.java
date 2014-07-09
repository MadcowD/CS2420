package assignment7;

/**
 * Graph tools command-line user interface. 
 * 
 * @author Paymon Saebi
 */
public class GraphTool
{
	/**
	 * The main is responsible for checking the range of the user arguments length
	 * It also catches and handles exception while running user requested operations
	 * 
	 * @param args - user command arguments
	 */
	public static void main(String[] args)
	{
		if ((args.length < 2) || (args.length > 7))
		{
			System.out.println("Incorrect number of graph ops command arguments!");
			return;
		}

		try
		{
			run_graph_ops(args);
		} 
		catch (Exception e)
		{
			System.err.println(e.getMessage() + "\n");
		}
	}

	/**
	 * Responsible for checking user arguments and making sure the user command is valid
	 * According to the user's command arguments runs the appropriate graph operation
	 * 
	 * Accepts the following command parameters together:
	 * 
	 * (filename), (-TLS), 
	 * (filename), (-DFS), (start vertex name), (goal vertex name)
	 * (filename), (-BFS), (start vertex name), (goal vertex name)
	 * (filename), (-DCP), (start vertex name), (goal vertex name)
	 * (filename), (-GRG), (-d or -ud), (-c or -ac), (-w or -uw), (number of vertices), (edge density) 
	 * 
	 * @param args - user command arguments
	 */
	public static void run_graph_ops(String[] args)
	{
		if ((args.length == 2) && (args[1].equals("-TLS")))
		{
			Graph TLSgraph = GraphUtil.buildGraphFromDotFile(args[0]);
			System.out.println("Topological sort list: " + 
								GraphUtil.topologicalSort(TLSgraph) + "\n");
		}
		else if ((args.length == 4) && (args[1].equals("-DFS")))
		{
			Graph DFSgraph = GraphUtil.buildGraphFromDotFile(args[0]);
			System.out.println("Depth first search path: " + 
								GraphUtil.depthFirstSearch(DFSgraph, args[2], args[3]) + "\n");
		} 
		else if ((args.length == 4) && (args[1].equals("-BFS")))
		{
			Graph BFSgraph = GraphUtil.buildGraphFromDotFile(args[0]);
			System.out.println("Breath first search path: " + 
								GraphUtil.breadthFirstSearch(BFSgraph, args[2], args[3]) + "\n");
		} 
		else if ((args.length == 4) && (args[1].equals("-DCP")))
		{
			Graph DSPgraph = GraphUtil.buildGraphFromDotFile(args[0]);
			System.out.println("Dijkstra's cheapest path: " + 
								GraphUtil.dijkstrasShortestPath(DSPgraph, args[2], args[3]) + "\n");
		}		 
		else if ((args.length == 7) && (args[1].equals("-GRG")))
		{
			if (!args[0].substring((args[0].length() - 4), args[0].length()).equals(".dot"))
			{
				System.out.println("Invalid graph generator .dot filename argument!\n");
				return;
			}

			int vertices = 0;
			int density = 0;
			
			try
			{
				vertices = Integer.parseInt(args[5]);
				density = Integer.parseInt(args[6]);
			}
			catch(Exception e)
			{
				System.out.println("Invalid graph generator number of vertices argument!\n");
				return;
			}

			boolean valid = true;
			boolean directed = true;
			boolean cyclic = true;
			boolean weighted = true;

			if (args[2].equals("-ud"))
				directed = false;
			else if (!args[2].equals("-d"))
				valid = false;

			if (args[3].equals("-c"))
				cyclic = false;
			else if (!args[3].equals("-ac"))
				valid = false;

			if (args[4].equals("-uw"))
				weighted = false;
			else if (!args[4].equals("-w"))
				valid = false;

			if (valid)
			{
				GraphUtil.generateGraphInDotFile(args[0], vertices, density, directed, cyclic, weighted);
				System.out.println("Succesfull random graph generation with " + vertices + " vertices.\n");
				return;
			} 
			else
			{
				System.out.println("Invalid options for random graph generator arguments!\n");
				return;
			}
		}
		else
		{
			System.out.println("Invalid graph oprtation request argument: " + args[1] + "\n");
			return;
		}
	}
}
