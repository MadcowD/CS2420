package assignment6;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>, TreeTraversal<Type>{
	public static void main(String[] args){
		//TODO DELETE THIS WHEN WE'RE DONE.
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

		ArrayList<Integer> list = randomList(20);
		
		bst.addAll(list);
		
		bst.writeDot("BST");
		
		System.out.println("Done");
	}
	
	
	private BinaryNode root = null;

	public BinarySearchTree(){
	}

	@Override
	public boolean add(Type item) {
		// TODO Auto-generated method stub
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



	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean changed = false;
		for(Type item : items)
			if(this.add(item))
				changed = true;

		return changed;
	}



	@Override
	public void clear() {
		// We simply let java's garbage collector do the work by setting the root to null
		root = null;

	}



	@Override
	public boolean contains(Type item) {
		//check for null
		if(item == null)
			throw new NullPointerException();

		if(isEmpty())
			return false;
		else
			return root.contains(item);

	}


	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for(Type item : items){
			if(!contains(item))
				return false;
		}
		return true;
	}



	@Override
	public boolean isEmpty() {
		return root == null;
	}



	@Override
	public boolean remove(Type item) {
		if(item == null)
			throw new NullPointerException();

		if(isEmpty())
			return false;
		else{
			if( root.remove(item)){
				//if there is only one item and that item is the root.
				if(root.getData() == null)
					root = null;
				return true;
			}
			else
				return false;
		}
	}



	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		boolean changed = false;
		for(Type item : items)
			if(remove(item))
				changed = true;

		return changed;
	}



	@Override
	public int size() {
		return root == null ? 0 : root.size();
	}



	/**
	 * This is just unfortunate. -_-
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		return new ArrayList<Type>(this.inOrderDFT());
	}



	@Override
	public Type first() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();

		return root.getLeftmostNode().getData();
	}



	@Override
	public Type last() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();

		return root.getRightmostNode().getData();
	}


	//----------------------------------
	// TRAVERSAL
	//----------------------------------

	@Override
	public List<Type> inOrderDFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		if(!isEmpty())
			root.inOrderDFT(result);

		return result;
	}

	@Override
	public List<Type> preOrderDFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		if(!isEmpty())
			root.preOrderDFT(result);

		return result;
	}

	@Override
	public List<Type> postOrderDFT() {
		ArrayList<Type> result = new ArrayList<Type>();
		if(!isEmpty())
			root.postOrderDFT(result);

		return result;
	}

	@Override
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

	
	
	// Driver for writing this tree to a dot file
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
		
		
		public static ArrayList<Integer> randomList(int size){
			Random rng = new Random();
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int i = 0; i<size; i++)
				temp.add(i);

			for(int i = 0; i< size; i++)
				normalSwap(temp, i, rng.nextInt(size));

			return temp;	
		}
		
		private final static <T> void normalSwap(ArrayList<T> list, int index1, int index2){
			T temp = list.get(index1);
			list.set(index1, list.get(index2));
			list.set(index2, temp);
		}

//	@Override
//	public void writeDot(String filename) {
//		if(isEmpty())
//			return;
//		try{
//			FileWriter fw = new FileWriter(filename);
//
//			root.writeDot(fw);
//
//
//			fw.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}



	// YOU CAN USE THE PRIVATE CLASS BELOW AS YOUR NODE FOR ASSIGNMENT 6
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

		public void inOrderDFT(List<Type> result) {
			if(getLeft() != null)
				getLeft().inOrderDFT(result);
			result.add(this.data);
			if(getRight() != null)
				getRight().inOrderDFT(result);
		}

		public void preOrderDFT(List<Type> result){
			result.add(this.data);

			if(getLeft() != null)
				getLeft().preOrderDFT(result);

			if(getRight() != null)
				getRight().preOrderDFT(result);

		}

		public void postOrderDFT(List<Type> result){
			if(getLeft() != null)
				getLeft().postOrderDFT(result);

			if(getRight() != null)
				getRight().postOrderDFT(result);

			result.add(this.data);
		}


		public boolean remove(Type item) {
			int compare = item.compareTo(this.data);

			if(compare == 0){
				//Actually remove the node by setting data to null
				// The dereferencing occurs on the recursive closure of the stack
				if(!hasChildren()){
					this.data = null;
					return true;
				}
			}
			else if(compare > 0){
				if(getRight() != null){
					if(getRight().getData().compareTo(item) == 0)
						this.right = right.getSuccessor();
					else
						getRight().remove(item);
				}
				else
					return false;
			}
			else if(compare < 0)
			{
				if(getLeft() != null){
					if(getLeft().getData().compareTo(item) == 0)
						this.left = left.getSuccessor();
					else
						getLeft().remove(item);
				}

				else

					return false;
			}

			//unreachable code.
			return false;
		}


		/**
		 * Returns whether or not any children exist for the given binary node.
		 * @return
		 */
		public boolean hasChildren(){
			return this.right != null || this.left != null;
		}

		public boolean add(Type item) {
			int compare = item.compareTo(this.data);
			if(compare == 0)
				return false;
			else if(compare > 0)
			{
				if(this.right != null)
					return this.right.add(item);
				else{
					this.right = (new BinaryNode(item));
					return true;
				}
			}
			else if(compare < 0){
				if(left != null)
					return left.add(item);
				else{
					this.left = (new BinaryNode(item));
					return true;
				}
			}

			//Unreachable code
			return false;
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
			// FILL IN - do not return -1

			int rh = getRight() == null ? -1 : getRight().height();
			int lh = getLeft() == null ? -1 : getLeft().height();
			// FILL IN - do not return -1
			return 1 + (rh > lh ? rh : lh);
		}

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
			else if(compare < 0){
				if(getLeft() != null)
					return getLeft().contains(elem);
				else
					return false;
			}
			//Unreachable statement.
			return false;


		}


		// Recursive method for writing the tree to  a dot file
//		public void writeDot(FileWriter output) throws Exception
//		{
//			output.write(this.data + "[label=\"<L> |<D> " + this.data + "|<R> \"]\n");
//
//			if(this.left != null)
//			{
//				// write the left subtree
//				this.left.writeDot(output);
//
//				// then make a link between n and the left subtree
//				output.write(this.data + ":L -> " + this.left.data + ":D\n" );
//			}
//			if(this.right != null)
//			{
//				// write the left subtree
//				this.right.writeDot(output);
//
//				// then make a link between n and the right subtree
//				output.write(this.data + ":R -> " + this.right.data + ":D\n" );
//			}		
//		}

	}

}

