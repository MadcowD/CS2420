package FinalProject;

import FinalProject.language.Dictionary;

public class FinalProjectTester {
	public static void main(String... args){
		
		Dictionary d = new Dictionary("wordstats1.txt");
		
		System.out.print(d.dictionary.first());
		
	}
}
