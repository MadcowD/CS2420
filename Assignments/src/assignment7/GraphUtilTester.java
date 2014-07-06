package assignment7;

import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

/**
 * @author Jesus Hernandez
 * @author William Guss
 * Tester class
 */
public class GraphUtilTester extends TestCase {

	Random r;
	protected void setUp () throws Exception {
		super.setUp();
		r = new Random();

	}
	
	
	/**
	 * Depth first
	 */
	public void testDepthFirstSearch () {
		Graph v = makeGraph();
		List<String> a = GraphUtil.depthFirstSearch(v, "v0", "v4");
		//Verify.
		assertEquals(a.size(), 4);
		assertEquals(a.get(0), "v0");
		assertEquals(a.get(1), "v2");
		assertEquals(a.get(2), "v7");
		assertEquals(a.get(3), "v4");

		//Test unsupported exceptions
		v =new Graph();
		v.getVertices().put("asd", new Vertex("asd"));
		boolean threw = false;
		try{
			GraphUtil.depthFirstSearch(v, "v0", "v4");
		}
		catch(Exception e){
			threw = true;
		}
		assertTrue(threw);
	}

	/**
	 * Tests breadth first search.
	 */
	public void testBreadthFirstSearch () {
		Graph v = makeGraph();
		List<String> a = GraphUtil.breadthFirstSearch(v, "v0", "v4");
		
		//Verify.
		assertEquals(a.size(), 3);
		assertEquals(a.get(0), "v0");
		assertEquals(a.get(1), "v7");
		assertEquals(a.get(2), "v4");

		//Test unsupported exceptions
		v =new Graph();
		v.getVertices().put("asd", new Vertex("asd"));
		boolean threw = false;
		try{
			GraphUtil.breadthFirstSearch(v, "v0", "v4");
		}
		catch(Exception e){
			threw = true;
		}
		assertTrue(threw);
	}

	/**
	 * Tests dijkstras
	 */
	public void testDijkstrasShortestPath () {
		Graph v = makeGraph();
		List<String> a = GraphUtil.dijkstrasShortestPath(v, "v0", "v4");
		//Verify.
		assertEquals(a.size(), 3);
		assertEquals(a.get(0), "v0");
		assertEquals(a.get(1), "v7");
		assertEquals(a.get(2), "v4");

		//Test unsupported exceptions
		v =new Graph();
		v.getVertices().put("asd", new Vertex("asd"));
		v.setWeighted(false);
		
		boolean threw = false;
		try{
			GraphUtil.dijkstrasShortestPath(v, "v0", "v4");
		}
		catch(Exception e){
			threw = true;
		}
		assertTrue(threw);
	}

	/**
	 * Tests a sort of the topological kind.
	 */
	public void testTopologicalSort () {
		
		Graph v = makeAcyclicGraph();
		List<String> a = GraphUtil.topologicalSort(v);
		System.out.println(a);
		//Verify.
		assertEquals(a.size(), 7);
		assertEquals(a.get(0), "v0");
		assertEquals(a.get(1), "v2");
		assertEquals(a.get(2), "v1");
		assertEquals(a.get(3), "v3");
		assertEquals(a.get(4), "v4");
		assertEquals(a.get(5), "v5");
		assertEquals(a.get(6), "v6");

		//Test unsupported exceptions
		//nameley cyclic
		v = makeGraph();
		boolean threw = false;
		try{
			GraphUtil.topologicalSort(v);
		}
		catch(Exception e){
			threw = true;
		}
		assertTrue(threw);
	}

	/**
	 * Makes a cyclic graph
	 * @return
	 */
	public Graph makeGraph(){
		return GraphUtil.buildGraphFromString("digraph G {\n\tv6->v9 [label=60]\n\tv5->v0 [label=55]\n\tv10->v9 [label=29]\n\tv4->v7 [label=25]\n\tv10->v1 [label=94]\n\tv5->v7 [label=82]\n\tv0->v1 [label=47]\n\tv0->v10 [label=88]\n\tv5->v7 [label=14]\n\tv0->v7 [label=1]\n\tv3->v0 [label=97]\n\tv0->v9 [label=55]\n\tv3->v1 [label=49]\n\tv9->v1 [label=79]\n\tv3->v9 [label=78]\n\tv1->v0 [label=106]\n\tv6->v7 [label=84]\n\tv1->v5 [label=109]\n\tv8->v3 [label=58]\n\tv1->v2 [label=36]\n\tv4->v9 [label=62]\n\tv9->v6 [label=108]\n\tv7->v0 [label=87]\n\tv6->v4 [label=70]\n\tv2->v7 [label=63]\n\tv9->v6 [label=77]\n\tv10->v5 [label=2]\n\tv0->v2 [label=81]\n\tv10->v1 [label=72]\n\tv7->v4 [label=31]\n\tv9->v0 [label=55]\n\tv0->v2 [label=100]\n\tv1->v0 [label=56]\n}\n");
	}
	
	/**
	 * makes an acyclic graph
	 * @return
	 */
	public Graph makeAcyclicGraph(){
		return GraphUtil.buildGraphFromString("digraph G {\n\tv4->v6 [label=31]\n\tv3->v5 [label=19]\n\tv4->v5 [label=4]\n\tv2->v3 [label=20]\n\tv2->v5 [label=19]\n\tv4->v6 [label=43]\n\tv1->v5 [label=47]\n\tv1->v6 [label=45]\n\tv3->v6 [label=48]\n\tv2->v4 [label=12]\n\tv0->v6 [label=11]\n\tv1->v3 [label=49]\n\tv3->v4 [label=64]\n\tv1->v5 [label=31]\n\tv0->v4 [label=59]\n\tv5->v6 [label=18]\n\tv0->v2 [label=15]\n\tv0->v1 [label=41]\n\tv1->v4 [label=15]\n\tv1->v6 [label=42]\n\tv0->v3 [label=7]\n}\n");
	}
}
