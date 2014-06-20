package assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Driver class for the spell checking utility.
 * 
 * This class contains a main method which provides a command-line interface 
 * specific to the spell checking utility. The interface must not run the 
 * spell checker unless the user input parameters are valid.
 * 
 * @author Paymon Saebi
 */
public class SpellChecker 
{	
	public static void main(String[] args) 
	{	
		File dictionary = null;
		File document = null;
		String option = "";
		
		//TODO: Check parameter size according to the handout
		
		//TODO: Instantiate the dictionary File object using args[0]		
		
		//TODO: Check to see if this dictionary file is a normal file
		//Use the File class isFile() method
		
		//TODO: Instantiate the document File object using args[1]		
		
		//TODO: Check to see if this document file is a normal file
		//Use the File class isFile() method		
		
		// If a third parameter was passed for the options, check its validity 
		if (args.length == 3) 
   			if(args[2].equalsIgnoreCase("-p") || args[2].equalsIgnoreCase("-f"))
   				option = args[2];
   			else 
   			{
   				System.out.println("Invalid printing or filing option argument!");
   				return;
   			}
		
		// Passing the dictionary file, document file, and the option
		run_spell_check(dictionary, document, option);		
	}

	public static void run_spell_check(File dic, File doc, String option)  
	{
		// Creating a new SpellCheckerUtil object with the dictionary file
		SpellCheckUtil mySC = new SpellCheckUtil(dic);
		
		// Creating a list of misspelled words after checking spellcheking the document
		List<String> misspelledWords = mySC.spellCheck(doc);
	   
		if (misspelledWords.size() == 0) 
			System.out.println("\nThere are no misspelled words in file " + doc + ".\n");
		else 
		{
			System.out.println("\nThere are " + misspelledWords.size() + " misspelled words in file " + doc + ".");
	      
			if(option.equals("-p"))
			{
				//TODO: Print every misspelled word on a new line
			}
			else if(option.equals("-f"))				
				try
				{
					FileWriter writer = new FileWriter("misspelled.txt");
					
					//TODO: Put every misspelled word on a new line in the misspelled.txt file 
					
					//TODO: Make sure to close the "writer" file you just populated.
					
					System.out.println("Please see misspelled.txt for a list of the words.");	
				} 
				catch (IOException e) 
				{
					System.out.println("Unable to create a file for the misspelled words!");
					return;
				}
		
			System.out.println("\nHave a nice day!\n");		
		}		
	}
}
