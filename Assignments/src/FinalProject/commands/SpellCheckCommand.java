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

	public int run (CommandManager manager, Object... args) {
		//Given that arguments are passed to the method
		if(args == null || args.length == 0)
			return -1;
		
		//construct parameters.
		String[] data = (String[]) args[0];
		
		boolean verbose = args.length > 1 && args[1] instanceof Boolean ? (Boolean)args[1] : false;
		
		
		//PERFORM SPELL CHECKING
		for(int i = 0; i < data.length; i++){
			try{
				String correction = SpellCheckCommand.WordDB.find(data[i], verbose).getWord();
				
				if(correction.equals(data[i])) 
					return 0; //Word is already correct
				else{
					data[i] = correction;
					return 1; //Word was corrected 
				}
			}
			catch(NoSuchElementException e) {
				if(data.length == 1)
					return 2; //Unknown word
				
			}
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see FinalProject.Command#init(java.lang.Object[])
	 */
	public boolean init(Object... args) {
		String file = "";
		if(args != null && args.length != 0)
			file = (String)args[0];
		
		//Build the WordDB from the command line arguments
		if(WordDB == null) WordDB = new Dictionary(file);
		
		//Optimally we allow commands to be disabled and
		//return WordDB != null && !WordDB.isEmpty();
		//but as per the request of the assignment:
		return true;
		
	}
	
}
