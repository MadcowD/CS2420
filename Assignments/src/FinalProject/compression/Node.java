package FinalProject.compression;

public class Node implements Comparable<Node>{

	Node one;
	Node zero;
	Branch parent;
	int frequency;
	boolean isZero;

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
		return this.frequency - other.frequency;
	}
	
	public Node traverse(int i){
		Node temp = this;
		
		if(temp instanceof Leaf)
			return temp;
		
		if(i == 1){
			temp = this.one;
		}
		else if(i == 0){
			temp = this.zero;
		}
		return temp;
		
	}

	
}


