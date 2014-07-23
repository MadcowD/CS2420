package maksFinal;

public class Leaf extends Node{
	private char symbol;
	private String code; 


	public Leaf(char symbol, int frequency){
		super(frequency);
		this.symbol = symbol;
		code = null;
	}
	
	public String toString(){
		return "The character is: " + symbol + " and the frequency is " + this.getFrequency();
	}
	

	public void makeCode(){
		Node temp = this;
		StringBuilder code = new StringBuilder();

		while(temp.parent != null){
			if(temp.parent.one == this)
				code.append('1');
			else
				code.append('0');

			temp = temp.parent;

		}

		this.code = code.toString();
	}
	
	public String getCode(){
		if(code == null)
			this.makeCode();
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
