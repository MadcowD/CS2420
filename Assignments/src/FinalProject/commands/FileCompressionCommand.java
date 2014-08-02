/**
 * 
 */
package FinalProject.commands;

import java.io.File;
import java.util.Scanner;

import FinalProject.Command;
import FinalProject.CommandManager;
import FinalProject.compression.FileCompression;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 * 
 * The file compression component of the command line interface.
 */
public class FileCompressionCommand extends Command {

	public boolean decompress = false;
	
	public FileCompressionCommand(boolean decompress){
		this.decompress = decompress;
	}
	
	/* (non-Javadoc)
	 * @see FinalProject.Command#run(FinalProject.CommandManager, java.lang.Object[])
	 */
	public int run (CommandManager manager, Object... args) {
		String source = "";
		String dest = "";
		if(args != null && args.length > 1){
			source = (String)args[0];
			dest = (String)args[1];
		}
		else //Take input
		{
			Scanner kb = new Scanner(System.in);
			
			//Prompt the user as it pertains to 
			System.out.println("Please enter the source file path:");
			source = kb.nextLine();
			
			System.out.println("Please enter the destination file path:");
			dest = kb.nextLine();
		
		}
		
		File src = new File(source);
		if(!src.exists() || !src.isFile())
		{
			System.out.println(source + " is invalid for decompression!");
			return 0;
		}
		
		if(decompress)
			FileCompression.decompressFile(source, dest);
		else
			FileCompression.compressFile(source, dest);
		
		
		
		return 1;
	}
	
	
	/* (non-Javadoc)
	 * @see FinalProject.Command#init(java.lang.Object[])
	 */
	public boolean init(Object... args) {
		// TODO Auto-generated method stub
		return true;
		
	}

}
