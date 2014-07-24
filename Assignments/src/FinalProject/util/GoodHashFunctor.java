package FinalProject.util;

/**
 * Good Hash Functor, returns a value computed by a polynomial function (of 37) by use of Horner's Rule.
 * 
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * Source - Data Structures and Algorithm Analysis in Java by Mark Allen Weiss
 */
public class GoodHashFunctor implements HashFunction{

	public int hash(String item) {
		int hashVal = 0;
		int lastPrime = 37;
		for(int i = 0; i<item.length(); i++){
			hashVal = HashTable.nextPrime(lastPrime)*hashVal + item.charAt(i)*31;
			lastPrime = HashTable.nextPrime(lastPrime);
		}

		return Math.abs(hashVal);//take absolute value to prevent integer overflow
	}

	@Override
	public <T> int hash(T key) {
		String item = key.toString();
		int hashVal = 0;
		int lastPrime = 37;
		for(int i = 0; i<item.length(); i++){
			hashVal = HashTable.nextPrime(lastPrime)*hashVal + item.charAt(i)*31;
			lastPrime = HashTable.nextPrime(lastPrime);
		}

		return Math.abs(hashVal);//take absolute value to prevent integer overflow
	}
}