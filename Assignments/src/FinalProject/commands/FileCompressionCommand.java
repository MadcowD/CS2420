/**
 * 
 */
package FinalProject.commands;

import FinalProject.Command;
import FinalProject.CommandManager;

/**
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public class FileCompressionCommand extends Command {

	public boolean decompress = false;
	
	public FileCompressionCommand(boolean decompress){
		this.decompress = decompress;
	}
	
	/* (non-Javadoc)
	 * @see FinalProject.Command#run(FinalProject.CommandManager, java.lang.Object[])
	 */
	public int run (CommandManager manager, Object... args) {
		// TODO Auto-generated method stub
		return -1;
	}
	
	
	/* (non-Javadoc)
	 * @see FinalProject.Command#init(java.lang.Object[])
	 */
	public boolean init(Object... args) {
		// TODO Auto-generated method stub
		return true;
		
	}

}
