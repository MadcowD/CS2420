 package maksFinal;

	public class Leaf extends BinaryTrie{
	private final char value;
	
	public Leaf(int frequency, char value){
		super(frequency);
		this.value = value;
	}

}
