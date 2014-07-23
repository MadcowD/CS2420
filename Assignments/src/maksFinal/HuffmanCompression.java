package maksFinal;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import maksFinal.BinaryTrie.Leaf;

public class HuffmanCompression {
	private static int CHOICE = 1;
	public static String[] view = new String[1];
	
	private static BinarySearchTree<CompressionData> bst = new BinarySearchTree<CompressionData>();
	private static BinarySearchTree<CompressionData> tempBST = new BinarySearchTree<CompressionData>();

	public static void main(String[] args){
		String inputString = "hello.txt";//Sample document
		
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
		bst2.add(4);
		bst2.add(14);
		bst2.add(19);
		
		if(CHOICE < 0){
			writeFileToBits(inputString);//Convert from characters to bits
			System.out.println("Done");//To notify that main has finished
		}
		else{
			huffman(inputString);
			bst.writeDot("visual.dot");
			System.out.println(view[0]);
//			bst2.writeDot("test.dot");
		}
		
		
	}



	public static void huffman(String inputString){
		FileWriter write = null;//write the compressed file

		ArrayList<Character> characters = new ArrayList<>();//Stores all the characters in the input

		char readInt;//characters (ints) being read

		try{
			FileReader fr = new FileReader(inputString);//The FileReader
			//Loop until end of file (-1)

			while(true){
				readInt = (char) fr.read();//get the next char
				if(readInt < 0)//if end of file, break
					break;
				characters.add(readInt);//add to array
			}
			fr.close();
		}catch(Exception e){
			e.printStackTrace();//Exceptions
		}
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		ArrayList<Character> unique = new ArrayList<>();
		ArrayList<Integer> frequency = new ArrayList<>();
		
		for(char c : characters){
			if(map.containsKey(c)){
				int temp = map.remove(c);
				map.put(c, temp++);
			}
			else{
				map.put(c, 1);
				unique.add(c);
			}
		}
		
		for(char c : unique){
			frequency.add(map.get(c));
		}

		//Now we have an ArrayList of all the characters and frequencies
		PriorityQueue<Leaf> pq = new PriorityQueue<Leaf>();
		BinaryTrie result = new BinaryTrie();
		
		for(int i = 0; i < unique.size(); i++){
			pq.add(new Leaf(frequency.get(i), unique.get(i)));
		}
		
		
		
		
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
