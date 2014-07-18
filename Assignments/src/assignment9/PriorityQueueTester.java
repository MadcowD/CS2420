package assignment9;

import java.util.Comparator;

import junit.framework.TestCase;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 */
public class PriorityQueueTester extends TestCase {

	PriorityQueueHEAP<Integer> heap = new PriorityQueueHEAP<Integer>();
	PriorityQueueBST<Integer> bst = new PriorityQueueBST<Integer>();
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

	public void testSizeBST(){
		assertEquals(bst.size(), 0);
		bst.add(3);
		assertEquals(bst.size(), 1);
		bst.add(4);
		assertEquals(bst.size(), 2);
		bst.add(3);
		assertEquals(bst.size(), 2);
		bst.deleteMin();
		bst.deleteMin();
		assertEquals(bst.size(), 0);

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

	public void testClearBST(){
		assertEquals(bst.size(), 0);
		for(int i = 0; i < 5; i++)
			bst.add(i);
		assertEquals(bst.size(), 5);
		bst.clear();
		assertEquals(bst.size(), 0);
	}

	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#isEmpty()}.
	 */
	public void testIsEmptyHeap() {
		assertTrue(heap.isEmpty());
		heap.add(1);
		assertTrue(!heap.isEmpty());
		heap.clear();
		assertTrue(heap.isEmpty());

	}

	public void testIsEmptyBST(){
		assertTrue(bst.isEmpty());
		bst.add(1);
		assertTrue(!bst.isEmpty());
		bst.clear();
		assertTrue(bst.isEmpty());
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

	public void testFindMinBST(){
		bst.add(10);
		bst.add(13);
		bst.add(2);
		bst.add(24);
		bst.add(25);
		bst.add(14);

		assertEquals(bst.findMin(), new Integer(2));
		bst.deleteMin();
		assertEquals(bst.findMin(), new Integer(10));
		bst.deleteMin();
		assertEquals(bst.findMin(), new Integer(13));
		bst.deleteMin();
		assertEquals(bst.findMin(), new Integer(14));
		bst.deleteMin();
		assertEquals(bst.findMin(), new Integer(24));
		bst.deleteMin();
		assertEquals(bst.findMin(), new Integer(25));
		bst.deleteMin();
		assertEquals(bst.size(), 0);
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


	public void testDeleteMinBST(){
		bst.add(10);
		bst.add(13);
		bst.add(2);
		bst.add(24);
		bst.add(25);
		bst.add(24);
		bst.add(14);


		assertEquals(bst.deleteMin(), new Integer(2));
		assertEquals(bst.deleteMin(), new Integer(10));
		assertEquals(bst.deleteMin(), new Integer(13));
		assertEquals(bst.deleteMin(), new Integer(14));
		assertEquals(bst.deleteMin(), new Integer(24));
		assertEquals(bst.deleteMin(), new Integer(25));
		assertEquals(bst.size(), 0);
	}
	/**
	 * Test method for {@link assignment9.PriorityQueueHEAP#add(java.lang.Object)}.
	 */
	public void testAddHeap() {
		assertEquals(heap.size(), 0);
		heap.add(13);
		assertEquals(heap.size(), 1);
		assertEquals(heap.findMin(), new Integer(13));

	}

	public void testAddBST(){
		assertEquals(bst.size(), 0);
		bst.add(13);
		assertEquals(bst.size(), 1);
		assertEquals(bst.findMin(), new Integer(13));
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

	public void testToArrayBST(){
		Object[] compare = {1, 3, 5, 12, 14};
		bst.add(14);
		bst.add(1);
		bst.add(5);
		bst.add(12);
		bst.add(3);

		Object[] bstArr = bst.toArray();

		for(int i = 0; i < 5; i++)
			assertEquals(compare[i], bstArr[i]);
	}
	
	//TESTING WITH COMPARATOR
	
	private PriorityQueueBST<Integer> bstC = new PriorityQueueBST<Integer>(new MaxComp());
	private PriorityQueueHEAP<Integer> heapC = new PriorityQueueHEAP<Integer>(new MaxComp());
	
	
	public void testMaxTree(){
		bstC.add(4);
		bstC.add(13);
		bstC.add(17);
		bstC.add(5);
		bstC.add(2);
		bstC.add(30);
		
		Object[] compare = {30, 17, 13, 5, 4, 2};
		Object[] toArr = bstC.toArray();
		for(int i = 0; i < bstC.size(); i++)
			assertEquals(compare[i], toArr[i]);
		
		assertEquals(bstC.findMin(), new Integer(30));//Should delete 30, not 2
		assertEquals(bstC.deleteMin(), new Integer(30));
		assertEquals(bstC.deleteMin(), new Integer(17));
		assertEquals(bstC.deleteMin(), new Integer(13));
		assertEquals(bstC.deleteMin(), new Integer(5));
		assertEquals(bstC.deleteMin(), new Integer(4));
		assertEquals(bstC.deleteMin(), new Integer(2));
		
	}
	
	public void testMaxHeap(){
		heapC.add(4);
		heapC.add(13);
		heapC.add(17);
		heapC.add(5);
		heapC.add(2);
		heapC.add(30);
		
		Object[] compare = {30, 5, 17, 4, 2, 13};
		Object[] toArr = heapC.toArray();
		for(int i = 0; i < heapC.size(); i++)
			assertEquals(compare[i], toArr[i]);
		
		assertEquals(heapC.findMin(), new Integer(30));//Should delete 30, not 2
		assertEquals(heapC.deleteMin(), new Integer(30));
		assertEquals(heapC.deleteMin(), new Integer(17));
		assertEquals(heapC.deleteMin(), new Integer(13));
		assertEquals(heapC.deleteMin(), new Integer(5));
		assertEquals(heapC.deleteMin(), new Integer(4));
		assertEquals(heapC.deleteMin(), new Integer(2));
		
	}
	
	
	/**
	 * 
	 * This class returns the opposite of the natural ordering of Integers, used for Max-Heap
	 *
	 */
	private class MaxComp implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1.compareTo(o2) < 0)
				return 1;
			if(o1.compareTo(o2) > 0)
				return -1;
			return 0;
		}
		
	}

}
