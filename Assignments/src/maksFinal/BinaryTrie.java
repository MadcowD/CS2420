package maksFinal;

import java.util.Arrays;

import FinalProject.util.GoodHashFunctor;
import FinalProject.util.HashTable;
import FinalProject.util.KeyValuePair;

public class BinaryTrie{
	
	
	public static void main(String[] args){
		
		byte test = (byte)Integer.parseInt("11111111", 2);
		System.out.println(test);
		
		HashTable<KeyValuePair<Character, String>> map = new HashTable<KeyValuePair<Character,String>>(11, new GoodHashFunctor());
		
		char[] chars = {'a','b','c'};
		String[] strs = {"000","001","101"};
		
		int i = 0;
		for(char c : chars){
			map.add(new KeyValuePair<Character,String>(c, strs[i]));
		}
		

		map.toArray();

	}


}
