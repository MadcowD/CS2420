package assignment8;

import junit.framework.TestCase;

public class HashTableTester extends TestCase {
	
	private static HashFunctor FUNCTOR = new GoodHashFunctor();

	private ProbingHashTable probe = new ProbingHashTable(11, FUNCTOR);
	
	
	public void testContainsProbe(){
		probe.add("test");
		assertTrue(probe.contains("test"));
		
		assertTrue(!probe.contains("empty"));
	}
	
	public void testAddProbe(){
		assertTrue(probe.add("first"));
		assertTrue(probe.add("second"));
		assertEquals(probe.size(), 2);
		assertTrue(!probe.add("first"));
		assertEquals(probe.size(), 2);
	}
	
	public void testAddProbeGrow(){
		//Add 6 items
		
		probe.add("one");
		probe.add("two");
		probe.add("three");
		probe.add("four");
		probe.add("five");
		//Check if the next lambda is >.5 or not.
		
		//TODO then check if size grows correctly
		
		fail("not done");
	}
}
