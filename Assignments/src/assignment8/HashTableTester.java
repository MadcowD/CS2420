package assignment8;

import java.util.ArrayList;

import junit.framework.TestCase;


/**
 * Test Class for HashTable, ProbingHashTable, and ChainingHashTable
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class HashTableTester extends TestCase {
	
	private HashFunctor FUNCTOR = new GoodHashFunctor();//Hashing using our good functor

	private ProbingHashTable probe = new ProbingHashTable(11, FUNCTOR);//the ProbingHashTable
	private ChainingHashTable chain = new ChainingHashTable(11, FUNCTOR);//the ChainingHashTable
	
	
	/**
	 * Tests the contains method for ProbingHashTable.
	 */
	public void testContainsProbe(){
		probe.add("test");
		assertTrue(probe.contains("test"));
		assertTrue(!probe.contains("empty"));
	}
	
	/**
	 * Tests the add method for ProbingHashTable.
	 */
	public void testAddProbe(){
		assertTrue(probe.add("first"));
		assertTrue(probe.add("second"));
		assertEquals(probe.size(), 2);
		assertTrue(!probe.add("first"));
		assertEquals(probe.size(), 2);
	}
	
	/**
	 * Tests growing of the array in ProbingHashTable. 
	 */
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
	/**
	 * Tests the size method in ProbingHashTable. 
	 */
	public void testProbeSize(){
		probe.add("one");
		assertEquals(probe.size(), 1);
		probe.add("two");
		assertEquals(probe.size(), 2);
		probe.add("three");
		assertEquals(probe.size(), 3);
		probe.add("one");
		assertEquals(probe.size(), 3);
		probe.add("four");
		probe.add("five");
		probe.add("six");
		assertEquals(probe.size(), 6);
		probe.add("seven");
		assertEquals(probe.size(), 7);
		probe.add("eight");
		assertEquals(probe.size(), 8);	
	}
	
	/**
	 * Tests the clear method in ProbingHashTable. 
	 */
	public void testProbeClear(){
		assertEquals(probe.size(), 0);
		probe.add("something");
		probe.add("nice");
		assertEquals(probe.size(), 2);
		assertTrue(probe.contains("something"));
		assertTrue(probe.contains("nice"));
		probe.clear();
		assertEquals(probe.size(), 0);
		assertTrue(!probe.contains("something"));
		assertTrue(!probe.contains("nice"));
	}
	
	/**
	 * Tests the addAll method for ProbingHashTable. 
	 */
	public void testProbeAddAll(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i<20; i++)
			list.add("" + i);
		
		probe.addAll(list);
		
		for(int i = 0; i<20; i++)
			assertTrue(probe.contains("" + i));
		
		assertTrue(!probe.contains("20"));
		
	}
	
	/**
	 * Tests the containsAll method for ProbingHashTable. 
	 */
	public void testProbeContainsAll(){
		ArrayList<String> evens = new ArrayList<String>();
		ArrayList<String> notList = new ArrayList<String>();
		for(int i = 0; i<=20; i++){
			probe.add("" + i);//add everything [0,20]
			if(i%2 == 0)
				evens.add("" + i);//adds evens
			notList.add("" + (i + 21));//adds items out of the range
		}
		
		assertTrue(probe.containsAll(evens));
		assertTrue(!probe.containsAll(notList));
	}
	
	
	///////////////////////////TESTS FOR CHAINING////////////////////////////
	/**
	 * Tests the contains method for ChainingHashTable.
	 */
	public void testContainsChain(){
		chain.add("first");
		assertTrue(chain.contains("first"));
		assertTrue(!chain.contains("empty"));
	}

	/**
	 * Tests the add method for ChainingHashTable.
	 */
	public void testAddChain(){
		assertTrue(chain.add("first"));
		assertTrue(chain.add("second"));
		assertEquals(chain.size(), 2);
		assertTrue(!chain.add("first"));
		assertEquals(chain.size(), 2);
	}
	
	/**
	 * Tests the growth of the array in the ChainingHashTable.
	 */
	public void testAddChainGrow(){
		assertEquals(chain.getTableSize() ,11);
		chain.add("one");
		chain.add("two");
		chain.add("three");
		chain.add("four");
		chain.add("five");
		assertTrue(chain.getLambda() < .5);
		chain.add("six");
		assertTrue(chain.getLambda() > .5);
		chain.add("seven");
		assertTrue(chain.getLambda() < .5);
		assertEquals(chain.getTableSize(), 23);
	}
	/**
	 * Tests the size method for ChainingHashTable.
	 */
	public void testSizeChain(){
		chain.add("one");
		assertEquals(chain.size(), 1);
		chain.add("two");
		assertEquals(chain.size(), 2);
		chain.add("three");
		assertEquals(chain.size(), 3);
		chain.add("one");
		assertEquals(chain.size(), 3);
		chain.add("four");
		chain.add("five");
		chain.add("six");
		assertEquals(chain.size(), 6);
		chain.add("seven");
		assertEquals(chain.size(), 7);
		chain.add("eight");
		assertEquals(chain.size(), 8);
	}
	
	/**
	 * Tests the clear method for ChainingHashTable.
	 */
	public void testChainingClear(){
		assertEquals(chain.size(), 0);
		chain.add("something");
		chain.add("nice");
		assertEquals(chain.size(), 2);
		assertTrue(chain.contains("something"));
		assertTrue(chain.contains("nice"));
		chain.clear();
		assertEquals(chain.size(), 0);
		assertTrue(!chain.contains("something"));
		assertTrue(!chain.contains("nice"));
	}
	
	/**
	 * Tests the addAll method for ChainingHashTable.
	 */
	public void testChainingAddAll(){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i<20; i++)
			list.add("" + i);
		
		chain.addAll(list);
		
		for(int i = 0; i<20; i++)
			assertTrue(chain.contains("" + i));
		
		assertTrue(!chain.contains("20"));
		
	}
	/**
	 * Tests the containsAll method for ChainingHashTable.
	 */
	public void testChainingContainsAll(){
		ArrayList<String> evens = new ArrayList<String>();
		ArrayList<String> notList = new ArrayList<String>();
		for(int i = 0; i<=20; i++){
			chain.add("" + i);//add everything [0,20]
			if(i%2 == 0)
				evens.add("" + i);//adds evens
			notList.add("" + (i + 21));//adds items out of the range
		}
		
		assertTrue(chain.containsAll(evens));
		assertTrue(!chain.containsAll(notList));
	}
	
	/**
	 * Very hopeful test for our two prime methods. Only checks basic functionality.
	 * Not assumed to work perfectly as the primes approach large values, seeing as primes are infinite
	 * and that calculating if something is prime will begin to take much longer when there are more values to check
	 * using our method.
	 */
	public void foolishPrimeTester(){
		assertTrue(HashTable.isPrime(11));
		assertTrue(HashTable.isPrime(101));
		assertTrue(!HashTable.isPrime(12));
		
		int prime = 11;
		assertEquals(HashTable.nextPrime(prime), 13);
		prime = 97;
		assertEquals(HashTable.nextPrime(prime), 101);
	}
	
	
	

}
