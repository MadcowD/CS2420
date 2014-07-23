/**
 * 
 */
package FinalProject;

import FinalProject.commands.SpellCheckCommand;
import FinalProject.util.ArrayList;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public class CommandManager {
	ArrayList<Command> commands;
	
	
	
	public void register (String string, Command command) {
		commands.add(command);
		
	}

	public boolean process (String string) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean process (String[] string) {
		// TODO Auto-generated method stub
		return false;
	}

	public void display () {
		// TODO Auto-generated method stub
		
	}

	public void run (String string, Object... args) {
		// TODO Auto-generated method stub
		
	}

}
