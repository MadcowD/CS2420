package assignment8;

import junit.framework.TestCase;

public class HashTableTester extends TestCase {
	
	private HashFunctor FUNCTOR = new GoodHashFunctor();

	private ProbingHashTable probe = new ProbingHashTable(11, FUNCTOR);
	
	//TODO add tests for addAll and containsAll
	
	
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
		assertEquals(probe.getTableSize(), 11);
		probe.add("one");
		probe.add("two");
		probe.add("three");
		probe.add("four");
		probe.add("five");
		assertTrue(probe.getLambda() < .5);
		//Check if the next lambda is >.5 or not.
		probe.add("six");
		assertTrue(probe.getLambda() > .5);
		//now should grow on next add
		probe.add("seven");
		assertTrue(probe.getLambda() < .5);
		assertEquals(probe.getTableSize(), 23); //2*11 is 22, next prime is 23

	}
}
