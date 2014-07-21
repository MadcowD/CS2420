package FinalProject;
/**
 * 
 * @author William Guss
 * @author Maks Cegielski-Johnson
 */
public  abstract class Command {
	public abstract boolean run(CommandManager manager, Object... args);
}
