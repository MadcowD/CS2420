package assignment9;

import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

/**
 * This class represents a communication infrastructure for a remote device
 * It generates a fixed graph with randomized weights and then it find the cheapest path
 *
 * @author Paymon saebi
 */
public class DeviceManager
{
	private Graph network;

	/**
	 * This method re-initializes the network for every new command
	 * It emulates external delays and reports the arbitrary timing
	 */
	public void sendCommand(String command) 
	{
		initializeNetwork();
		int delay = 0, time = 0;
	
		List<String> path = network.dijkstrasShortestPath("control", "device");
		System.out.println("\nStablishing a connection, please wait ...");

		for(int i = 0; i < path.size() - 1; i++)
		{
			for(Edge e : network.getVertices().get(path.get(i)).getEdges())
				if(e.getOtherVertex().getName().equals(path.get(i + 1)))
					delay = e.getWeight();
			
			try //Emulating an external connection delay
			{
				Thread.sleep(100*delay);
			} 
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			
			System.out.println("\tData arrived at " + path.get(i + 1) + " in " + delay + " seconds.");
			
			time += delay;
		}
	
		System.out.println("Command reached after " + time + " seconds! Please enter your next command...");
	}
	
	/**
	 * Creates a fixed graph with randomized weights, and also creates a dot file
	 */
	public void initializeNetwork() 
	{
		PrintWriter out = null;
		Random rng = new Random();
		network = new Graph();
		
		try
		{
			out = new PrintWriter("rover_netwrok.dot");
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}		

		network.setDirected(true);
		network.setWeighted(true);
		out.println("digraph G {");

		for (int i = 1; i < 4; i++)
		{
			out.print("\tcontrol->server" + i);
			int delay = rng.nextInt(10) + 1;
			out.print(" [label=" + delay + "]\n");
			network.addEdgeWeighted("control", "server" + i, delay);
			
			for (int j = 0; j < 3; j++)
			{
				int k = i + j;
				out.print("\tserver" + i + "->antenna" + k);
				delay = rng.nextInt(10) + 10;
				out.print(" [label=" + delay + "]\n");
				network.addEdgeWeighted("server" + i, "antenna" + k, delay);
			}
		}
		
		for (int i = 1; i < 4; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				int k = i + j;
				out.print("\tantenna" + k + "->station" + i);
				int delay = rng.nextInt(10) + 10;
				out.print(" [label=" + delay + "]\n");
				network.addEdgeWeighted("antenna" + k, "station" + i, delay);
			}
			
			out.print("\tstation" + i + "->device");
			int delay = rng.nextInt(10) + 1;
			out.print(" [label=" + delay + "]\n");
			network.addEdgeWeighted("station" + i, "device", delay);			
		}		

		out.println("}");
		out.close();
	}	
}