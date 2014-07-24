package FinalProject.compression;

public class Leaf extends Node{
	private char symbol;
	private String code; 


	public Leaf(char symbol, int frequency){
		super(frequency);
		this.symbol = symbol;
		code = null;
	}

	public String toString(){
		return symbol + "=" + this.getFrequency();
	}
	
	public char getChar(){
		return symbol;
	}
	
	
//	
//	private int getByteCode(){
//		Node temp = this;
//		int code = 1;
//		while(temp.parent != null){
//			if(!temp.isZero){
//				code |= 1;
//				code = code >> 1;
//			}
//		}
//	}
	

	//TODO CONVERT TO BITS?
	private void makeStringCode(){
		Node temp = this;
		StringBuilder code = new StringBuilder();

		while(temp.parent != null){
			if(!temp.isZero)
				code.insert(0, '1');
			else if(temp.isZero)
				code.insert(0, '0');

			temp = temp.parent;

		}

		this.code = code.toString();
	}

	public String getCode(){
		if(code == null)
			this.makeStringCode();
		return code;
	}


	public int compareTo(Leaf other){
		if(this.compareTo(other) == 0){
			return this.symbol - other.symbol;
		}
		else 
			return this.compareTo(other);
	}


}
