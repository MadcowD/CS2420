package maksFinal;

import java.util.ArrayList;

public class PermutationTechnique {

	//Lower case ASCII values are: 97 through 122 (inclusive)
	
	
	public static void main(String[] args){
		String str = "abc";
		
		StringBuilder sb;
		StringBuilder sb2;
		ArrayList<String> list = new ArrayList<String>();
		
		//Deletion - N CORRECT
		for(int i = 0; i < str.length(); i++){
			sb = new StringBuilder(str);
			sb2 = sb.deleteCharAt(i);
			list.add(sb2.toString());
		}
		
		//Transpose - N - 1 CORRECT
		for(int i = 0; i<str.length()-1; i++){
			sb = new StringBuilder(str);
			char temp = str.charAt(i+1);
			sb2 = sb.replace(i+1, i+2, str.charAt(i)+"");
			sb2 = sb2.replace(i, i+1, temp + "");
			list.add(sb2.toString());
		}
		
		
		//Substitute - 25*N
		for(int i = 0; i < str.length(); i++){
			for(int j = 97; j<123; j++){
				if(str.charAt(i) == (char)j)
					continue;
				sb = new StringBuilder(str);
				char c = (char)j;
				String insert = "" + c;
				sb2 = sb.replace(i, i+1, insert);
				list.add(sb2.toString());
			}
		}
		
		//Insertion 26*(N+1)
		for(int i = 0; i < str.length()+1; i++){
			for(int j = 97; j<123; j++){
				sb = new StringBuilder(str);
				char c = (char) j;
				sb2 = sb.insert(i, c);
				list.add(sb2.toString());
			}
		}

		
		System.out.println(list.toString());
		System.out.println(list.size());
		

	}
	
	

	
	
	public class Word{
		private String word;
		private int freq;
		
		public Word(String str, int fr){
			word = str;
			freq = fr;
		}
		
		public Word(String str){
			word = str;
			freq = -1;
		}
		
		public String getWord(){
			return word;
		}
		
		public int getFreq(){
			return freq;
		}
	}
}
