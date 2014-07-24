package maksFinal;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import FinalProject.compression.Branch;
import FinalProject.compression.Leaf;
import FinalProject.compression.Node;


public class HuffmanCompression {
	private static int CHOICE = 1;
	public static String[] view = new String[2];

	private static BinarySearchTree<CompressionData> bst = new BinarySearchTree<CompressionData>();
	private static BinarySearchTree<CompressionData> tempBST = new BinarySearchTree<CompressionData>();

	public static void main(String[] args){
		String inputString = "hello.txt";//Sample document

		BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
		
		BinaryTrie3 result;
		bst2.add(4);
		bst2.add(14);
		bst2.add(19);

		if(CHOICE < 0){
			writeFileToBits(inputString);//Convert from characters to bits
			System.out.println("Done");//To notify that main has finished
		}
		else{
			buildTrie(inputString);
//			bst.writeDot("visual.dot");
			System.out.println(view[0]);
			System.out.println(view[1]);
			
//			bst2.writeDot("test.dot");
		}


	}



	public static void buildTrie(String inputString){
		FileWriter write = null;//write the compressed file

		ArrayList<Character> characters = new ArrayList<>();//Stores all the characters in the input

		int readInt;//characters (ints) being read

		try{
			FileReader fr = new FileReader(inputString);//The FileReader
			//Loop until end of file (-1)

			while(true){
				readInt = fr.read();//get the next char
				if(readInt < 0)//if end of file, break
					break;
				characters.add((char)readInt);//add to array
			}
			fr.close();
		}catch(Exception e){
			e.printStackTrace();//Exceptions
		}
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		ArrayList<Character> unique = new ArrayList<>();

		for(char c : characters){
			if(map.containsKey(c)){
				int temp = map.remove(c);
				map.put(c, ++temp);
			}
			else{
				map.put(c, 1);
				unique.add(c);
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		LinkedList<Leaf> sample = new LinkedList<>();
		ArrayList<String> viewCode = new ArrayList<>();

		for(char c : unique){
			Leaf l = new Leaf(c, map.get(c));
			sample.add(l);
			pq.add(l);
		}
		
		
		view[0] = pq.toString();

		while(pq.size() > 1){
			Node left = pq.poll();
			Node right = pq.poll();
			pq.add(new Branch(right, left));
		}

		Node root = pq.poll();
		System.out.println(root.getFrequency());

		for(Leaf l : sample){
			viewCode.add(l.getCode());
		}
		
		view[1] = viewCode.toString();


	}




	/**
	 * Converts a given text file into the binary representation
	 * @param inputString - the text file
	 */
	public static void writeFileToBits(String inputString){

		FileWriter write = null;//write the compressed file

		ArrayList<Integer> characters = new ArrayList<>();//Stores all the characters in the input

		int readInt;//characters (ints) being read

		try{
			FileReader fr = new FileReader(inputString);//The FileReader
			//Loop until end of file (-1)

			while(true){
				readInt = fr.read();//get the next char
				if(readInt < 0)//if end of file, break
					break;
				characters.add(readInt);//add to array
			}
			fr.close();
		}catch(Exception e){
			e.printStackTrace();//Exceptions
		}


		String bits;//The binary value 
		StringBuilder toFile = new StringBuilder("");//StringBuilder

		//For every character convert to bits and then append to String
		for(int i = 0; i<characters.size(); i++){
			bits = Integer.toBinaryString(characters.get(i));//Can be changed to binary or hex
			toFile.append(bits);//add the bits to the String
		}
		//Write the String to a file

		try{
			write = new FileWriter("output.txt");//Create new file
			write.write(toFile.toString());//Write the String of bits to the file
			write.close();//CLOSE THE WRITER, IMPORTANT
		}catch(Exception e){
			e.printStackTrace();//Stack Trace
		}
	}
}
