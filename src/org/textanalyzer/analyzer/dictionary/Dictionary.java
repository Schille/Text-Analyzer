/**
 * 
 */
package org.textanalyzer.analyzer.dictionary;

//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
//import java.net.URLConnection;
import java.util.Scanner;

import org.textanalyzer.database.DBDictionary;

/**
 * @author Brab
 * 
 */
public class Dictionary implements IDictionary {

	
	private DBDictionary dbDict;
	
	public Dictionary(){
		dbDict = new DBDictionary();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.textanalyzer.analyzer.dictionary.IDictionary#getWordStatus(java.lang
	 * .String)
	 */
	@SuppressWarnings("resource")
	@Override
	public WordStatus getWordStatus(String myWord) {
		HashMap<String, WordStatus> cache = new HashMap<String, WordStatus>();
		if (cache.get(myWord) != null) {
			return cache.get(myWord);

		}

		InputStream stream = null;

		try {
			String Abfrage = ""; // Variable f�r den Wiktionary Output
			String var = myWord; // Zu überpr�fendes Wort
			WordStatus Art = WordStatus.WRONG; // Wortart identifier
			int i = 0;
			WordStatus dbWordStatus = dbDict.getWordStatus(myWord);

			if (dbWordStatus != null) { // Lokale DB abfragen
				return dbWordStatus; // Rückgabe aus lokaler DB
			} else {

				/*
				 * URL wird festgelegt &prop= welche Eigenschaft wird abgefragt
				 * (Kategorien zu denen das Wort zugeteilt wurde) &format= gibt
				 * das Format an welches zur�ckgeliefert werden soll (XML, HTML
				 * etc.)
				 */

				URL url = new URL(" http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles=" + var);

				while (i <= 1) {
					stream = url.openStream(); // stream �ffnen

					Abfrage = new Scanner(stream).useDelimiter("\\Z").next(); // Den
																				// Stream
																				// in
																				// die
																				// Variable
																				// schreiben,
																				// bei
																				// einer
																				// leerstelle
																				// nicht
																				// stoppen

					if (Abfrage.contains("Adjektiv"))
						Art = WordStatus.ADJECTIV;
					else if (Abfrage.contains("Substantiv"))
						Art = WordStatus.NOMEN;
					else if (Abfrage.contains("Verb"))
						Art = WordStatus.VERB;
					else if (Abfrage.contains("Präposition"))
						Art = WordStatus.PREPOSITION;
					else if (Abfrage.contains("Füllwort"))
						Art = WordStatus.FILLER;
					else if (Abfrage.contains("missing\" \""))
						Art = WordStatus.FILLER; // Wort nicht vorhanden

					System.out.println(Abfrage);
					dbDict.setWordStatus(myWord, Art);
					return Art;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					return WordStatus.WRONG;
				}
		}
		return WordStatus.WRONG;
	}

}
