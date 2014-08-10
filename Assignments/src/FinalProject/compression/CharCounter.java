package FinalProject.compression;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 *
 * This class enables the user to count the frequency of characters that will be allowed in the compression
 * system.  
 */
public class CharCounter {

	private int[] theCounts = new int[BitUtils.DIFF_BYTES];//size 256, all the different characters to be used
	
	/**
	 * Creates a default character counter.
	 */
	public CharCounter(){}
	
	/**
	 * Creates the Character counter given an input
	 * @param input
	 * @throws IOException
	 */
	public CharCounter(InputStream input) throws IOException{
		int ch;
		while((ch = input.read()) != -1)
			theCounts[ch]++;
	}
	
	/**
	 * Returns the count of a specific character
	 * @param ch - the character we search
	 * @return
	 */
	public int getCount(int ch){
		return theCounts[ch & 0xFF];
	}
	
	/**
	 * Sets the count of a specific character
	 * @param ch - the character we are setting the count for
	 * @param count - the count we want
	 */
	public void setCount(int ch, int count){
		theCounts[ch & 0xFF] = count;
	}
}
