package FinalProject.compression;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * Node class used for building a Huffman Trie
 */
public class Node implements Comparable<Node>{

	protected Node one;
	protected Node zero;
	protected Branch parent;
	protected int frequency;
	protected boolean isZero;

	public Branch getParent(){
		return this.parent;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
	public Node(int frequency){
		this.frequency = frequency;
	}

	/**
	 * Compares two nodes based on their frequency value
	 */
	public int compareTo(Node other) {
		return this.frequency - other.frequency;
	}
	
	/**
	 * Method for traversing down a node, used for decompressing
	 * @param i - either a 1 or 0 from the binary String
	 * @return
	 */
	public Node traverse(int i){
		Node temp = this;
		
		//Break out if necessary
		if(temp instanceof Leaf)
			return temp;
		
		if(i == 1){
			temp = this.one;
		}
		else if(i == 0){
			temp = this.zero;
		}
		return temp;
		
	}

	
}


