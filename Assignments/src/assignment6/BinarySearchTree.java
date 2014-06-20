package assignment6;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


//MUST CONTAIN NO DUPLICATES SINCE IT IS A SET

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Paymon Saebi
 *
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>, TreeTraversal<Type>{
	
	private BinaryNode root;
	
	public BinarySearchTree(){
		root.left = null;
		root.right = null;
		root.data = null;
	}

	@Override
	public List<Type> inOrderDFT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Type> preOrderDFT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Type> postOrderDFT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Type> levelOrderBFT() {
		LinkedList<BinaryNode> list = new LinkedList<BinaryNode>();
		//TODO
		return null;
	}

	@Override
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

	
	// Recursive method for writing the tree to  a dot file
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

	@Override
	public boolean add(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		root.left = null;
		root.right = null;
	}

	@Override
	public boolean contains(Type item) {
		// TODO Auto-generated method stub
		
		return contains(item, root);
	}
	
	private boolean contains(Type item, BinaryNode t){
		//TODO
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (root.right == null) && (root.left == null);
	}

	@Override
	public boolean remove(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * Represents a general binary tree node. Each binary node contains
	 * data, a left child, and a right child, and a parent.
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
		 * @return The leftmost node in the binary tree rooted at this node.
		 */
		public BinaryNode getLeftmostNode() 
		{
			// Base case, done for you
			if(getLeft() == null)
				return this; // returns "this" node
			

			// FILL IN - do not return null
			return this.left.getLeftmostNode();
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
			return this.right.getRightmostNode();
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
			if(this.right == null)
				return this.left.getRightmostNode();
			return this.right.getLeftmostNode();
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
			if(this == null)
				return -1;
			int lefth = this.left.height();
			int righth = this.right.height();
			
			if(lefth > righth)
				return lefth + 1;
			else
				return righth + 1;
		}
	}	
}
