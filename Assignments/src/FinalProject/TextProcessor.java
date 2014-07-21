/**
 * 
 */
package FinalProject;

import java.util.Scanner;

import FinalProject.commands.FileCompressionCommand;
import FinalProject.commands.FileRTCommand;
import FinalProject.commands.SpellCheckCommand;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public class TextProcessor {
	/**
	 * Build commands
	 */
	public static CommandManager cManager = new CommandManager();
	
	/**
	 * Register 
	 */
	static{
		cManager.register("Word spell check", 		new SpellCheckCommand());
		cManager.register("File spell check", 		new SpellCheckCommand());
		cManager.register("File compression", 		new FileCompressionCommand());
		cManager.register("File decompression", 	new FileCompressionCommand());
		cManager.register("File remote transfer", new FileRTCommand());
		
	}
	
	/**
	 * @param args The file name of text to be processed
	 */
	public static void main (String[] args) {
		Scanner kb = new Scanner(System.in);
		
		if(!cManager.process(args)){
			do
				cManager.display();
			while(cManager.process(kb.nextLine()));
		}
	}
	
	/**
	 * This driver method
	 *  should first create a file from statsFile
	 *   the and check it for validity, then it should 
	 *   instantiates your spell checker component using the
	 *    file, your compression component,
	 *     and the device manager. Since your
	 *      program is interactive, it is best 
	 *      to initialize all three in this method 
	 *      once for the remainder of the life of the 
	 *      program. if the statsFile is invalid then it
	 *       should print the 
	 *    following message and return: 
	 *    Invalid word stats file argument!
	 *     Please not that the above method should only be 
	 *     called once for the  
	 *     remainder of the life of the program.
	 *      The following methods below will be called
	 *       as many times as the user asks for their 
	 *       respective option.
	 * @param statsFile
	 */
	public static void initializeComponents(String statsFile){
	}
	
	public static void spellcheckWord(String word, boolean fileWrite){
		 cManager.run("Word spell check", word, fileWrite);
	}
	
	public static void spellcheckFile(String srcFile, String dstFile){
		 cManager.run("File spell check",  srcFile, dstFile);
	}
	
	public static void compressFile(String srcFile, String dstFile){
		 cManager.run("File compression", true, srcFile, dstFile);
	}
	
	public static void decompressFile(String srcFile, String dstFile){
		 cManager.run("File decompression", false, srcFile, dstFile);
	}
	
	public static void transmitFile(String srcFile, String statsFile){
		 cManager.run("File remote transfer", srcFile, statsFile);
	}
}
