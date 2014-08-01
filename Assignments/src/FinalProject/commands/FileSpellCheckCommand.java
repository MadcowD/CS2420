package FinalProject.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import FinalProject.CommandManager;

public class FileSpellCheckCommand extends SpellCheckCommand{
	@Override
	public int run(CommandManager manager, Object... args) {
		String source = "";
		String dest = "";
		

		boolean correctedWords = false;
		boolean unknownWords = false;
		
		//HANDLE ARGUMENTS
		if(args != null && args.length ==2){
			source = (String)args[0];
			dest = (String)args[1];
		}
		//If arguments are invalid, prompt the user.
		else{
			@SuppressWarnings("resource")
			Scanner kb = new Scanner(System.in);
			
			//Prompt the user as it pertains to 
			System.out.println("Please enter the source file path:");
			source = kb.nextLine();
			
			System.out.println("Please enter the destination file path:");
			dest = kb.nextLine();
		}
	
		File src = new File(source);
		if(!src.exists() || !src.isFile()){
			System.out.println(source + " is invalid for spell correction!");
			return 1;
		}
		
		
		try{
			FileWriter out = new FileWriter(dest);
			
			@SuppressWarnings("resource")
			BufferedReader r = new BufferedReader(new FileReader(source));
			
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
					String[] data = {temp};
					int result = super.run(manager, data, false);//find the replacement if it exists
					
					if(result == 1)
						correctedWords = true;
					
					if(result == 2)
						unknownWords = true;
					temp = data[0];
						
					
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

		}catch(IOException e){
			System.out.println(source + " is invalid for spell correction!");
			return 1;
		}
		
		
		//Proper output
		
		if(unknownWords)
		{
			System.out.println(source + " was corrected, but it contains unknown words!");
			return 2;
		}
		if(correctedWords){
			
			System.out.println(source + " was corrected successfully!");
			return 1;
		}
		else
		{
			System.out.println(source + " contains words with correct spelling!");
			return 0;
		}
	}
}
