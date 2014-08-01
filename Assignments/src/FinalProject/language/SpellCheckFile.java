package FinalProject.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SpellCheckFile {

	static Dictionary d = new Dictionary("wordstats1.txt");
	

	/**
	 * Method that takes an input file and corrects any misspelled words that are similar 
	 * to ones contained in the dictionary. If no replacement exists, then it leaves the word alone.
	 * Ignores punctuation and makes every character lower case. 
	 * @param input - input file
	 * @param output - output file
	 */
	public static void spellCheckFile(String input, String output){
		try{
			FileWriter out = new FileWriter(output);
			
			BufferedReader r = new BufferedReader(new FileReader(input));
			
			String line = r.readLine();
			while(line != null){
				String[] l = line.split(" ");
				for(int i = 0; i < l.length; i++){
					boolean charFlag = false;
					
					String temp = l[i];
					
					if(temp.equals("")){//if any extra spaces skips them otherwise checking for punctuation breaks
						continue;
					}
					
					char punct = temp.charAt(temp.length()-1);//get the last character
					
					//if the character is punctuation then set the flag and take special precautions with it
					for(int c = 33; c < 64; c++){
						if(c == 48){
							c+= 10;//skip the integers characters
						}
						if(c == punct){
							charFlag = true;
							temp = temp.substring(0, temp.length()-1);//remove the punctutaion
							break;//break out of the char loop
						}
					}
					Word w = d.find(temp);//find the replacement if it exists
					temp = w.getWord();//get the string
					
					if(charFlag){
						temp = temp + punct;//put the punctuation back
					}
					
					l[i] = temp;//update the word
					
				}
				StringBuilder sb = new StringBuilder();
				int counter = 0;//checks if the last word of the line to prevent extra spacing
				for(String i : l){
					sb.append(i);
					if(counter != l.length - 1)
						sb.append(" ");//put all the spaces back in until the last word
					counter++;
				}
				sb.append("\r\n");//new line
				
				out.write(sb.toString());//write the line back in
				
				line = r.readLine();//get the next line
			}
			
			out.close();//important

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
