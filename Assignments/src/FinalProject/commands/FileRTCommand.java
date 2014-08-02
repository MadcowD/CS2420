/**
 * 
 */
package FinalProject.commands;


import java.io.File;
import java.util.Scanner;

import FinalProject.Command;
import FinalProject.CommandManager;
import FinalProject.DeviceManager;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 * 
 * The File transmit component of the Command Line Interface
 */
public class FileRTCommand extends Command {
	DeviceManager dm;
	
	/* (non-Javadoc)
	 * @see FinalProject.Command#run(FinalProject.CommandManager, java.lang.Object[])
	 */
	public int run (CommandManager manager, Object... args) {
		String source = "";
		if(args != null && args.length > 0){
			source = (String)args[0];
		}
		else //Take input
		{
			Scanner kb = new Scanner(System.in);
			
			//Prompt the user as it pertains to 
			System.out.println("Please enter the source file path:");
			source = kb.nextLine();
		
		}
		
		File src = new File(source);
		if(!src.exists() || !src.isFile())
		{
			System.out.println(source + " is invalid for file transfer!");
			return 1;
		}
		
		dm.transmitFile(source);
		return 0;
	}


	/* (non-Javadoc)
	 * @see FinalProject.Command#init(java.lang.Object[])
	 */
	public boolean init(Object... args) {
		
		dm = new DeviceManager();
		return true;
		
	}

}
