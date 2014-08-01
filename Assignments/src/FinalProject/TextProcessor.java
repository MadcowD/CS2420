/**
 * 
 */
package FinalProject;

import java.util.Scanner;

import FinalProject.commands.FileCompressionCommand;
import FinalProject.commands.FileRTCommand;
import FinalProject.commands.FileSpellCheckCommand;
//import FinalProject.commands.WordSpellCheckCommand;//TODO
import FinalProject.commands.WordSpellCheckCommand;

/**
 * @author Willy Guss
 * @author Makswell Cegielski-Johnson
 */
public class TextProcessor {
	/**
	 * Build commands
	 */
	public static CommandManager cManager = new CommandManager();
	static{
		cManager.register("Word spell check", 		new WordSpellCheckCommand());//TODO uncomment
		cManager.register("File spell check", 		new FileSpellCheckCommand());
		cManager.register("File compression", 		new FileCompressionCommand(false));
		cManager.register("File decompression", 	new FileCompressionCommand(true));
		cManager.register("File remote transfer", 	new FileRTCommand());
	}
	
	/**
	 * Defines the entry point for the TextProcessor
	 * @param args
	 */
	public static void main (String[] args) {
		Scanner kb = new Scanner(System.in);
		if(cManager.init(args)){
			do    	 cManager.display();
			while	(cManager.process(kb.nextLine()));
		}
	}
	

	
	
	/////////////////////////////////////////////////
	// Assignment methods
	/////////////////////////////////////////////////
	
	
	/**
	 * Unfortunate use of singleton command manager 
	 * See documentation in method herein referenced.
	 * @param statsFile The stats file with which to initialize arguments
	 */
	public static void initializeComponents(String statsFile){
		cManager.init(statsFile);
	}
	
	/**
	 * Unfortunate use of singleton command manager 
	 * See documentation in method herein referenced.
	 * @param word
	 * @param fileWrite
	 */
	public static void spellcheckWord(String word, boolean fileWrite){
		 cManager.run("Word spell check", word, fileWrite);
	}
	
	/**
	 * Unfortunate use of singleton command manager 
	 * See documentation in method herein referenced.
	 * @param srcFile
	 * @param dstFile
	 */
	public static void spellcheckFile(String srcFile, String dstFile){
		 cManager.run("File spell check",  srcFile, dstFile);
	}
	
	/**
	 * Unfortunate use of singleton command manager 
	 * See documentation in method herein referenced.
	 * @param srcFile
	 * @param dstFile
	 */
	public static void compressFile(String srcFile, String dstFile){
		 cManager.run("File compression", true, srcFile, dstFile);
	}
	
	/**
	 * Unfortunate use of singleton command manager 
	 * See documentation in method herein referenced.
	 * @param srcFile
	 * @param dstFile
	 */
	public static void decompressFile(String srcFile, String dstFile){
		 cManager.run("File decompression", false, srcFile, dstFile);
	}
	

	/**
	 * Unfortunate use of singleton command manager 
	 * See documentation in method herein referenced.
	 * @param srcFile
	 * @param statsFile
	 */
	public static void transmitFile(String srcFile, String statsFile){
		 cManager.run("File remote transfer", srcFile, statsFile);
	}
}
