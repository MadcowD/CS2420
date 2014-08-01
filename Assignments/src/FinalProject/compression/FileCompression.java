package FinalProject.compression;

//import java.util.HashMap;//TODO USE OUR OWN HASHMAP
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import FinalProject.compression.Branch;
import FinalProject.compression.Leaf;
import FinalProject.compression.Node;
import FinalProject.util.ArrayList;
import FinalProject.util.KeyValuePair;
import FinalProject.util.PriorityQueue;



public class FileCompression {
	private final static char EOF = (char)(3);//End of file character TODO fix?
	private static ArrayList<KeyValuePair<Character, String>> translate = new ArrayList<>();
	private static ArrayList<KeyValuePair<Character, Integer>> frequency = new ArrayList<>();
//	private static HashMap<Character, String> translate = new HashMap<Character, String>();//Every character and translation for it as a map
//	private static HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();//Every character and frequency as a map
	private static ArrayList<Character> unique = new ArrayList<Character>();//All the unique characters, a set of the characters
	private static ArrayList<Character> characters;//A list of every character in order that it appears
	private static Node root;//The root Node, used for decompression

	
	public static void main(String[] args){
		compressFile("hello_world.txt", "compression_file_huffman.txt");
		decompressFile("compression_file_huffman.txt", "new_world.txt");
	}


	/**
	 * The main method for compressing a file using Huffman Compression.
	 * @param inputFile - the input file name, given by the user
	 * @param output - the output file name, given by the user
	 */
	public static void compressFile(String inputFile, String output){
		////Setup////
		readCharacters(inputFile);//Get every character in the file
		generateFrequency();//Generate a frequency map and get every unique character
		buildTrie();//Build the trie and generate a translation map

		//Create Header
		ByteBuffer header = ByteBuffer.allocate(unique.size()*5+5);
		for(char c : unique){
			header.put((byte)c);
			header.putInt(frequency.get(c));
		}
		//End of Header:
		header.put((byte)0);
		header.putInt(0);

		header.flip();//Reset pointers on header

		//Create string of the translation code
		StringBuilder stringCode = new StringBuilder();
		for(char c : characters){
			stringCode.append(translate.get(c));
		}

		//Append extra 0s to make full byte
		while((stringCode.length()%8 != 0))
			stringCode.append('0');


		String str = stringCode.toString();//Use for parsing

		//Actual translation in bytes
		ByteBuffer code = ByteBuffer.allocate(str.length()/8);//Allocate enough memory for the bytes

		//Looping through 8 bits of the string representation
		int i = 0; int j = 8;
		while(j <= stringCode.length()){
			String sub = str.substring(i, j);//create a substring of byte size
			int b = Integer.parseInt(sub, 2);//parse an integer in binary from the bits
			code.put((byte)b);//place the byte into the code
			i+= 8; j+=8;//increment
		}

		code.flip();//Reset the pointers for the code

		//Write the bytes to a text file	
		try{
			//Prepare output
			FileOutputStream io = new FileOutputStream(new File(output));
			BufferedOutputStream buff = new BufferedOutputStream(io);
			//Write the header and code
			byte[] input = header.array();
			buff.write(input);
			input = code.array();
			buff.write(input);

			buff.close();//Necessary, close the stream.

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * The main method for decompressing a file using the Huffman algorithm. 
	 * @param input - the file to be decompressed, given by the user
	 * @param output - the output file name, given by the user.
	 */
	public static void decompressFile(String input, String output){
		byte[] data = new byte[0];//All the bytes from the input
		//Read the input
		try{
			data = Files.readAllBytes(Paths.get(input));
		}catch(Exception e){
			e.printStackTrace();
		}

		ArrayList<Character> unique = new ArrayList<Character>();//Local unique character list
		HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();//Local frequency map

		int i;//need indexing for later
		//Create the frequency map
		for(i = 0; i < data.length; i+= 5){
			int freq = data[i+1] + data[i+2] + data[i+3] + data[i+4];
			if(freq == 0)
				break;//If reach a frequency of zero, we know this character does not exist so this is the end of header
			frequencies.put((char) data[i], freq);
			unique.add((char)data[i]);
		}

		i+=5;//Shift the index to the start of the code
		translate = buildTrie(frequencies, unique);//Create the Huffman Trie
		StringBuilder sb = new StringBuilder();//StringBuilder for the binary representation
		int a;
		for(; i<data.length; i++){
			a = data[i] & 0xFF;//Fixes negative integers to be representable as a byte
			String binaryString = Integer.toBinaryString(a);
			StringBuilder binaryStringBuilder = new StringBuilder(binaryString);//Builder for byte length
			//Extends ensures that the binary String is 8 bits long
			while(binaryStringBuilder.length() < 8){
				binaryStringBuilder.insert(0, '0');
			}
			sb.append(binaryStringBuilder.toString());
		}

		char[] decypher = sb.toString().toCharArray();//Character array of all the bits

		Node temp = root;//Temporary Node for traversal

		StringBuilder result = new StringBuilder();//String builder for the final result

		for(int j = 0; j < decypher.length; j++){
			temp = temp.traverse(Integer.parseInt(decypher[j] + ""));//Traverse either 1 or 0
			//If we are at a leaf then this is a character and we reset the pointer to the root
			if(temp instanceof Leaf){
				result.append(((Leaf) temp).getChar());
				temp = root;
			}

		}
		//Write the translation to the file
		try{
			FileWriter fw = new FileWriter(output);
			fw.write(result.toString());
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}

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

	/**
	 * Build trie with given parameters as the frequencies and list of unique characters. Used for decompression
	 * @param frequencies - map of characters and their frequencies
	 * @param unique - list of unique characters
	 * @return - returns the characters and their translations
	 */
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

		root = pq.deleteMin();//Get the root for decompression

		//Create the translation from the tree
		for(Leaf l : leaves)
			translate.put(l.getChar(), l.getCode());


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

		frequency.put(EOF, 1);//Add a end of file character with frequency 0
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
}
