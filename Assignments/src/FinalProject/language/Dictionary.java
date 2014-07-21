package FinalProject.language;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import FinalProject.util.BinarySearchTree;
import FinalProject.util.PriorityQueue;

public class Dictionary {

	public BinarySearchTree<Word> dictionary = new BinarySearchTree<Word>();
	
	
	public Dictionary(String fileName){
		try {
			Object[] data = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset()).toArray();
			
			for(Object strObject : data){
				String s = (String)strObject;
				dictionary.add(new Word(s.split(" ")[0].toLowerCase(), Integer.parseInt(s.split(" ")[1])));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public PriorityQueue<Word> find(String wordToFind){
		PriorityQueue<Word> result = new PriorityQueue<Word>();
		Word wordizedSearch = new Word(wordToFind);
		
		if(dictionary.contains(wordizedSearch))
			result.add(wordizedSearch);
		else{
			for(Word alternative : this.getAlternatives(wordizedSearch)){
				if(dictionary.contains(alternative)) //TODO: COULD BE BETTER
					result.add(alternative);
			}
		}
		
		
		return result;//Find the closest words
	}


	private ArrayBasedCollection getAlternatives (Word word) {
		String data = word.getWord();
		int n = data.length;
		Word[] result = new Word[53*n + 25];
		
		//DELETIU
		for(int i = 0; i < n; i++){
			StringBuilder sb = new StringBuilder(data);
			sb.deleteCharAt(i);
			if()
		}
		
		for(int i = 0; i < n-1; i++){
			StringBuilder sb = new StringBuilder(data);
			char temp = data.charAt(i);
			sb.setCharAt(i, data.charAt(i+1));
			
		}
		
		return null;
	}
}

