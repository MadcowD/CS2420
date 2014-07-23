package maksFinal;

public class Node extends BinaryTrie {


	public Node(BinaryTrie left, BinaryTrie right){
		super(left.frequency + right.frequency);
	}
}
