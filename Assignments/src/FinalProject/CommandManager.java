/**
 * 
 */
package FinalProject;

import java.io.File;

import FinalProject.util.ArrayList;
import FinalProject.util.KeyValuePair;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 * 
 * This class contains the main Command Manager components for the Command Line interface.
 */
public class CommandManager {
	////////////////////////////////////
	//// Static declaration
	////////////////////////////////////
	
	/**
	 * The required number of initialization arguments.
	 * To specify n arguments change the constant to -1.
	 */
	public static int INIT_ARGC = 1;
	
	
	
	////////////////////////////////////
	//// Instantiable implementation
	////////////////////////////////////
	
	/**
	 * The commands internally stored
	 */
	ArrayList<KeyValuePair<String,Command>> commands = new ArrayList<KeyValuePair<String,Command>>();
	
	
	/**
	 * Initializes the command manager in the abstract case.
	 * For our specific implementation we maintain that argument length should be
	 * a single valid stats file.
	 * @param args 0-Stats file
	 * 				We maintain that these arguments are optional if
	 * 				in the future we endeavor to extend multiple command line
	 * 				arguments. We specify the required argument count within
	 * 				the command managers static class declaration.
	 * @return
	 */
	public boolean init(Object... args) {
		//Perform primary intialization code
		//add shutdown hooks (shutdown courtesies)
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	System.out.println("Thanks for using the text processor...");
		    }
		}));
		
		//An interesting extension would to be creating a modular argument class
		//so that commands can be extensibly initialized based on commandline options, etc. 
		
		//If arguments are being checked confirm proper number of arguments
		if(INIT_ARGC != -1 && (args == null || args.length != INIT_ARGC)){
			System.out.println("Incorrect number of arguments!");	
			System.exit(1);
		}
		
		//This follows from the specifications given, the program need not terminate.
		for(KeyValuePair<String, Command> c : commands)
			c.Value.setEnabled(c.Value.init((Object[]) args));

		return true;
	}
	
	
	/**
	 * Registers a command with the command manager.
	 * @param string The command string or name.
	 * @param command
	 */
	public void register (String name, Command command) {
		commands.add(new KeyValuePair<String, Command>(name,command));
		
	}
	

	/**
	 * Processes command line input through simple regex. Runs given command as a result.
	 * @param string The command selected
	 * @return
	 */
	public boolean process (String string) {
		if(!string.equals("exit")){
			try{
				Command c = commands.get(Integer.parseInt(string)-1).Value;
				
				if(c.isEnabled()){
					boolean result = c.run(this) != -1;
					System.out.println("Please choose from the following options:");
					return result;
				}
				else
					System.out.println("Command disabled. Please choose another option:");
				
				
			}
			catch(NumberFormatException e){
				System.out.println("Invalid option, please choose again:");
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Invalid option, please choose again:");
			}
			catch(NullPointerException n){
				System.out.println("Invalid option, please choose again:");
			}
			
			return true;
		}
		
		//On exit
		return false;
	}
	
	
	/**
	 * Displays a list of possible commands
	 */
	public void display () {
		for(int i = 0; i < commands.size(); i++)
			if(commands.get(i).Value.isEnabled())
				System.out.println((i+1) + ") " + commands.get(i).Key);
			else //This is never reached by paymons specifications
				System.out.println((i+1) + ") [DISABLED] " + commands.get(i).Key);
	}

	
	/**
	 * Runs a given command (O(n) where n is the number of commands);
	 * @param string The command name
	 * @param args The arguments to pass to the command.
	 * @return If the command ran successfully.
	 */
	public  boolean run (String string, Object... args) {
		for(KeyValuePair<String, Command> c : commands)
			if(c.Key.equals(string)){
				if(c.Value.isEnabled())
					return c.Value.run(this, args) != -1;
				else
					System.out.println("Command disabled. Please choose another option:");
			}
				
				
		return false;
		
	}
}
