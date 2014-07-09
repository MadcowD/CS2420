package assignment8;

public class GoodHashFunctor implements HashFunctor {

	public int hash(String item) {
		int hashVal = 0;
		for(int i = 0; i<item.length(); i++)
			hashVal = 37*hashVal + item.charAt(i);

		return Math.abs(hashVal);//take absolute value to prevent integer overflow
	}
}
