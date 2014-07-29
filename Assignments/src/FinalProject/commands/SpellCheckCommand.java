package FinalProject.commands;

import java.util.NoSuchElementException;

import FinalProject.Command;
import FinalProject.CommandManager;
import FinalProject.language.Dictionary;

public abstract class SpellCheckCommand extends Command {
	
	/* (non-Javadoc)
	 * @see FinalProject.Command#run(FinalProject.CommandManager, java.lang.Object[])
	 */
	public static Dictionary WordDB;

	public boolean run (CommandManager manager, Object... args) {
		//Given that arguments are passed to the method
		if(args == null || args.length == 0)
			return false;
		
		//construct parameters.
		String[] data = (String[]) args[0];
		boolean verbose = (Boolean)args[1];
		
		
		//PERFORM SPELL CHECKING
		for(int i = 0; i < data.length; i++){
			try{
				String correction = SpellCheckCommand.WordDB.find(data[i]).getWord();
				
				
				if(correction.equals(data[i])) System.out.println(data[i] + " is a known word!");
				else System.out.println(""+data[i]+" is an unknown word, "+correction+" is a known word!");
			}
			catch(NoSuchElementException e) {
				if(data.length == 1) System.out.println(data[i] + " is an unknown word!");
			}
		}
		return true;
			
	}

	/* (non-Javadoc)
	 * @see FinalProject.Command#init(java.lang.Object[])
	 */
	public boolean init(Object... args) {
		if(args == null || args.length == 0)
			return false;
		
		//Build the WordDB from the command line arguments
		if(WordDB != null) WordDB = new Dictionary((String)args[0]);
		return true;
		
	}
	
}
