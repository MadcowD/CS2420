
package FinalProject;

import FinalProject.commands.FileSpellCheckCommand;
import FinalProject.compression.FileCompression;
import FinalProject.language.Dictionary;
import FinalProject.language.Word;

/**
 * Not using JUnit because it will be easier to test different functions of our Processor using just normal testing.
 *
 */
public class FinalProjectTester{

	static Dictionary d = new Dictionary("wordstats1.txt");
	
	public static void main(String[] args){
		//////////////////Checking get alternatives////////////////////////////////
		String str = "thisisalongstringthatshouldbeovertwentycharacterslong";
		int length = 53;
		
		int compare = 53*(length+1) + 25;
		
		Word[] w = d.getAlternatives(new Word(str));
		
		if(w.length != compare)
			System.out.println("Error in length of alternatives");
		
		/////////////////Adding 26 more characters to the string///////////////////////////
		
		str = str + "abcdefghijklmnopqrstuvwxyz";
		
		length += 26;
		
		compare = 53*(length+1) + 25;
		
		w = d.getAlternatives(new Word(str));
		
		if(w.length != compare)
			System.out.println("Error in the extended length of alternatives");
		
		/*
		 * To test spell checking a file we can create files with misspelled words and run them in the interface
		 * 
		 * testFile1.txt
		 */
		
		
//		FileCompression.compressFile("testFile1.txt", "testCompress1.txt");
//		FileCompression.decompressFile("testCompress1.txt",	"testDecompress.txt");
		
		
		Word correct = d.find("whyle", false);
		
		if(!correct.getWord().equals("while"))
			System.out.println("Find method in dictionary correct whyle to while");
		
		
		FileCompression.compressFile("great_expectations.txt" , "ge_cd_com.txt");
		FileCompression.decompressFile("ge_cd_com.txt", "ge_decom.txt");
		
		System.out.println("Done Testing");
		
	}

}
