package assignment8;

/**
 * Serves as a guide for how to define a functor that contains a hashing
 * function for String items (i.e., the hash method).
 * 
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * DO WE NEED TO PUT OUR NAMES ON THIS? WE DID IT JUST IN CASE DUE TO MAYBE STRICT GRADING POLICY
 * IF NOT EVERY FILE HAS OUR NAME ON IT, ALBEIT WE DIDN'T EVEN TOUCH THIS CODE.
 */
public interface HashFunctor 
{
	/**
	 * Returns the hash code of a passed in String parameter
	 */
	public int hash(String item);
}