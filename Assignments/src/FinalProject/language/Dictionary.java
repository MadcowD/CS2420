package FinalProject.language;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import FinalProject.util.BinarySearchTree;
import FinalProject.util.PriorityQueue;

public class Dictionary {

	public BinarySearchTree<Word> dictionary = new BinarySearchTree<Word>();
	
	
	/**
	 * Creates a dictionary given a file containing words and frequency
	 * @param fileName
	 */
	public Dictionary(String fileName){
		try {
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
	
	
	
	
	
	//TODO UGH SO ANNOYING
	public void verboseAlternatives(String word){
		int n = word.length();
		Word[] words = this.getAlternatives(new Word(word));
		
		try{
			FileWriter fw = new FileWriter("alternatives.txt");
			fw.write("User string: " + word + "\r\n\n");
			for(int i = 0; i < n; i++){
				fw.write("Deletion string: " + words[i].getWord() + "\r\n");
			}
			fw.write("\r\nCreated " + n + " deletion alternatives\r\n");
			for(int i = n; i < n+n-1; i++){
				fw.write("Transposition string: " + words[i].getWord() + "\n");
			}
			fw.write("Created " + (n-1) + " transposition alternatives\n");
			for(int i = n+n-1; i < 25*(n+n-1); i++){
				fw.write("Substitution string: " + words[i].getWord() + "\r\n");
			}
			fw.write("Created " + 25*(n) + " substitution alternatives\n");
			int x = 25*(n+n-1);
			fw.close();
			for(int i = x; i < 26*(x+1); i++){
				fw.write("Insertion string: " + words[i].getWord() + "\n");
			}
			fw.write("Created " + 26*(n+1) + " insertion alternatives\n\n");
			
			fw.write("TOTAL: generated " + words.length + " alternative spellings!");
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Performs all the permutations of the String, returning a Priority 
	 * Queue with all the words sorted by frequency
	 * @param word - the alternatives to be found
	 * @return - PriorityQueue with alternatives
	 */
	private Word[] getAlternatives(Word word) {
		String str = word.getWord();
		int n = str.length();

		Word[] result = new Word[53*n + 25];
		StringBuilder sb;
		StringBuilder sb2;
		int add = 0;
		
//		PriorityQueue<Word> alternatives = new PriorityQueue<Word>();
		
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

