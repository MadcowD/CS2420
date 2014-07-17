package assignment9;

import junit.framework.TestCase;

/**
 * @author Maks
 *
 */
public class PriorityQueueTester extends TestCase {

	PriorityQueueHEAP<Integer> heap = new PriorityQueueHEAP<Integer>();
	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#size()}.
	 */
	public void testSizeHeap() {
		assertEquals(heap.size(), 0);
		heap.add(3);
		assertEquals(heap.size(), 1);
		heap.add(4);
		assertEquals(heap.size(), 2);
		heap.add(3);
		assertEquals(heap.size(), 3);
		heap.deleteMin();
		heap.deleteMin();
		heap.deleteMin();
		assertEquals(heap.size(), 0);
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#clear()}.
	 */
	public void testClearHeap() {
		assertEquals(heap.size(), 0);
		for(int i = 0; i<5; i++)
			heap.add(i);
		assertEquals(heap.size(), 5);
		heap.clear();
		assertEquals(heap.size(), 0);
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#isEmpty()}.
	 */
	public void testIsEmpty() {
		assertTrue(heap.isEmpty());
		heap.add(1);
		assertTrue(!heap.isEmpty());
		heap.clear();
		assertTrue(heap.isEmpty());
		
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#findMin()}.
	 */
	public void testFindMinHeap() {
		heap.add(10);
		heap.add(13);
		heap.add(2);
		heap.add(24);
		heap.add(25);
		heap.add(24);
		heap.add(14);
		
		assertEquals(heap.findMin(), new Integer(2));
		heap.deleteMin();
		assertEquals(heap.findMin(), new Integer(10));
		heap.deleteMin();
		assertEquals(heap.findMin(), new Integer(13));
		heap.deleteMin();
		assertEquals(heap.findMin(), new Integer(14));
		heap.deleteMin();
		assertEquals(heap.findMin(), new Integer(24));
		heap.deleteMin();
		assertEquals(heap.findMin(), new Integer(24));
		heap.deleteMin();
		assertEquals(heap.findMin(), new Integer(25));
		heap.deleteMin();
		assertEquals(heap.size(), 0);
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#deleteMin()}.
	 */
	public void testDeleteMinHeap() {
		heap.add(10);
		heap.add(13);
		heap.add(2);
		heap.add(24);
		heap.add(25);
		heap.add(24);
		heap.add(14);
		
		
		assertEquals(heap.deleteMin(), new Integer(2));
		assertEquals(heap.deleteMin(), new Integer(10));
		assertEquals(heap.deleteMin(), new Integer(13));
		assertEquals(heap.deleteMin(), new Integer(14));
		assertEquals(heap.deleteMin(), new Integer(24));
		assertEquals(heap.deleteMin(), new Integer(24));
		assertEquals(heap.deleteMin(), new Integer(25));
		assertEquals(heap.size(), 0);
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#add(java.lang.Object)}.
	 */
	public void testAdd() {
		assertEquals(heap.size(), 0);
		heap.add(13);
		assertEquals(heap.size(), 1);
		assertEquals(heap.findMin(), new Integer(13));
		
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#toArray()}.
	 */
	public void testToArray() {
		Object[] compare = {1, 3, 5, 14, 12};
		heap.add(14);
		heap.add(1);
		heap.add(5);
		heap.add(12);
		heap.add(3);
		Object[] heapArr = heap.toArray();
		
		for(int i = 0; i < 5; i++)
			assertEquals(compare[i], heapArr[i]);
		
		
	}

}
