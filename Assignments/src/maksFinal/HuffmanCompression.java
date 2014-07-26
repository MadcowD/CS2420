package maksFinal;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import FinalProject.compression.Branch;
import FinalProject.compression.Leaf;
import FinalProject.compression.Node;
import FinalProject.util.ArrayList;
import FinalProject.util.PriorityQueue;


public class HuffmanCompression {
	private final static char EOF = (char)(-1);//End of file character

	private static HashMap<Character, String> translate = new HashMap<Character, String>();//Every character and translation for it as a map
	private static HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();//Every character and frequency as a map
	private static ArrayList<Character> unique = new ArrayList<Character>();//All the unique characters, a set of the characters
	private static ArrayList<Character> characters;//A list of every character in order that it appears
	private static Node root;
	private static char end;

	public static void main(String[] args){
		compressFile("hello.txt", "output_compress.txt");
		decompress("output_compress.txt", "ok");
	}


	public static void compressFile(String file, String output){
		////Setup////
		readCharacters(file);//Get every character in the file
		generateFrequency();//Generate a frequency map and get every unique character
		buildTrie();//Build the trie and generate a translation map



		//Create Header
		ByteBuffer header = ByteBuffer.allocate(unique.size()*5+5);
		for(char c : unique){
			header.put((byte)c);
			header.putInt(frequency.get(c));
		}
		
		header.put((byte)0);
		header.putInt(0);
		
		header.flip();//Reset pointers on header

		//Create string of the translation code
		StringBuilder stringCode = new StringBuilder();
		for(char c : characters){
			stringCode.append(translate.get(c));
		}
		stringCode.append(EOF);
		//Append extra 0s to make full byte
		while((stringCode.length() + header.capacity()) % 8 != 0){
			stringCode.append('0');
		}
		stringCode.deleteCharAt(stringCode.length()-1);//Overcounting
		stringCode.deleteCharAt(stringCode.length()-1);

		String str = stringCode.toString();//Use for parsing

		//Actual translation in bytes
		ByteBuffer code = ByteBuffer.allocate(str.length()/8);//Allocate enough memory for the bytes

		//Looping through 8 bits of the string representation
		int i = 0; int j = 7;
		while(j < stringCode.length()){
			String sub = str.substring(i, j);//create a substring of byte size
			int b = Integer.parseInt(sub, 2);//parse an integer in binary from the bits
			code.put((byte)b);//place the byte into the code
			i+= 8; j+=8;//increment
		}

		code.flip();//Reset the pointers for the code
		//Write the bytes to a text file
		try{
			FileWriter fw = new FileWriter(output);
			//Write the header
			while(header.position() < header.limit())
				fw.write(header.get());
			//Write the rest of the code
			while(code.position() < code.limit())
				fw.write(code.get());
			fw.close();//Must close to work
		}catch(Exception e){
			e.printStackTrace();//Catch any errors
		}
	}



	public static void decompress(String input, String output){
		byte[] data = new byte[0];
		try{
			data = Files.readAllBytes(Paths.get(input));
			System.out.println(Arrays.toString(data));

		}catch(Exception e){
			e.printStackTrace();
		}

		ArrayList<Character> unique = new ArrayList<Character>();

		HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
		int i = 0;

		System.out.println(data.length);
		int freq = 1;
		for(i = 0; i < data.length; i+= 5){
			freq = data[i+1] + data[i+2] + data[i+3] + data[i+4];
			if(freq == 0)
				break;
			frequencies.put((char) data[i], freq);
			unique.add((char)data[i]);
		}


		translate = buildTrie(frequencies, unique);
		StringBuilder sb = new StringBuilder();

		for(; i<data.length; i++){
			sb.append(Integer.toBinaryString(data[i]));
		}
		
		char[] decypher = sb.toString().toCharArray();
		System.out.println(Arrays.toString(decypher));

		Node temp = root;
		
		StringBuilder tempResult = new StringBuilder();
		for(int j = 0; j < decypher.length; j++){
			temp = temp.traverse(Integer.parseInt(decypher[j] + ""));
			System.out.println(decypher[j]);
			if(temp instanceof Leaf){
				System.out.println(((Leaf) temp).getChar());
				tempResult.append(((Leaf) temp).getChar());
				temp = root;
			}

		}
		System.out.println(tempResult.toString());	
		
	}


	/**
	 * Builds the Trie and creates a HashMap that contains every unique character and its bit translation as a String
	 */
	private static void buildTrie(){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		ArrayList<Leaf> leaves = new ArrayList<Leaf>();//All the leaves

		//Add every leaf to the queue and list
		for(char c : unique){
			Leaf l = new Leaf(c, frequency.get(c));
			leaves.add(l);
			pq.add(l);
		}

		//Create the tree
		while(pq.size() > 1){
			Node left = pq.deleteMin();
			Node right = pq.deleteMin();
			pq.add(new Branch(right, left));
		}

		root = pq.deleteMin();

		//Create the translation from the tree
		for(Leaf l : leaves)
			translate.put(l.getChar(), l.getCode());
	}

	private static HashMap<Character, String> buildTrie(HashMap<Character,Integer> frequencies, ArrayList<Character> unique){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		ArrayList<Leaf> leaves = new ArrayList<Leaf>();//All the leaves

		HashMap<Character, String> translate = new HashMap<Character,String>();

		//Add every leaf to the queue and list
		for(char c : unique){
			Leaf l = new Leaf(c, frequency.get(c));
			leaves.add(l);
			pq.add(l);
		}

		//Create the tree
		while(pq.size() > 1){
			Node left = pq.deleteMin();
			Node right = pq.deleteMin();
			pq.add(new Branch(right, left));
		}

		root = pq.deleteMin();

		//Create the translation from the tree
		for(Leaf l : leaves)
			translate.put(l.getChar(), l.getCode());
		
		System.out.println(translate.toString());

		return translate;
	}

	/**
	 * Generates a frequency map and list for every unique character in the file
	 */
	private static void generateFrequency(){
		for(char c : characters){
			//If the character is in the map, increment the frequency
			if(frequency.containsKey(c)){
				int temp = frequency.remove(c);
				frequency.put(c, ++temp);
			}
			//If the character is new, place it into the tree with a frequency of 1
			else{
				frequency.put(c, 1);
				unique.add(c);//Add the to list of unique characters
			}
		}

		frequency.put(EOF, 0);//Add a end of file character with frequency 0
	}


	/**
	 * Places all the characters from a file into an ArrayList
	 * @param file
	 */
	private static void readCharacters(String file){
		characters = new ArrayList<>();//Stores all the characters in the input

		int readInt;//characters (ints) being read

		try{
			FileReader fr = new FileReader(file);//The FileReader
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
	}


	/**
	 * Converts a given text file into the binary string representation, used for testing and practice
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
