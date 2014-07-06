package assignment8;

import java.util.Arrays;

public class TempHashTest {

	
	public static void main(String[] args){
		
		
		ProbingHashTable h = new ProbingHashTable(11, new GoodHashFunctor());
		h.add("one");
		print(h.lambda);
		h.add("two");
		print(h.lambda);
		h.add("for");
		h.add("nor");
		h.add("wow");
		h.add("sweet");
		print(h.lambda);
		print(Arrays.toString(h.toArray()));
		h.add("big");
		h.add("ok");
		h.add("a");
		print(h.lambda);
		print(Arrays.toString(h.toArray()));
		

		
	}
	
	/**
	 * Lazy print statement
	 * @param d - AnyType that want printed
	 */
	public static <T> void print(T d){
		System.out.println(d);
	}
}
