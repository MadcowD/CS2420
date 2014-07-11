package assignment8;

/**
 * A decent Hash Functor, returns the sum of the characters (as integers) of the String
 * @author Maks Cegielski-Johnson
 * @author William Guss
 */
public class FairHashFunctor implements HashFunctor{

	
	public int hash(String item) {
		int hashVal = 0;
		
		for(int i = 0; i< item.length(); i++)
			hashVal += item.charAt(i);
		
		return Math.abs(hashVal);
	}

	
}
