/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.textanalyzer.analyzer.dictionary.WordStatus;
import org.textanalyzer.database.DBDictionary;
import org.textanalyzer.database.IDBDictionary;

/**
 * @author Michael Schilonka
 *
 */
public class TestDBDictionary {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		IDBDictionary dict = new DBDictionary();
		dict.setWordStatus("Haus", WordStatus.NOMEN);
		dict.setWordStatus("aisji", WordStatus.WRONG);
		
		
		assertEquals(WordStatus.NOMEN, dict.getWordStatus("Haus"));
		assertEquals(WordStatus.WRONG, dict.getWordStatus("aisji"));
	}

}
