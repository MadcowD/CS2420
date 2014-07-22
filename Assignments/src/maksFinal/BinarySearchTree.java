package maksFinal;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 * 
 * This class encompasses the idea of a Binary Search Tree, keeping the items in a sorted fashion, using a tree structure.
 * Allows for O(logN) searches, O(logN) adds in the best case, and O(logN) removal in the best case. 
 * 
 * Works best if items are added to the list in a random order, becomes worst case if sorted items are added. 
 *
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>, TreeTraversal<Type>{
	private BinaryNode root = null;

	public BinarySearchTree(){
	}

	/**
	 * Adds a given item to the tree if it is not already in the list, 
	 * returns true if the item was added, false if the item was not added.
	 * @param item
	 * @return
	 */
	public boolean add(Type item) {
		if(item == null)
			throw new NullPointerException();

		if(isEmpty())
		{
			root = new BinaryNode(item);
			return true;
		}
		else
			return root.add(item);
	}
	
	public BinarySearchTree<Type> merge(Type sum, Type left, Type right){
		BinarySearchTree<Type> bst = new BinarySearchTree<Type>();
		bst.root = new BinaryNode(sum);
		bst.root.left = new BinaryNode(left);
		bst.root.right = new BinaryNode(right);
		
		return bst;
	}
	
	public BinaryNode createNode(Type data){
		BinaryNode node = new BinaryNode(data);
		return node;
	}



	/**
	 * Adds every item from a given collection to the tree, ignoring duplicates.
	 * Returns false if no items were added to the tree, true otherwise.
	 */
	public boolean addAll(Collection<? extends Type> items) {
		boolean changed = false;
		for(Type item : items)
			if(this.add(item))
				changed = true;

		return changed;
	}



	/**
	 * Clears every item in the tree, setting the root to null.
	 */
	public void clear() {
		// We simply let java's garbage collector do the work by setting the root to null
		root = null;

	}



	/**
	 * Driver method for contains. Searches through the list recursively, if the item was found returns true, false otherwise.
	 */
	public boolean contains(Type item) {
		//check for null
		if(item == null)
			throw new NullPointerException();

		if(isEmpty())
			return false;
		else
			return root.contains(item);

	}
	
	public String binaryValue(Type item){
		if(item == null)
			throw new NullPointerException();
		
		if(isEmpty())
			return -1 + "";
		else
			return root.binaryValue(item, "");
	}


	/**
	 * Returns true if every item of a given collection is contained in the tree, false otherwise.
	 */
	public boolean containsAll(Collection<? extends Type> items) {
		for(Type item : items){
			if(!contains(item))
				return false;
		}
		return true;
	}



	/**
	 * Returns true if the list contains no items, aka the root is null; false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}



	/**
	 * Removes an item from the tree, checking 3 different cases for removal, returning true if the
	 * item was found and removed, false if no such item is in the tree.
	 */
	public boolean remove(Type item) {
		if(item == null)
			throw new NullPointerException();

		if(isEmpty())
			return false;

		else{

			//FIRST WE TRAVESRE TO THE NODE.
			BinaryNode parent = null;
			BinaryNode p = this.root;
			while(!p.getData().equals(item)){
				int compare = item.compareTo(p.getData());


				if(compare > 0){
					if(p.getRight() != null){
						parent = p;
						p = p.getRight();
					}
					else
						return false;
				}
				else if(compare < 0){
					if(p.getLeft() != null){
						parent = p;
						p = p.getLeft();
					}
					else
						return false;
				}
			}

			//NOW WE GO OVER THE THREE CASES
			if(p.getLeft() == null){
				if(parent == null)
					this.root = p.getRight();
				else if(p == parent.left)
					parent.setLeft(p.getRight());
				else
					parent.setRight(p.getRight());
			}
			else if(p.getRight() == null){
				if(parent == null)
					this.root = p.getLeft();
				else if(p == parent.left)
					parent.setLeft(p.getLeft());
				else
					parent.setRight(p.getLeft());
			}
			else{
				//THE THIRD CASE WHEN THERE ARE TWO CHILDREN
				// NOT USING GETSUCCESSOR BECAUSE WE WANT REFERENCE TO PARENT
				BinaryNode successor = p.getRight();
				BinaryNode sparent = p;
				while(successor.getLeft() != null)
				{
					sparent = successor;
					successor = successor.getLeft();
				}

				//NOW WE MUST MOVE THE SUCESSOR
				if(parent == null)
					this.root = successor;
				else if(p == parent.left)
					parent.setLeft(successor);
				else
					parent.setRight(successor);

				//WE HERE THEN SET THE SUCCESSORS PARENT TO THE PROPER VALUE
				if(successor == sparent.getLeft())
					sparent.setLeft(successor.getRight());
				else
					sparent.setRight(successor.getRight());

				successor.setRight(p.getRight());
				successor.setLeft(p.getLeft());
			}

		}
		return true;

	}



	/**
	 * Removes every item of the given collection from the tree, returning true if
	 * any item was removed, false if no items were removed.
	 */
	public boolean removeAll(Collection<? extends Type> items) {
		boolean changed = false;
		for(Type item : items)
			if(remove(item))
				changed = true;

		return changed;
	}



	/**
	 * Returns the number of elements in the tree.
	 */
	public int size() {
		return root == null ? 0 : root.size();
	}



	/**
	 * Returns the ArrayList representation of the tree, ordered from smallest to largest.
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		return new ArrayList<Type>(this.inOrderDFT());
	}



	/**
	 * Returns the smallest, leftmost item of the tree, throwing an exception if the list is empty.
	 */
	public Type first() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();

		return root.getLeftmostNode().getData();
	}



	/**
	 * Returns the largest, rightmost element of the tree, throwing an exception if the list is empty.
	 */
	public Type last() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();

		return root.getRightmostNode().getData();
	}


	//----------------------------------
	// TRAVERSAL
	//----------------------------------

	/**
	 * Returns a List representation of the In-Order depth first traversal of the tree,
	 * such that the left child of a node is given first, then the node itself, then the right child
	 */
	public List<Type> inOrderDFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		if(!isEmpty())
			root.inOrderDFT(result);

		return result;
	}

	/**
	 * Returns a List representation of the Pre-Order depth first traversal of the tree,
	 * such that the node is given, then the left and right children.
	 */
	public List<Type> preOrderDFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		if(!isEmpty())
			root.preOrderDFT(result);

		return result;
	}

	/**
	 * Returns a List representation of the Post-Order depth first traversal of the tree,
	 * such that the left and right children are given first, and then the node itself.
	 */
	public List<Type> postOrderDFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		if(!isEmpty())
			root.postOrderDFT(result);

		return result;
	}

	/**
	 * Returns a List representation of the Level-Order breadth first traversal of the tree,
	 * such that the items are given from left to right, going through each row of the tree,
	 * starting from the root and working down.
	 */
	public List<Type> levelOrderBFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		LinkedList<BinaryNode> queue = new LinkedList<BinaryNode>();

		queue.addLast(root);
		while(!queue.isEmpty()){
			BinaryNode n = queue.removeFirst();
			result.add(n.getData());
			if(n.getLeft() != null)
				queue.add(n.getLeft());
			if(n.getRight() != null)
				queue.add(n.getRight());
		}

		return result;
	}



	/**
	 * Driver method that writes the Tree as a .dot file.
	 */
	public void writeDot(String filename)
	{
		try 
		{
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));

			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");

			if(root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * The recursive method for writeDot
	 * @param n - the binary node
	 * @param output - the file to be written to
	 * @throws Exception
	 */
	private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception
	{
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");

		if(n.left != null)
		{
			// write the left subtree
			writeDotRecursive(n.left, output);

			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D" );
		}
		if(n.right != null)
		{
			// write the left subtree
			writeDotRecursive(n.right, output);

			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D" );
		}		
	}

	/**
	 * Creates a randomly permutated list of integers from 0 to size-1.
	 * @param size - the size of the list
	 * @return - an ArrayList of the integers
	 */
	public static ArrayList<Integer> randomList(int size){
		Random rng = new Random();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i<size; i++)
			temp.add(i);

		for(int i = 0; i< size; i++)
			normalSwap(temp, i, rng.nextInt(size));

		return temp;	
	}

	/**
	 * Swap helper method used in randomList method, swaps two elements.
	 * @param list - the ArrayList the items are in
	 * @param index1 - the first item to be swapped
	 * @param index2 - the second item to be swapped
	 */
	private final static <T> void normalSwap(ArrayList<T> list, int index1, int index2){
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}


	
	/**
	 * Represents a general binary tree node. Each binary node contains
	 * data, a left child, and a right child.
	 * 
	 * This would make a good node class for a BinarySearchTree implementation
	 * 
	 */
	private class BinaryNode 
	{
		// Since the outer BST class declares <Type>, we can use it here without redeclaring it for BinaryNode
		Type data;

		BinaryNode left;

		BinaryNode right;

		/**
		 * Construct a new node with known children
		 * 
		 */
		public BinaryNode(Type _data, BinaryNode _left, BinaryNode _right) 
		{
			data = _data;
			left = _left;
			right = _right;
		}



		/**
		 * Recursive method used by postOrderDFT in BinarySearchTree.
		 * @param result
		 */
		public void inOrderDFT(List<Type> result) {
			if(getLeft() != null)
				getLeft().inOrderDFT(result);
			result.add(this.data);
			if(getRight() != null)
				getRight().inOrderDFT(result);
		}
		/**
		 * Recursive method used by postOrderDFT in BinarySearchTree.
		 * @param result
		 */
		public void preOrderDFT(List<Type> result){
			result.add(this.data);

			if(getLeft() != null)
				getLeft().preOrderDFT(result);

			if(getRight() != null)
				getRight().preOrderDFT(result);

		}
		
		/**
		 * Recursive method used by postOrderDFT in BinarySearchTree.
		 * @param result
		 */
		public void postOrderDFT(List<Type> result){
			if(getLeft() != null)
				getLeft().postOrderDFT(result);

			if(getRight() != null)
				getRight().postOrderDFT(result);

			result.add(this.data);
		}

		/**
		 * Returns whether or not any children exist for the given binary node.
		 * @return
		 */
		public boolean hasChildren(){
			return this.right != null || this.left != null;
		}

		/**
		 * Helper method used by add in BinarySearchTree.
		 * @param item - item to be added to the tree
		 * @return
		 */
		public boolean add(Type item) {
			BinaryNode p = this;
			while(true){
				int compare = item.compareTo(p.data);
				if(compare == 0)
					return false;
				else if(compare > 0)
				{
					if(p.getRight() != null)
						p = p.getRight();
					else{
						p.setRight(new BinaryNode(item));
						return true;
					}
				}
				else if(compare < 0){
					if(p.getLeft() != null)
						p = p.getLeft();
					else{
						p.setLeft(new BinaryNode(item));
						return true;
					}
				}
			}
		}

		/**
		 * Construct a new node with no children
		 * 
		 */
		public BinaryNode(Type _data) 
		{
			this(_data, null, null);
		}


		/**
		 * Getter method.
		 * 
		 * @return the node data.
		 */
		public Type getData() 
		{
			return data;
		}

		/**
		 * Setter method.
		 * 
		 * @param _data
		 *          - the node data to be set.
		 */
		public void setData(Type _data) 
		{
			data = _data;
		}

		/**
		 * Getter method.
		 * 
		 * @return the left child node.
		 */
		public BinaryNode getLeft() 
		{
			return left;
		}

		/**
		 * Setter method.
		 * 
		 * @param _left
		 *          - the left child node to be set.
		 */
		public void setLeft(BinaryNode _left) 
		{
			left = _left;
		}

		/**
		 * Getter method.
		 * 
		 * @return the right child node.
		 */
		public BinaryNode getRight() 
		{
			return right;
		}

		/**
		 * Setter method.
		 * 
		 * @param _right
		 *          - the right child node to be set.
		 */
		public void setRight(BinaryNode _right) 
		{
			right = _right;
		}

		/**
		 * Number of children
		 * Use this to help figure out which BST deletion case to perform
		 * 
		 * @return The number of children of this node
		 */
		public int numChildren()
		{
			int numChildren = 0;

			if(getLeft() != null)
				numChildren++;
			if(getRight() != null)
				numChildren++;

			return numChildren;
		}

		/**
		 * Method that returns the size of the tree/subtree at a node.
		 * @return
		 */
		public int size(){

			int size = 1;

			if(getLeft() != null)
				size += getLeft().size();
			if(getRight() != null)
				size += getRight().size();


			return size;

		}

		/**
		 * @return The leftmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getLeftmostNode() 
		{
			// Base case, done for you
			if(getLeft() == null)
				return this; // returns "this" node

			// FILL IN - do not return null
			return getLeft().getLeftmostNode();
		}

		/**
		 * @return The rightmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getRightmostNode() 
		{
			// Base case, done for you
			if(getRight() == null)
				return this; // returns "this" node

			// FILL IN - do not return null
			return getRight().getRightmostNode();
		}		

		/** 
		 * This method applies to binary search trees only (not general binary trees).
		 * 
		 * @return The successor of this node.
		 *  
		 * The successor is a node which can replace this node in a case-3 BST deletion.
		 * It is either the smallest node in the right subtree,
		 * or the largest node in the left subtree.
		 */
		public BinaryNode getSuccessor() 
		{			
			if(hasChildren()){
				if(getLeft() == null && getRight() != null)
					return getRight();
				else if(getLeft() != null && getRight() == null)
					return getLeft();
				else // the two children case
					return getRight().getLeftmostNode();
			}

			return null;
		}


		/**
		 * @return The height of the binary tree rooted at this node. The height of a
		 * tree is the length of the longest path to a leaf node. Consider a tree with
		 * a single node to have a height of zero.
		 *  
		 * The height of a tree with more than one node is the greater of its two subtrees'
		 * heights, plus 1
		 */
		public int height() 
		{			
			int rh = getRight() == null ? -1 : getRight().height();
			int lh = getLeft() == null ? -1 : getLeft().height();
			return 1 + (rh > lh ? rh : lh);
		}

		/**
		 * Helper method used by contains in the BinarySearchTree class on specific nodes.
		 * @param elem - The item searching for
		 * @return
		 */
		public boolean contains(Type elem){
			int compare = elem.compareTo(this.data);
			if(compare == 0)
				return true;
			else if(compare > 0){
				if(getRight() != null)
					return getRight().contains(elem);
				else
					return false;
			}
			else{
				if(getLeft() != null)
					return getLeft().contains(elem);
				else
					return false;
			}
		}
		
		public String binaryValue(Type item, String data) {
			int compare = item.compareTo(this.data);
			if(compare == 0)
				return data + "";
			else if(compare > 0){
				if(getRight()!= null)
					return getRight().binaryValue(item, data + 1);
				else
					return "error";
			}
			else{
				if(getLeft() != null)
					return getLeft().binaryValue(item, data + 0);
				else
					return "error";
			}
		}

	}

}

