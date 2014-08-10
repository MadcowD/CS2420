package FinalProject.compression;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * @author Mark Allen Weiss - author of the textbook
 *
 * This code is taken from "Data Structures & Problem Solving Using Java" - 4th edition
 *
 * This interface provides values for the number of Bits per single Byte, the number of Different Bytes
 * possible, and the End of File character 
 */
public interface BitUtils {

	public static final int BITS_PER_BYTES = 8;
	public static final int DIFF_BYTES = 256;
	public static final int EOF = 256;
}
