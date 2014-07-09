package assignment7;

import java.util.LinkedList;

import junit.framework.TestCase;

/**
 * Testing class for the Graph object. Tests primarily exceptions and corner cases, 
 * but also checks performance on normal graphs that wouldn't throw any errors.
 * 
 * 
 * @author Maks Cegielski-Johnson
 * @author Choun Votha 
 *
 */
public class GraphUtilTester extends TestCase {

	LinkedList<String> list;
	LinkedList<String> compare = new LinkedList<String>();


	/**
	 * Tests BFS on an empty graph with wrong vertex names.
	 */
	public void testEmptyGraphWrongNamesBreadth(){
		Graph empty = GraphUtil.buildGraphFromDotFile("empty.dot");
		try{
			list = (LinkedList<String>)GraphUtil.breadthFirstSearch(empty, "A", "G");
		}catch(UnsupportedOperationException e){
			assertTrue(true);
			return;
		}
		fail("Error not caught");
	}

	/**
	 * Tests DFS on an empty graph with wrong vertex names.
	 */
	public void testEmptyGraphWrongNamesDepth(){
		Graph empty = GraphUtil.buildGraphFromDotFile("empty.dot");
		try{
			list = (LinkedList<String>)GraphUtil.depthFirstSearch(empty, "A", "G");
		}catch(UnsupportedOperationException e){
			assertTrue(true);
			return;
		}
		fail("Error not caught");
	}

	/**
	 * Tests Dijkstra's on a normal graph with wrong vertex names
	 */
	public void testEmptyGraphWrongNamesDijk(){
		Graph empty = GraphUtil.buildGraphFromDotFile("weight.dot");
		try{
			list = (LinkedList<String>)GraphUtil.dijkstrasShortestPath(empty, "X", "W");
		}catch(UnsupportedOperationException e){
			assertTrue(true);
			return;
		}
		fail("Error not caught");
	}

	/**
	 * Tests topological sort on an empty graph
	 */
	public void testEmptyGraph(){
		Graph empty = GraphUtil.buildGraphFromDotFile("empty.dot");
		list = (LinkedList<String>)GraphUtil.topologicalSort(empty);

		assertEquals(list.size(), 0);

	}
	
	/**
	 * Tests all 4 algorithms on graphs with one item or for searching for an item that is the start
	 */
	public void singleItem(){
		Graph single = GraphUtil.buildGraphFromDotFile("single.dot");
		compare.add("A");
		

		list = (LinkedList<String>)GraphUtil.topologicalSort(single);
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		list = (LinkedList<String>)GraphUtil.breadthFirstSearch(single, "A", "A");
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		list = (LinkedList<String>)GraphUtil.depthFirstSearch(single, "A", "A");
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		list = (LinkedList<String>)GraphUtil.dijkstrasShortestPath(single, "A", "A");
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		
		single = GraphUtil.buildGraphFromDotFile("weight.dot");
		
		list = (LinkedList<String>)GraphUtil.breadthFirstSearch(single, "A", "A");
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		list = (LinkedList<String>)GraphUtil.depthFirstSearch(single, "A", "A");
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		list = (LinkedList<String>)GraphUtil.dijkstrasShortestPath(single, "A", "A");
		assertEquals(list.get(0),compare.get(0));
		assertTrue(list.size() == compare.size());
		
	}
	
	/**
	 * Testing the 3 path finding algorithms on graphs that have no path to the goal
	 */
	public void testNoPath(){
		Graph nopath = GraphUtil.buildGraphFromDotFile("nopath.dot");
		
		list = (LinkedList<String>)GraphUtil.breadthFirstSearch(nopath, "A", "E");
		assertEquals(list.size(), 0);
		
		list = (LinkedList<String>)GraphUtil.depthFirstSearch(nopath, "A", "E");
		assertEquals(list.size(), 0);
		
		list = (LinkedList<String>) GraphUtil.dijkstrasShortestPath(nopath, "A", "E");
		System.out.println(list.toString());
		assertEquals(list.size(), 0);

	}
	/**
	 * Testing exceptions for unweighted Dijkstra's.
	 */
	public void testUnweightedDijkstras(){
		Graph dsfg = GraphUtil.buildGraphFromDotFile("dfsg.dot");
		try{
			list = (LinkedList<String>) GraphUtil.dijkstrasShortestPath(dsfg, "A", "G");
		}
		catch(UnsupportedOperationException e){
			assertTrue(true);
			return;
		}
		fail("Error not caught");
	}

	/**
	 * Testing a normal case DFS algorithm with one explicit path to the goal
	 */
	public void testDepthFirstSearchNormal() {
		Graph dsfg = GraphUtil.buildGraphFromDotFile("dfsg.dot");
		list = (LinkedList<String>)GraphUtil.depthFirstSearch(dsfg, "A", "G");//A L K J I G
		compare.add("A");
		compare.add("L");
		compare.add("K");
		compare.add("J");
		compare.add("I");
		compare.add("G");

		for(int i = 0; i < compare.size(); i++)
			assertEquals(list.get(i), compare.get(i));
		assertTrue(list.size() == compare.size());
	}

	/**
	 *Testing a normal case DFS algorithm with one explicit path to the goal
	 */
	public void testBreadthFirstSearchNormal() {
		Graph bsfg = GraphUtil.buildGraphFromDotFile("dfsg.dot");
		list = (LinkedList<String>) GraphUtil.breadthFirstSearch(bsfg, "A", "G"); //A L K J I G
		compare.add("A");
		compare.add("L");
		compare.add("K");
		compare.add("J");
		compare.add("I");
		compare.add("G");

		for(int i = 0; i < compare.size(); i++)
			assertEquals(list.get(i), compare.get(i));
		assertTrue(list.size() == compare.size());
	}

	/**
	 * Testing a normal case Dijkstra's algorithm.
	 */
	public void testDijkstrasShortestPathNormal() {
		Graph weight = GraphUtil.buildGraphFromDotFile("weight.dot");
		list = (LinkedList<String>) GraphUtil.dijkstrasShortestPath(weight, "A", "C"); //A B E D C
		compare.add("A");
		compare.add("B");
		compare.add("E");
		compare.add("D");
		compare.add("C");

		for(int i = 0; i<compare.size(); i++)
			assertEquals(list.get(i), compare.get(i));
		assertTrue(list.size() == compare.size());
	}

	/**
	 * Testing Exception catching on Dijkstra's for a negative edge
	 */
	public void testDijkstrasShortestPathNegative(){
		Graph negative = GraphUtil.buildGraphFromDotFile("negativeweight.dot");

		try{
			list = (LinkedList<String>) GraphUtil.dijkstrasShortestPath(negative, "A", "C");
		}catch(UnsupportedOperationException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing Topological Sort on a normal graph
	 */
	public void testTopologicalSortNormal() {
		Graph top = GraphUtil.buildGraphFromDotFile("topsort.dot"); 
		list = (LinkedList<String>)GraphUtil.topologicalSort(top);// A E B C D G H F J I
		compare.add("A");
		compare.add("E");
		compare.add("B");
		compare.add("C");
		compare.add("D");
		compare.add("G");
		compare.add("H");
		compare.add("F");
		compare.add("J");
		compare.add("I");

		for(int i = 0; i<compare.size(); i++)
			assertEquals(list.get(i), compare.get(i));
		assertTrue(list.size() == compare.size());
	}

	/**
	 * Testing Exception throwing for the Topological Sort on a Cyclic graph.
	 */
	public void testTopologicalSortException(){
		Graph cyclic = GraphUtil.buildGraphFromDotFile("cyclic.dot");
		try{
			list = (LinkedList<String>)GraphUtil.topologicalSort(cyclic);
		}catch(UnsupportedOperationException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

}
