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
 * This class encapsulates a Input Stream for bits.
 */
public class BitInputStream {

	private InputStream in;
	private int buffer;
	private int bufferPos;
	
	/**
	 * Creates the BitInputStream given an InputStream
	 * @param is
	 */
	public BitInputStream(InputStream is){
		in = is;
		bufferPos = BitUtils.BITS_PER_BYTES;
	}
	
	/**
	 * Read the next bit, returning the integer
	 * @throws IOException
	 */
	public int readBit() throws IOException{
		if(bufferPos == BitUtils.BITS_PER_BYTES){
			buffer = in.read();
			if(buffer == -1)
				return -1;
			bufferPos = 0;
		}
		
		return getBit(buffer, bufferPos++);
	}
	
	/**
	 * CLoses the stream
	 * @throws IOException
	 */
	public void close() throws IOException{
		in.close();
	}
	
	/**
	 * Returns a specific bit using bit operations described in the textbook.
	 */
	public int getBit(int pack, int pos){
		return (pack & (1<<pos))!= 0 ? 1 : 0;
	}
}
