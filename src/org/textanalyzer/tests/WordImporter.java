package org.textanalyzer.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.textanalyzer.analyzer.dictionary.Dictionary;
import org.textanalyzer.analyzer.dictionary.WordStatus;
import org.textanalyzer.database.DBDictionary;

public class WordImporter {

	@Test
	public void test() {
		String outputString = "";
		String buffer = "";
		int i=0;
		Dictionary test = new Dictionary();
		DBDictionary db = new DBDictionary();
		try {
			BufferedReader out = new BufferedReader(new FileReader("german.txt"));

			try {

				while ((buffer = out.readLine()) != null) {
					outputString = outputString + buffer + "\n";
					//System.out.println(buffer);
						if(db.getWordStatus(buffer)!=null){
							System.out.println("DB: "+buffer+" "+db.getWordStatus(buffer));
						}else{
						if(Character.isUpperCase(buffer.charAt(0))){
							db.setWordStatus(buffer, WordStatus.NOMEN);
							System.out.println(buffer);
							i++;
							System.out.println(i);
						} else if(Character.isLowerCase(buffer.charAt(0))){
							db.setWordStatus(buffer, WordStatus.OTHER);
							System.out.println(buffer);
							i++;
							System.out.println(i);
						}
						}	
								buffer = null;
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
		}
		
	}

}
