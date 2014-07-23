package FinalProject;
/**
 * 
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public interface Command {
	public boolean run(CommandManager manager, Object... args);
}
