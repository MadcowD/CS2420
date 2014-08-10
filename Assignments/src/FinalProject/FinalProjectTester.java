
package FinalProject;

import FinalProject.language.Dictionary;
import FinalProject.language.Word;

/**
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
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

		Word correct = d.find("whyle", false);
		
		if(!correct.getWord().equals("while"))
			System.out.println("Find method in dictionary correct whyle to while");
		
		//These tests were done by putting all the words with their frequencies into
		//an excel sheet and then sorting them in alphabetical order to find words
		//that have similar spellings with different frequencies.
		
		correct = d.find("aga", false);
		if(!correct.getWord().equals("ago"))
			System.out.println("Did not find the closest word to aga, instead found " +correct.getWord());
		
		correct = d.find("aiz", false);
		if(!correct.getWord().equals("air"))
			System.out.println("Did not find air, instead found " + correct.getWord());
		
		correct = d.find("appeam", false);
		if(!correct.getWord().equals("appear"))
			System.out.println("Did not find appear, instead found " + correct.getWord());
		
		String ahead = "ahead";
		correct = d.find(ahead, false);
		if(!correct.getWord().equals(ahead))
			System.out.println("Did not return the correct word as supposed to");
		
//		FileCompression.compress("testFile1.txt", "testCompress1");
//		FileCompression.decompress("testCompress1",	"testDecompress.txt");
//		FileCompression.compress("great_expectations.txt" , "ge_cd_com");
//		FileCompression.decompress("ge_cd_com", "ge_decom.txt");
		
		System.out.println("Done Testing");
		
	}

}
