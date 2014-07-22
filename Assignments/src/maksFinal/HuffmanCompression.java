package maksFinal;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

		PriorityQueue<CompressionData> pq = new PriorityQueue<>();
//		HashMap<Integer, Integer> characters = new HashMap<>();
		
		LinkedList<CompressionData> characters = new LinkedList<>();
		//		ArrayList<Integer> characters = new ArrayList<>();//Stores all the characters in the input

		int readInt;//characters (ints) being read

		try{
			FileReader fr = new FileReader(inputString);//The FileReader
			//Loop until end of file (-1)

			while(true){
				readInt = fr.read();//get the next char
				if(readInt < 0)//if end of file, break
					break;
				
				CompressionData tempTrie = new CompressionData(readInt,false);//Trie Node of freq 1
				
				if(characters.contains(tempTrie)){
					int location = characters.indexOf(tempTrie);
					characters.get(location).updateFrequency();//increment frequency by 1
				}
				else{
					characters.add(tempTrie);//add the freq 1 character
				}

			}
			fr.close();
		}catch(Exception e){
			e.printStackTrace();//Exceptions
		}
		
		view[0] = characters.toString();//View the List (optional)
		
		for(CompressionData n : characters){
			pq.add(n);
		}
		while(pq.size() > 1){
			CompressionData left = pq.poll();
			CompressionData right = pq.poll();
			CompressionData center = new CompressionData(left.getFrequency() + right.getFrequency(), true);
			bst.merge(center, left, right);
			pq.add(center);
		}
		
		System.out.println(pq.toString());
		bst.add(pq.poll());
		System.out.println(bst.toArrayList().toString());
		
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
