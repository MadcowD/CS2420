package maksFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import FinalProject.language.Dictionary;
import FinalProject.language.Word;

public class SpellCheckFile {

	static Dictionary d = new Dictionary("wordstats1.txt");
	
	public static void main(String[] args){
		spellCheckFile("hello_world.txt");
	}

	public static void spellCheckFile(String file){
		File f = null;
		FileInputStream fio = null;
		
		

		try{
			fio = new FileInputStream(file);
			FileWriter out = new FileWriter("testSpell.txt");
			f = new File(file);
			BufferedReader r = new BufferedReader(new FileReader(file));
			String line = r.readLine();
			
			
			while(line != null){
				String[] l = line.split(" ");
				for(int i = 0; i < l.length; i++){
					boolean charFlag = false;
					String temp = l[i];
					if(temp.equals("")){
						continue;
					}
					char punct = temp.charAt(temp.length()-1);
					for(int c = 33; c < 48; c++){
						if(c == punct){
							charFlag = true;
							temp = temp.substring(0, temp.length()-1);
							break;
						}
					}

					Word w = d.find(temp);
					temp = w.getWord();
					if(charFlag){
						temp = temp + punct;
					}
					l[i] = temp;
					
				}
				StringBuilder sb = new StringBuilder();
				for(String i : l){
					sb.append(i);
					sb.append(" ");
				}
				
				out.write(sb.toString());
				line = r.readLine();
			}
			out.close();
			System.out.println();


		}catch(Exception e){
			e.printStackTrace();
		}
		Scanner s = new Scanner(fio);


	}

}
