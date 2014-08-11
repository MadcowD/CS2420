package FinalProject.compression;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import FinalProject.util.PriorityQueue;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 *
 * This class encapsulates the Huffman Trie, building it and creating/reading headers
 */
public class HuffmanTree {

	public static final int ERROR = -3;//Incase an error occurs
	public static final int INCOMPLETE_CODE = -2;// I don't know what this is for.
	public static final int END = BitUtils.DIFF_BYTES;
	
	private CharCounter theCounts;
	private HuffNode[] theNodes = new HuffNode[BitUtils.DIFF_BYTES + 1];
	private HuffNode root;
	
	public HuffmanTree(){
		theCounts = new CharCounter();
		root = null;
	}
	
	/**
	 * Creates a Huffman Tree from a given Character Count
	 * @param cc
	 */
	public HuffmanTree(CharCounter cc){
		theCounts = cc;//the characters and their counts
		root = null;
		createTree();
	}
	
	/**
	 * Returns the huffman code for a specific character
	 * @param ch - the character
	 * @return the bit array
	 */
	public int[] getCode(int ch){
		HuffNode current = theNodes[ch];
		if(current == null)
			return null;
		String v = "";
		
		HuffNode par = current.parent;
		while(par != null){
			if(par.left == current)
				v = "0" + v;
			else
				v = "1" + v;
			current = current.parent;
			par = current.parent;
		}
		
		int[] result = new int[v.length()];
		for(int i = 0; i < result.length; i++)
			result[i] = v.charAt(i) == '0' ? 0 : 1;
		
		return result;
	}
	
	/**
	 * Returns the character that a specific code represents
	 * @param code - the string representation of the code
	 * @return - the integer value of the character
	 */
	public int getChar(String code){
		HuffNode p = root;
		for(int i = 0; p!=null && i < code.length(); i++)
			if(code.charAt(i) == '0')
				p = p.left;
			else
				p = p.right;
		
		if(p == null)
			return ERROR;
		
		return p.value;
	}
	
	/**
	 * Creates the header for the huffman compression
	 * @param output stream
	 * @throws IOException
	 */
	public void writeEncodingTable(DataOutputStream out) throws IOException{
		for(int i = 0; i < BitUtils.DIFF_BYTES; i++){
			if(theCounts.getCount(i) > 0){
				out.writeByte(i);
				out.writeInt(theCounts.getCount(i));
			}
		}
		
		out.writeByte(0);
		out.writeInt(0);
	}
	
	/**
	 * Reads the header of a given huffman file and creates the tree
	 * @param input stream
	 * @throws IOException
	 */
	public void readEncodingTable(DataInputStream in) throws IOException{
		for(int i = 0; i < BitUtils.DIFF_BYTES; i++)
			theCounts.setCount(i, 0);
		
		int ch;
		int num;
		
		for( ; ; ){
			ch = in.readByte();
			num = in.readInt();
			if(num == 0)
				break;
			theCounts.setCount(ch, num);
		}
		
		createTree();
	}
	
	/**
	 * Creates the Huffman Tree and sets the Root node as a pointer to the tree
	 */
	private void createTree(){
		
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();

		for( int i = 0; i < BitUtils.DIFF_BYTES; i++ ) {
			if( theCounts.getCount( i ) > 0 )
			{
				HuffNode newNode = new HuffNode( i, theCounts.getCount( i ), null, null, null );
				
				theNodes[ i ] = newNode;
				
				pq.add( newNode );
			}
		}

		theNodes[ END ] = new HuffNode( END, 1, null, null, null );
		
		pq.add( theNodes[ END ] );

		while( pq.size( ) > 1 )
		{
			HuffNode n1 = pq.deleteMin( );
			HuffNode n2 = pq.deleteMin( );
			HuffNode result = new HuffNode( INCOMPLETE_CODE, n1.weight + n2.weight, n1, n2, null );
			
			
			n1.parent = n2.parent = result;
			pq.add( result );
		}
		
		root = pq.findMin( );
	}

	
	/**
	 * @author Maks Cegielski-Johnson
	 * @author William Guss
	 * @author Mark Allen Weiss - author of the textbook
	 *
	 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
	 *
	 * This class contains the Node object of the Huffman Trie.
	 */
	public class HuffNode implements Comparable<HuffNode>{

		public int value;
		public int weight;
		
		public int compareTo(HuffNode rhs) {
			return weight - rhs.weight;
		}
		
		HuffNode left;
		HuffNode right;
		HuffNode parent;
		
		HuffNode(int v, int w, HuffNode lt, HuffNode rt, HuffNode pt){
			value = v;
			weight = w;
			left = lt;
			right = rt;
			parent = pt;
		}
		
	}
}
