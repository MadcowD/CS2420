package FinalProject.language;


import java.io.File;

import java.io.FileWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;




import FinalProject.Command;

import FinalProject.util.BinarySearchTree;
import FinalProject.util.KeyValuePair;
import FinalProject.util.PriorityQueue;

public class Dictionary {

	public BinarySearchTree<Word> dictionary = new BinarySearchTree<Word>();
	
	
	/**
	 * Creates a dictionary given a file containing words and frequency
	 * @param fileName
	 */
	public Dictionary(String fileName){
		try {
			
//			else
//			{
//				File statsFile = new File(args[0]);
//				
//				if(!statsFile.exists())
//					System.out.println("Invalid word stats file argument!");
//				else //When the file exists and the arguments are valid, load the file.
//				{
//					//Initialize commands
//					for(KeyValuePair<String, Command> c : commands)
//						c.Value.setEnabled(c.Value.init(statsFile, args));
//					
//					return true;
//				}	
//			}
			
			Object[] data = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset()).toArray();
			
			for(Object strObject : data){
				String s = (String)strObject;
				dictionary.add(new Word(s.split(" ")[0].toLowerCase(), Integer.parseInt(s.split(" ")[1])));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the closest word to the one we want. 
	 * @param wordToFind
	 * @return
	 */
	public Word find(String wordToFind){
		Word result;
		
		Word wordizedSearch = new Word(wordToFind);
		
		if(dictionary.contains(wordizedSearch))
			result = wordizedSearch;
		else{
			Word[] list = this.getAlternatives(wordizedSearch);
			PriorityQueue<Word> alternatives = new PriorityQueue<Word>();
			
			for(Word w : list){
				if(this.dictionary.contains(w)){
					alternatives.add(w);
				}
			}
			result = alternatives.deleteMin();
		}
		
		
		return result;//Find the closest word
	}
	
	
	
	
	
	/**
	 * The verbose method for spell checking a single word, printing out all the
	 * alternatives for the passed String into a text file named after the String,
	 * for example if the word to be passed is "paymon" the text file will be called
	 * "paymon.txt"
	 * @param word - word to be spell checked verbosely
	 */
	public void verboseAlternatives(String word){
		int n = word.length();
		Word[] words = this.getAlternatives(new Word(word));
		
		try{
			FileWriter fw = new FileWriter(word + ".txt");
			fw.write("User string: " + word + "\r\n\r\n");
			//Deletions
			for(int i = 0; i < n; i++){
				fw.write("Deletion string: " + words[i].getWord() + "\r\n");
			}
			fw.write("Created " + n + " deletion alternatives\r\n\r\n");
			
			//Transposition
			for(int i = n; i < n+n-1; i++){
				fw.write("Transposition string: " + words[i].getWord() + "\r\n");
			}
			fw.write("Created " + (n-1) + " transposition alternatives\r\n\r\n");
			
			//Substitution
			int x = 25*n + 2*n - 1;//variable to shorten code
			for(int i = n+n-1; i < x; i++){
				fw.write("Substitution string: " + words[i].getWord() + "\r\n");
			}
			fw.write("Created " + 25*(n) + " substitution alternatives\r\n\r\n");
			
			//Insertion
			for(int i = x; i < 26*(n+1) + x; i++){
				fw.write("Insertion string: " + words[i].getWord() + "\r\n");
			}
			fw.write("Created " + 26*(n+1) + " insertion alternatives\r\n\r\n");
			
			
			fw.write("TOTAL: generated " + words.length + " alternative spellings!");
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Performs all the permutations of the String, returning a array of words
	 * @param word - the alternatives to be found
	 * @return - Array of words with alternatives
	 */
	private Word[] getAlternatives(Word word) {
		String str = word.getWord();
		int n = str.length();

		Word[] result = new Word[53*n + 25];
		StringBuilder sb;
		StringBuilder sb2;
		int add = 0;
		
		//Deletion
		for(int i = 0; i < n; i++){
			 sb = new StringBuilder(str);
			result[add++] = new Word(sb.deleteCharAt(i).toString());
		}
		//Transpose
		for(int i = 0; i < n-1; i++){
			sb = new StringBuilder(str);
			char temp = str.charAt(i+1);
			sb2 = sb.replace(i+1, i+2, str.charAt(i)+"");
			sb2 = sb.replace(i, i+1, temp + "");
			result[add++] = new Word(sb2.toString());
		}
		
		//Substitute
		for(int i = 0; i < n; i++){
			for(int j = 97; j<123; j++){
				if(str.charAt(i) == (char)j)
					continue;
				sb = new StringBuilder(str);
				char c = (char)j;
				String insert = "" + c;
				sb2 = sb.replace(i, i+1, insert);
				result[add++] = new Word(sb2.toString());
			}
		}
		//Insertion
		for(int i = 0; i<n+1; i++){
			for(int j = 97; j<123; j++){
				sb = new StringBuilder(str);
				char c = (char) j;
				sb2 = sb.insert(i, c);
				result[add++] = new Word(sb2.toString());
			}
		}
		
		return result;
	}
}

