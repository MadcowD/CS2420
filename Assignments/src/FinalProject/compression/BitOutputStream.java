package FinalProject.compression;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 *
 * This class encapsulates an Output Stream for bits.
 */
public class BitOutputStream {

	private OutputStream out;
	private int buffer;
	private int bufferPos;
	
	/**
	 * Creates the stream
	 * @param os
	 */
	public BitOutputStream(OutputStream os){
		out = os;
		bufferPos = 0;
		buffer = 0;
	}
	
	/**
	 * Writes a value to the buffer, flushes if new byte.
	 * @param val - the value being written
	 * @throws IOException
	 */
	public void writeBit(int val) throws IOException{
		buffer = setBit(buffer, bufferPos++, val);
		if(bufferPos == BitUtils.BITS_PER_BYTES)
			flush();
	}
	
	/**
	 * Writes multiple bits from a given array to the buffer 
	 * @param val
	 * @throws IOException
	 */
	public void writeBits(int[] val) throws IOException{
		for(int i = 0; i < val.length; i++){
			writeBit(val[i]);
		}
	}
	
	/**
	 * Sets a specific bit to a value. Bit Shifting taken from the textbook
	 */
	private int setBit(int pack, int pos, int val){
		if(val == 1)
			pack |= (val << pos);
		return pack;
	}
	
	/**
	 * Resets the buffer
	 * @throws IOException
	 */
	public void flush() throws IOException{
		if(bufferPos == 0)
			return;
		out.write(buffer);
		bufferPos = 0;
		buffer = 0;
	}
	
	/**
	 * Close the buffer
	 * @throws IOException
	 */
	public void close() throws IOException{
		flush();
		out.close();
	}
	
}
