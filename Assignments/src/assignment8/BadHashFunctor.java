package assignment8;

/**
 * Bad functor, returns the length of the string as the hash value.
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 */
public class BadHashFunctor implements HashFunctor{

	public int hash(String item) {
		return item.length();
	}

}
