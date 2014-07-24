package FinalProject;

/**
 * 
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public interface Command {
	/**
	 * Runs the given command with access to the command manager
	 * @param manager The manager to which the command is registered.
	 * @param args The arguments to the run method.
	 * @return Whether or not the algorithm ran successfully.
	 */
	public boolean run(CommandManager manager, Object... args);

	
	/**
	 * Initializes the command with certain arguments. (Universal)
	 * @param args The arguments with which the command is initialized.
	 */
	public void init(Object... args);
}
