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
	
	/**
	 * Return the character of the leaf
	 * @return
	 */
	public char getChar(){
		return symbol;
	}
	
	/**
	 * Makes the binary String representation of the code for a given leaf node, traversing up the trie.
	 */
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

	/**
	 * Driver method for getting the String representation of the code.
	 * @return
	 */
	public String getCode(){
		if(code == null)
			this.makeStringCode();
		return code;
	}

	/**
	 * Compare method, comparing leaves based on their frequency, unless a tie, then on the characters.
	 * @param other
	 * @return
	 */
	public int compareTo(Leaf other){
		if(this.compareTo(other) == 0){
			return this.symbol - other.symbol;
		}
		else 
			return this.compareTo(other);
	}


}
