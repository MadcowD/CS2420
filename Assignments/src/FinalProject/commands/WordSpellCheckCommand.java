package FinalProject.commands;

import java.util.Scanner;

import FinalProject.CommandManager;

public class WordSpellCheckCommand extends SpellCheckCommand{
	@Override
	public int run(CommandManager manager, Object... args) {
		String word = "";
		boolean verbose = false;
		
		//HANDLE ARGUMENTS
		if(args != null && args.length ==2){
			word = (String)args[0];
			verbose = (Boolean)args[1];
		}
		//If arguments are invalid, prompt the user.
		else{
			@SuppressWarnings("resource")
			Scanner kb = new Scanner(System.in);
			
			//Prompt the user as it pertains to 
			System.out.println("Please enter a text word:");
			String[] input = kb.nextLine().split(" |-");
			
			word = input[0];
			
			//Enable verbose options.
			if(input.length == 2)
				verbose = input[1].equals("f");
			
		}
		

		//Run the actual spell checking algorithm
		String[] data = {word};
		int result =  super.run(manager, data, verbose);
		
		if(result == 0)
			System.out.println(word + " is a known word!");
		else if(result == 1)
			System.out.println(""+word+" is an unknown word, "+data[0]+" is a known word!");
		else if(result == 2)
			System.out.println(word + " is an unknown word!");
		
		return result;
		
	}
}
