/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.textanalyzer.analyzer.dictionary.Dictionary;
import org.textanalyzer.analyzer.dictionary.WordStatus;
//import junit.framework.Assert;

/**
 * @author Brab
 *
 */
public class TestDictionary {

	
	public void test3(){
		Dictionary test = new Dictionary();
		WordStatus one = test.getWordStatus("e");
		assertEquals(WordStatus.WRONG, one);
	}
	
	
	public void test() {
		Dictionary test = new Dictionary();

		
		
	
		for(int i=0;i<1;i++){
		assertEquals(WordStatus.OTHER, test.getWordStatus("gekannt"));
		assertEquals(WordStatus.OTHER, test.getWordStatus("wiedersieht"));
		assertEquals(WordStatus.OTHER, test.getWordStatus("singend"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Baum"));
		assertEquals(WordStatus.VERB, test.getWordStatus("leben"));
		assertEquals(WordStatus.VERB, test.getWordStatus("überleben"));
		assertEquals(WordStatus.VERB, test.getWordStatus("denken"));
		assertEquals(WordStatus.VERB, test.getWordStatus("riechen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("essen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("trinken"));
		assertEquals(WordStatus.VERB, test.getWordStatus("rennen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("gehen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("laufen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("schlafen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("kochen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("sehen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("fühlen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("schmerzen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("sprechen"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Haus"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Handy"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Flasche"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Logo"));
		WordStatus a = test.getWordStatus("Zug");
		assertEquals(WordStatus.NOMEN, a);
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Auto"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Schwein"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Maus"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Katze"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Hund"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Straße"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Baum"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("blau"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("hohl"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("dick"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("spät"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("früh"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("spät"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("grau"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("eckig"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("rund"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("dunkel"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("dringend"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("hell"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("lila"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("langsam"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("schnell"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("dünn"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("breit"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("klein"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("groß"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("braun"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("rot"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("grün"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Pornokino"));
		assertEquals(WordStatus.ADJECTIV, test.getWordStatus("kackbraun"));
		assertEquals(WordStatus.WRONG, test.getWordStatus("blauddddd"));
		assertEquals(WordStatus.WRONG, test.getWordStatus("nähmlich"));
		}
		
	}
	
	@Test
	public void test2(){

		String outputString = "";
		String buffer = "";

		File data = new File("german");
		try {

			BufferedReader reader =  new BufferedReader(new FileReader(data));
			
			while((buffer = reader.readLine()) != null) {
				outputString  += buffer;
				buffer = null;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> words = new ArrayList<String>();
		
		Collections.addAll(list, outputString.split(" "));
		
		int a = list.size();
		for(String key : list){
			key = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
			words.add(key);
			words.add(key.toLowerCase());
		}

		
		Dictionary test = new Dictionary();
		
		long start = System.currentTimeMillis();
		
		for(String key : words){
			test.getWordStatus(key);
		}
		System.out.println("Impoted: " + words.size() + " words!");
		System.out.println("Import consumes: " + (System.currentTimeMillis() - start) + " ms");
		
	}

}
