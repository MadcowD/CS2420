package assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * TODO Most of these need to be also asserted by actually looking at the graph, since we test add with contains and contains with add. >.>
 *
 */
public class BSTTester extends TestCase {
	

	
	BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

	public void testBinarySearchTree() {
		fail("Not yet implemented");
	}

	public void testAdd() {
		assertTrue(bst.add(10));
		assertTrue(!bst.add(10));
		assertTrue(bst.contains(10));		
		for(int i = 0; i<= 20; i++){
			if(i%2 == 0)
				bst.add(i);
			else
				bst.add(20-i);
		}
		assertTrue(bst.contains(13));
		assertTrue(!bst.contains(27));
		
	}

	public void testAddAll() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<10; i++){
			list.add(i);
		}
		
		bst.addAll(list);
		
		assertEquals(bst.size(), 10);
		for(int i : list)
			assertTrue(bst.contains(i));
	}

	public void testClear() {
		assertEquals(bst.size(), 0);
		for(int i = 0; i<10; i++){
			bst.add(i);
		}
		assertEquals(bst.size(), 10);
		bst.clear();
		assertEquals(bst.size(), 0);
	}

	public void testContains() {
		for(int i = 0; i<= 20; i++){
			if(i%2 == 0)
				bst.add(i);
			else
				bst.add(20-i);
		}
		assertTrue(bst.contains(17));
		assertTrue(!bst.contains(39));
		
	}

	public void testContainsAll() {
		ArrayList<Integer> list = BinarySearchTree.randomList(20);
		
		
		bst.addAll(list);
		
		assertTrue(bst.containsAll(list));
	}

	public void testIsEmpty() {
		assertTrue(bst.isEmpty());
		bst.add(4);
		assertTrue(!bst.isEmpty());
		bst.clear();
		
		assertEquals(bst.size(), 0);
		for(int i = 0; i<10; i++){
			bst.add(i);
		}
		assertEquals(bst.size(), 10);
		bst.clear();
		assertTrue(bst.isEmpty());
	}

	public void testRemove() {
		fail("Not yet implemented");
	}

	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	public void testSize() {
		assertEquals(bst.size(), 0);
		for(int i = 0; i<10; i++){
			bst.add(i);
		}
		assertEquals(bst.size(), 10);
	}

	public void testToArrayList() {
		ArrayList<Integer> list = BinarySearchTree.randomList(10);
		for(int i = 0; i<10; i++)
			bst.add(list.get(i));
		
		ArrayList<Integer> BSTList = bst.toArrayList();
		Collections.sort(list);
		for(int i = 0; i<10; i++){
			assertEquals(list.get(i), BSTList.get(i));
		}
		
		
	}

	public void testFirst() {
		bst.add(10);
		bst.add(17);
		bst.add(7);
		bst.add(19);
		bst.add(3);
		
		assertEquals(bst.first(), new Integer(3));
	}

	public void testLast() {
		bst.add(10);
		bst.add(17);
		bst.add(7);
		bst.add(19);
		bst.add(3);
		
		assertEquals(bst.last(), new Integer(19));
	}

	public void testInOrderDFT() {
		ArrayList<Integer> list = BinarySearchTree.randomList(10);
		bst.addAll(list);
		
		List<Integer> inOrder = bst.inOrderDFT();//TODO IM GONNA FIGURE OUT HOW TO TEST THESE.
		
		fail("Not yet implemented.");
	}

	public void testPreOrderDFT() {
		fail("Not yet implemented");
	}

	public void testPostOrderDFT() {
		fail("Not yet implemented");
	}

	public void testLevelOrderBFT() {
		fail("Not yet implemented");
	}

	public void testWriteDot() {
		fail("Not yet implemented");
	}
	
	
	

}
