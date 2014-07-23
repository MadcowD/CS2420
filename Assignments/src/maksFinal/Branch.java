package maksFinal;

public class Branch extends Node {

	Node one;
	Node zero;

	public Branch(Node right, Node left){
		super(left.frequency + right.frequency);
		zero = left;
		one = right;
		one.parent = this;
		zero.parent = this;
	}
}

