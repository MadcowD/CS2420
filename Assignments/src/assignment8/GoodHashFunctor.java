package assignment8;

/**
 * Good Hash Functor, returns a value computed by a polynomial function (of 37) by use of Horner's Rule.
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * Source - Data Structures and Algorithm Analysis in Java by Mark Allen Weiss
 */
public class GoodHashFunctor implements HashFunctor {

	public int hash(String item) {
		int hashVal = 0;
		for(int i = 0; i<item.length(); i++)
			hashVal = 37*hashVal + item.charAt(i);

		return Math.abs(hashVal);//take absolute value to prevent integer overflow
	}
}
