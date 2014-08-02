package FinalProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import FinalProject.timing.AlgorithmTimer;
import FinalProject.timing.AlgorithmTimer.TimeComplexity;
import FinalProject.timing.Process;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * The timing class for the final project
 */
public class TextProcessorTimer {
	public static void main(String[] args){
		int sampleSize = 10;
		final boolean verbose = true;
		TextProcessor.initializeComponents("wordstats1.txt");
		
//		new AlgorithmTimer("5bV", new Process(){
//			String a = "";
//			public void generateData(int n, TimeComplexity complexity) {
//				a="";
//				for(int i =0; i < n; i++)
//					a+= "a";
//			};
//			
//			@Override
//			public long run(int n, TimeComplexity complexity) {
//				TextProcessor.spellcheckWord(a, verbose);
//				return 0;
//			}
//			
//		}, 	sampleSize).generateAnalysis(1, 1000,new TimeComplexity[] {TimeComplexity.BEST});
		
		new AlgorithmTimer("7c", new Process(){
			String a = "";
			public void generateData(int n, TimeComplexity complexity) {
				writeRandomStringToFile(generateStrings(n));
				TextProcessor.compressFile("RandomStrings.txt", "a");
			};
			
			@Override
			public long run(int n, TimeComplexity complexity) {
				TextProcessor.decompressFile( "a", "b");
				return 0;
			}
			
		}, 	sampleSize).generateAnalysis(1, 100000,new TimeComplexity[] {TimeComplexity.BEST});
	}
	
	
	
	public static String[] generateStrings(int n){
		String[] result = new String[n];
		Random rng = new Random();
		for(int i = 0; i<n; i++){
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j<7; j++){
				int c = 97 + rng.nextInt(123-97);
				sb.append((char)c);
			}
			result[i] = sb.toString();
		}
		
		return result;
	}
	
	public static void writeRandomStringToFile(String[] strings){
		try {
			FileWriter fw = new FileWriter("RandomStrings.txt");
			int lineCount = 0;
			for(String s : strings){
				fw.write(s);
				if(lineCount%8 == 0)
					fw.write("\r\n");
				else
					fw.append(' ');
			}
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
