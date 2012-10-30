/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;
import org.textanalyzer.analyzer.dictionary.Dictionary;
import org.textanalyzer.analyzer.dictionary.WordStatus;

/**
 * @author Brab
 *
 */
public class TestDictionary {

	@Test
	public void test() {
		Dictionary test = new Dictionary();
		for(int i=0;i<100;i++){
		assertEquals(WordStatus.VERB, test.getWordStatus("schreiben"));
		assertEquals(WordStatus.VERB, test.getWordStatus("leben"));
		assertEquals(WordStatus.VERB, test.getWordStatus("überleben"));
		assertEquals(WordStatus.VERB, test.getWordStatus("denken"));
		assertEquals(WordStatus.VERB, test.getWordStatus("riechen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("essen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("trinken"));
		assertEquals(WordStatus.VERB, test.getWordStatus("rennen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("gehen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("laufen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("laufen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("schlafen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("kochen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("sehen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("riechen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("fühlen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("schmerzen"));
		assertEquals(WordStatus.VERB, test.getWordStatus("sprechen"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Haus"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Handy"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Flasche"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Logo"));
		assertEquals(WordStatus.NOMEN, test.getWordStatus("Zug"));
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
		}
		
		
	}

}
