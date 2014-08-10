package FinalProject.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 * 
 * Reads a compressed file
 * 
 * HZIPInputStream wraps an input stream. read returns an
 * uncompressed byte from the wrapped input stream.
 */
public class HZIPInputStream extends InputStream
{

	private BitInputStream bin;
	private HuffmanTree codeTree;
	
	/**
	 * Creates the Huffman Tree from an input stream and initializes the BinInputStream
	 */
	public HZIPInputStream( InputStream in ) throws IOException
	{
		DataInputStream din = new DataInputStream( in );

		codeTree = new HuffmanTree( );
		codeTree.readEncodingTable( din );

		bin = new BitInputStream( in );
	}

	/**
	 * Reads all the characters from the input stream
	 */
	public int read( ) throws IOException
	{ 
		String bits = "";
		int bit;
		int decode;

		while( true )
		{
			bit = bin.readBit( );
			if( bit == -1 )
				throw new IOException( "Unexpected EOF" );

			bits += bit;
			decode = codeTree.getChar( bits );
			if( decode == HuffmanTree.INCOMPLETE_CODE )
				continue;
			else if( decode == HuffmanTree.ERROR )
				throw new IOException( "Decoding error" );
			else if( decode == HuffmanTree.END )
				return -1;
			else
				return decode;
		}
	}

	/**
	 * Closes the stream
	 */
	public void close( ) throws IOException
	{ bin.close( ); }


}