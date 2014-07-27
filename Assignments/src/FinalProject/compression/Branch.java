package FinalProject.compression;

/**
 * @author Maks
 * The Branch class encapsulates a Binary Trie node that has children and has a left(zero) and right(one) Node child.
 *
 */
public class Branch extends Node {

	public Branch(Node right, Node left){
		super(left.frequency + right.frequency);
		this.zero = left;
		this.zero.isZero = true;
		this.one = right;
		this.one.isZero = false;
		this.one.parent = this;
		this.zero.parent = this;
	}
	
	/**
	 * The String representation of a branch, showing the zero and one Nodes and the branches frequency
	 */
	public String toString(){
		return this.getFrequency() + " (" + zero + " " + one + ")";
	}
}

