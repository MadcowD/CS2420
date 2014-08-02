package FinalProject;

import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import FinalProject.util.ArrayList;
import FinalProject.util.Edge;
import FinalProject.util.Graph;
import FinalProject.util.LinkedList;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Paymon saebi
 * 
 * The device manager class.
 */
public class DeviceManager
{
	private Graph network;

	/**
	 * 
	 */
	public DeviceManager()
	{
		network = new Graph();
	}

	/**
	 * 
	 * @param src
	 */
	public void receiveFile(String src)
	{
		TextProcessor.decompressFile("transfer_" + src, "receive_" + src);
		TextProcessor.spellcheckFile("receive_" + src, "final_" + src);
	}

	/**
	 *
	 */
	public void transmitFile(String src)
	{
		TextProcessor.spellcheckFile(src, "prepare_" + src);
		TextProcessor.compressFile("prepare_" + src, "transfer_" + src);

		initializeNetwork();
		int delay = 0, time = 0;

		LinkedList<String> path = network.dijkstrasShortestPath("control", "device");
		System.out.println("\nStablishing a connection, please wait ...");

		for (int i = 0; i < path.size() - 1; i++)
		{
			for (Edge e : network.getVertices().get(path.get(i)).getEdges())
				if (e.getOtherVertex().getName().equals(path.get(i + 1)))
					delay = e.getWeight();

			try
			{
				Thread.sleep(100 * delay);
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}

			System.out.println("\tData arrived at " + path.get(i + 1) + " in " + delay + " seconds.");

			time += delay;
		}

		System.out.println("\"" + src + "\" reached after " + time + " seconds! Done...");
	}

	/**
	 *
	 */
	public void initializeNetwork()
	{
		PrintWriter out = null;
		Random rng = new Random();

		try
		{
			out = new PrintWriter("rover_netwrok.dot");
		}
		catch (Exception e)
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