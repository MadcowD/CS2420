package FinalProject.commands;

import java.util.Scanner;

import FinalProject.CommandManager;

public class WordSpellCheckCommand extends SpellCheckCommand{
	@Override
	public boolean run(CommandManager manager, Object... args) {
		String word = "";
		boolean verbose = false;
		
		//HANDLE ARGUMENTS
		if(args != null && args.length ==2){
			word = (String)args[0];
			verbose = (Boolean)args[1];
		}
		//If arguments are invalid, prompt the user.
		else{
			Scanner kb = new Scanner(System.in);
			
			//Prompt the user as it pertains to 
			System.out.println("Please enter a text word:");
			String[] input = kb.nextLine().split(" |-");
			
			word = input[0];
			if(input.length == 2)
				verbose = input[1].equals("f");
		}
		

		//Run the actual spell checking algorithm
		
		return super.run(manager, new String[] {word}, verbose);
	}
}
