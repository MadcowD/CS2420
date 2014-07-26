package FinalProject.compression;

public class Branch extends Node {

	Node one;
	Node zero;

	public Branch(Node right, Node left){
		super(left.frequency + right.frequency);
		this.zero = left;
		this.zero.isZero = true;
		this.one = right;
		this.one.isZero = false;
		this.one.parent = this;
		this.zero.parent = this;
	}
	
	
	public Leaf traverse(int i){
		Node temp = this;
		
		if(temp instanceof Leaf)
			return (Leaf) temp;
		
		if(i == 1){
			temp = one;
		}
		else if(i == 0)
			temp = zero;
		return null;
		
	}
	
	public String toString(){
		return this.getFrequency() + " (" + zero + " " + one + ")";
	}
}

