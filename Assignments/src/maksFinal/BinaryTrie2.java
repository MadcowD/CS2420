//package maksFinal;
//
//public class BinaryTrie2<T>{
//
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	private class Branch extends Node{
//		Node one;
//		Node zero;
//		
//		Branch(Node right, Node left){
//			super(left.frequency + right.frequency);
//			
//			zero = left;
//			one = right;
//			
//		}
//	}
//	
//	private class Node implements Comparable<Node>{
//		
//		Branch parent;
//		int frequency;
//		
//		public Node(int frequency){
//			this.frequency = frequency;
//		}
//		
//		
//		@Override
//		public int compareTo(Node other) {
//			return other.frequency - this.frequency;
//		}
//		
//	}
//	
//	private class Leaf2 extends Node{
//		private char symbol;
//		
//		public Leaf2(char symbol, int frequency){
//			super(frequency);
//			this.symbol = symbol;
//		}
//		
//		public int compareTo(Leaf other){
//			if(this.compareTo(other) == 0){
//				return this.symbol - other.symbol;
//			}
//			else return this.compareTo(other);
//		}
//		
//	}
//	
//	
//	
//	//idk lol 
//}
