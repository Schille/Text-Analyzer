/**
 * 
 */
package org.textanalyzer.analyzer.dictionary;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.textanalyzer.database.DBDictionary;

/**
 * @author Brab
 * 
 */

public class Dictionary implements IDictionary {

	DBDictionary db;
	int i=0;
	public Dictionary() {
		db = new DBDictionary();
	}

	@SuppressWarnings("resource")
	public WordStatus DudenRequest(String var) {

		WordStatus Art = WordStatus.OTHER;

		try {

			String Abfrage = "";
			InputStream stream = null;
			URL url = new URL("http://www.duden.de/rechtschreibung/" + var);
			stream = url.openStream();

			Abfrage = new Scanner(stream).useDelimiter("\\Z").next();

			if (Abfrage.contains("<span class=\"wortart\">Adjektiv"))
				Art = WordStatus.ADJECTIV;
			else if (Abfrage.contains("<div class=\"field-item even\">Adjektiv"))
				Art = WordStatus.ADJECTIV;
			else if (Abfrage.contains("<span class=\"wortart\">Substantiv"))
				Art = WordStatus.NOMEN;
			else if (Abfrage.contains("<div class=\"field-item even\">Substantiv"))
				Art = WordStatus.NOMEN;
			else if (Abfrage.contains("<div class=\"field-item even\">schwaches Verb"))
				Art = WordStatus.VERB;
			else if (Abfrage.contains("<span class=\"wortart\">schwaches Verb"))
				Art = WordStatus.VERB;
			else if (Abfrage.contains("<div class=\"field-item even\">starkes Verb"))
				Art = WordStatus.VERB;
			else if (Abfrage.contains("<span class=\"wortart\">starkes Verb"))
				Art = WordStatus.VERB;
			else
				Art = WordStatus.OTHER;

			db.setWordStatus(var, Art); // Set WordStatus in local Database
			i++;
			System.out.println(i);
			return Art;

		} catch (java.io.FileNotFoundException fnf) {
	
			return WordStatus.WRONG;
		} catch (IOException ex1) {
			return WordStatus.WRONG;
		} 

		
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

		// Declaration

		String wikiAbfrage = ""; // Variable f�r den Wiktionary Output
		String var = myWord; // Zu �berpr�fendes Wort
		WordStatus Art = WordStatus.OTHER; // Wortart identifier

		InputStream wikiStream = null;

		try {
			WordStatus dbWordStatus = db.getWordStatus(myWord);
			
			if (dbWordStatus != null) { // Lokale DB abfragen
				
				System.out.println(dbWordStatus);
				System.out.println(var);
				return dbWordStatus; // R�ckgabe aus lokaler DB
			} else {
				URL url_wiktionary = new URL(
						"http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles="
								+ var);

				wikiStream = url_wiktionary.openStream();

				wikiAbfrage = new Scanner(wikiStream).useDelimiter("\\Z")
						.next();

				if (wikiAbfrage.contains("Adjektiv"))
					Art = WordStatus.ADJECTIV;
				else if (wikiAbfrage.contains("Substantiv"))
					Art = WordStatus.NOMEN;
				else if (wikiAbfrage.contains("Verb"))
					Art = WordStatus.VERB;
				else if (wikiAbfrage.contains("missing\" \""))
					Art = DudenRequest(var); // Wort nicht vorhanden}
				else if (wikiAbfrage.contains("Kategorie:Deutsch"))
					Art = WordStatus.OTHER;
				else
					Art = DudenRequest(var);

				db.setWordStatus(var, Art);
				i++;
				System.out.println(i);
				return Art;
			}

		} catch (java.io.FileNotFoundException e) {

			return WordStatus.WRONG;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return WordStatus.WRONG;

		}
	}
}
