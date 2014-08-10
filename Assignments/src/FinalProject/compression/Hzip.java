package FinalProject.compression;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 *
 * This class contains the compress and decompress static methods used by the interface.
 */
public class Hzip
{	
	
	/**
	 * Compress a given file
	 * @param inFile - the file to be compressed
	 * @param output - the output compressed file
	 * @throws IOException
	 */
	public static void compress( String inFile, String output ) 
	{
		try{

			String compressedFile = output;

			InputStream in = new BufferedInputStream(new FileInputStream( inFile ) );
			OutputStream fout = new BufferedOutputStream(new FileOutputStream( compressedFile ) );

			HZIPOutputStream hzout = new HZIPOutputStream( fout );
			int ch;
			//Write all the characters from the file to a compressed file
			while( ( ch = in.read( ) ) != -1 )
				hzout.write( ch );

			//Close the streams
			in.close( );
			hzout.close( );

			System.out.println(inFile +" was compressed successfully!");
		}catch(Exception e){
			System.out.println(inFile +" compression was unsuccessful!");
		}
	}

	/**
	 * Decompress a given compressed file
	 * @param compressedFile - the compressed file
	 * @param output - the output decompressed file
	 * @throws IOException
	 */
	public static void decompress( String compressedFile, String output )
	{
		try{
			InputStream fin = new BufferedInputStream(new FileInputStream( compressedFile ) );
			DataInputStream in = new DataInputStream( fin );
			HZIPInputStream hzin = new HZIPInputStream( in );

			OutputStream fout = new BufferedOutputStream(
				new FileOutputStream( output ) );
			int ch;
			//Reads all the characters from the ZIP stream and write them to the "uncompressed" file
			while( ( ch = hzin.read( ) ) != -1 )
				fout.write( ch );

			//Close everything
			hzin.close( );
			fout.close( );
			System.out.println(compressedFile + " was decompressed successfully!");
		}catch(Exception e){
			System.out.println(compressedFile + " was decompressed unsuccessfully!");
		}
	}
}