package FinalProject.compression; 

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 * 
 * Creates the output stream for compression
 * 
 * Writes to HZIPOutputStream are compressed and
 * sent to the output stream being wrapped.
 * No writing is actually done until close.
 */
public class HZIPOutputStream extends OutputStream {

	private ByteArrayOutputStream byteOut = new ByteArrayOutputStream( );
	private DataOutputStream dout;

	/**
	 * Creates the output stream
	 * @param out
	 * @throws IOException
	 */
	public HZIPOutputStream( OutputStream out ) throws IOException
	{
		dout = new DataOutputStream( out );
	}

	/**
	 * Writes a character to the Byte output stream
	 */
	public void write( int ch ) throws IOException
	{ 
		byteOut.write( ch );
	}

	/**
	 * Writes the file to a compressed file
	 */
	public void close( ) throws IOException
	{
		byte [ ] theInput = byteOut.toByteArray( );
		ByteArrayInputStream byteIn = new ByteArrayInputStream( theInput );

		CharCounter countObj = new CharCounter( byteIn );
		byteIn.close( );

		HuffmanTree codeTree = new HuffmanTree( countObj );
		codeTree.writeEncodingTable( dout );
		BitOutputStream bout = new BitOutputStream( dout );
		
		for( int i = 0; i < theInput.length; i++ )
			bout.writeBits( codeTree.getCode( theInput[ i ] & 0xff ) );
		
		
		bout.writeBits( codeTree.getCode( BitUtils.EOF ) );
		bout.close( );
		byteOut.close( );

	}



}