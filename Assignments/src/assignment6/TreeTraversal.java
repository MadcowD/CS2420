package assignment6;

import java.util.List;

/**
 * An interface providing method contracts for tree traversal.
 * 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public interface TreeTraversal <Type extends Comparable<? super Type>>
{
   	/**
	 * Performs a pre-order depth-first-traversal of the tree
	 * 
	 * @return the list containing the tree elements
	 */
	public List<Type> inOrderDFT();
	/**
	 * Performs an in-order depth-first-traversal of the tree
	 * 
	 * @return the list containing the tree elements
	 */
	public List<Type> preOrderDFT();
	
	/**
	 * Performs a post-order depth-first-traversal of the tree
	 * 
	 * @return the list containing the tree elements
	 */
	public List<Type> postOrderDFT();
	
	/**
	 * Performs a level-order breath-first-traversal of the tree
	 * 
	 * @return the list containing the tree elements
	 */
	public List<Type> levelOrderBFT();
	
	/**
	 * Creates a file with .dot extension to contain information about the tree
	 * The format must be readable by the DOT tool use by graphviz.
	 * 
	 * @param filename - file containing the DOT formated data
	 */
	public void writeDot(String filename);
}
