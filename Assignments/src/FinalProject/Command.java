package FinalProject;

/**
 * 
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public abstract class Command {
	/**
	 * Runs the given command with access to the command manager
	 * @param manager The manager to which the command is registered.
	 * @param args The arguments to the run method.
	 * @return Whether or not the algorithm ran successfully.
	 */
	public abstract int run(CommandManager manager, Object... args);
	
	
	/**
	 * Initializes the command with certain arguments. (Universal)
	 * It would be adventageous in the future to make an argument data structure as to remove
	 * order from these initialization arguments.
	 * @param args The arguments with which the command is initialized.
	 */
	public abstract boolean init(Object... args);
	
	/**
	 * Returs whether or note the command is enabled.
	 * @return
	 */
	public boolean isEnabled() { return enabled; }
	/**
	 * Sets the command to either enabled or disabled. In the latter case
	 * the command is not displayed to the command line.
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	private boolean enabled = false;

}
