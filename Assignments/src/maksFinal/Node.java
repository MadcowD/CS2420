package maksFinal;



public class Node implements Comparable<Node>{

	Branch parent;
	int frequency;

	public Branch getParent(){
		return this.parent;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
	public Node(int frequency){
		this.frequency = frequency;
	}


	@Override
	public int compareTo(Node other) {
		return -other.frequency + this.frequency;
	}

	
}


