package FinalProject.compression;

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
	
	

	
	public String toString(){
		return this.getFrequency() + " (" + zero + " " + one + ")";
	}
}

