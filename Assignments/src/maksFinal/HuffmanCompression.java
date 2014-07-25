package maksFinal;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import FinalProject.compression.Branch;
import FinalProject.compression.Leaf;
import FinalProject.compression.Node;
import FinalProject.util.ArrayList;


public class HuffmanCompression {
	private static int CHOICE = 1;
	public static String[] view = new String[2];



	public static void main(String[] args){
		String inputString = "hello.txt";//Sample document

		ArrayList<Integer> chars = readCharacters(inputString);
		HashMap<Character, String> map = buildTrie(chars);
		compressFile(chars, map);

	}



	public static void compressFile(ArrayList<Integer> characters, HashMap<Character, String> translation){



	}



	public static void buildTrie(ArrayList<Integer> characters){

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		ArrayList<Character> unique = new ArrayList<>();

		HashMap<Character, String> result = new HashMap<Character,String>();

		for(int c : characters){
			if(map.containsKey(c)){
				int temp = map.remove(c);
				map.put((char)c, ++temp);
			}
			else{
				map.put((char)c, 1);
				unique.add((char)c);
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		LinkedList<Leaf> leaves = new LinkedList<>();

		for(char c : unique){
			Leaf l = new Leaf(c, map.get(c));
			leaves.add(l);
			pq.add(l);
		}


		while(pq.size() > 1){
			Node left = pq.poll();
			Node right = pq.poll();
			pq.add(new Branch(right, left));
		}

		for(Leaf l : leaves)
			result.put(l.getChar(), l.getCode());

		StringBuilder string = new StringBuilder();//The translation

		for(int c : characters){
			string.append(result.get((char)c));
		}

		try{
			FileWriter fw = new FileWriter("output.txt");

		}catch(Exception e){
			e.printStackTrace();
		}


	}


	public static ArrayList<Integer> readCharacters(String file){
		ArrayList<Integer> characters = new ArrayList<>();//Stores all the characters in the input

		int readInt;//characters (ints) being read

		try{
			FileReader fr = new FileReader(file);//The FileReader
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

		return characters;
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
