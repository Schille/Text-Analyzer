/**
 * 
 */
package org.textanalyzer.analyzer.dictionary;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Scanner;
import org.textanalyzer.database.DBDictionary;

/**
 * @author Fabian Brabänder & Michael Schilonka
 * 
 */

public class Dictionary implements IDictionary {

	/**
	 * This class checks the correctness of a given word. After the check, the
	 * result is written into the database to process faster in the future
	 * 
	 * @param String
	 *            Word
	 * @return WordStatus
	 */

	DBDictionary db;
	int i = 0;

	public Dictionary() {
		db = new DBDictionary();
	}

	/**
	 * This method checks the word in the online dictionary "Duden.de"
	 */
	@SuppressWarnings("resource")
	public WordStatus dudenRequest(String myRequest) throws IOException {

		WordStatus result;
		String requestString;
		try {
			/*
			 * URL to access the dictionary
			 */
			requestString = URLDecoder
					.decode("http://www.duden.de/rechtschreibung/" + myRequest,
							"UTF-8");
		} catch (UnsupportedEncodingException e) {
			requestString = "http://www.duden.de/rechtschreibung/" + myRequest;
		}

		URL url = new URL(requestString);
		/*
		 * The data of the website is written into the input stream, after that
		 * scanner checks which information the site contains about the
		 * word(verb, noun etc.)
		 */
		InputStream stream = url.openStream();
		String dudenResponse = new Scanner(stream).useDelimiter("\\A").next();

		if (dudenResponse.contains("<span class=\"wortart\">Adjektiv"))
			result = WordStatus.ADJECTIV;
		else if (dudenResponse
				.contains("<div class=\"field-item even\">Adjektiv"))
			result = WordStatus.ADJECTIV;
		else if (dudenResponse.contains("<span class=\"wortart\">Substantiv"))
			result = WordStatus.NOMEN;
		else if (dudenResponse
				.contains("<div class=\"field-item even\">Substantiv"))
			result = WordStatus.NOMEN;
		else if (dudenResponse
				.contains("<div class=\"field-item even\">schwaches Verb"))
			result = WordStatus.VERB;
		else if (dudenResponse
				.contains("<span class=\"wortart\">schwaches Verb"))
			result = WordStatus.VERB;
		else if (dudenResponse
				.contains("<div class=\"field-item even\">starkes Verb"))
			result = WordStatus.VERB;
		else if (dudenResponse.contains("<span class=\"wortart\">starkes Verb"))
			result = WordStatus.VERB;
		else
			result = WordStatus.OTHER;
		/*
		 * The result of the request is written into the database and the
		 * WordStatus is returned
		 */
		db.setWordStatus(myRequest, result); // Set WordStatus in local Database
		return result;
	}

	/**
	 * This method checks the word in the online dictionary
	 * "the free dictionary"
	 */
	public WordStatus freeDictionaryRequest(String myRequest) throws Exception {



		String requestString;
		try {
			/*
			 * URL to access the dictionary
			 */
			requestString = URLDecoder.decode(
					"http://de.thefreedictionary.com/" + myRequest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			requestString = "http://de.thefreedictionary.com/" + myRequest;
		}

		URL url = new URL(requestString);
		URLConnection connection = url.openConnection();
		/*
		 * The user-agent is set to a specific browser so the content is
		 * accessible. If this won't happen the website recognize that this
		 * request is opened by other software and so the access is reclined
		 */
		connection
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		/*
		 * The data of the website is written into the input stream, after that
		 * scanner checks which information the site contains about the
		 * word(verb, noun etc.)
		 */
		InputStream stream = connection.getInputStream();
		String freeResponse = new Scanner(stream).useDelimiter("\\A").next();

		freeResponse = freeResponse.replace("/" + myRequest, " ");
		freeResponse = freeResponse.replace(">" + myRequest, " ");
		if (freeResponse.contains("Das Wort konnte im") && freeResponse.contains("nicht gefunden werden.")||!freeResponse.contains(myRequest)) {
			stream.close();
			throw new Exception("Word not found!");
		}

		db.setWordStatus(myRequest, WordStatus.OTHER); // Set WordStatus in
														// local Database
		return WordStatus.OTHER;
	}

	/**
	 * This method checks the word in the online dictionary "Wiktionary"
	 */
	private WordStatus wiktionaryRequest(String myRequest) throws Exception {

		WordStatus result;
		String requestString;
		try {
			/*
			 * URL to access the dictionary
			 */
			requestString = URLDecoder
					.decode("http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles="
							+ myRequest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			requestString = "http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles="
					+ myRequest;
		}

		URL url_wiktionary = new URL(requestString);
		/*
		 * The data of the website is written into the input stream, after that
		 * scanner checks which information the site contains about the
		 * word(verb, noun etc.)
		 */
		InputStream wikiStream = url_wiktionary.openStream();
		String wikiResponse = new Scanner(wikiStream).useDelimiter("\\A")
				.next();

		if (wikiResponse.contains("Adjektiv"))
			result = WordStatus.ADJECTIV;
		else if (wikiResponse.contains("Personalpronomen"))
			result = WordStatus.OTHER;
		else if (wikiResponse.contains("Präposition"))
			result = WordStatus.OTHER;
		else if (wikiResponse.contains("Kategorie:Substantiv (Deutsch)"))
			result = WordStatus.NOMEN;
		else if (wikiResponse.contains("Kategorie:Verb (Deutsch)")
				|| wikiResponse.contains("Konjugierte Form"))
			result = WordStatus.VERB;
		else if (wikiResponse.contains("Kategorie:Deutsch")&&!wikiResponse.contains("Kategorie:Buchstabe"))
			result = WordStatus.OTHER;
		else if (wikiResponse.contains("missing\" \"")) {
			wikiStream.close();
			throw new Exception("Word not found!");
		} else {
			wikiStream.close();
			throw new Exception("Word not found!");
		}

		db.setWordStatus(myRequest, result);
		return result;
	}

	private String revertParticiple1(String myWord) {
		return myWord.substring(0, myWord.length() - 1);
	}

	private String revertParticiple2(String myWord) {
		return myWord.substring(2, myWord.length() - 1) + "en";
	}

	/** 
	 * This method starts the process with calling checkWord. If the result
	 * is wrong the word is checked again if it is a participle 1/2 and if
	 * so it's checked again
	 * @see org.textanalyzer.analyzer.dictionary.IDictionary#getWordStatus(java.lang.String)
	 */

	@SuppressWarnings("resource")
	@Override
	public WordStatus getWordStatus(String myWord) {

		WordStatus result = WordStatus.OTHER;
		// check the word status
		result = checkWord(myWord);

		// Just check other german word classes
		if (result == WordStatus.WRONG) {

			// Check whether the word is in participle I
			result = checkWord(revertParticiple1(myWord));
			if (result != WordStatus.WRONG) {
				return result;
			}

			// Check whether the word is in participle II
			String a = revertParticiple2(myWord);
			result = checkWord(a);
			if (result != WordStatus.WRONG) {
				return result;
			}

		}

		return result;
	}

	/**
	 * This method check if the word already exists in the locale database,
	 * if not, different dictionary's are requested.
	 * @param myWord
	 * @return WordStatus
	 */
	private WordStatus checkWord(String myWord) {

		System.out.println("\nLook up: " + myWord);
		/*
		 * lookup the word in locale database
		 */
		WordStatus dbWordStatus = db.getWordStatus(myWord);

		// If word exists in database, it's returned
		if (dbWordStatus != null) {
			System.out.println("DB: " + myWord + " | "
					+ dbWordStatus.toString());
			return dbWordStatus;
		}

		try {
			// Check the word in the dictionary "wiktionary"
			dbWordStatus = wiktionaryRequest(myWord);
			System.out.println("Wiktionary: " + myWord + " | "
					+ dbWordStatus.toString());
			return dbWordStatus;

		} catch (Exception e) {
			try {
				// If the wiktionary request throws a exception(WordStatus could
				// not be resolved, the Duden request is executed
				dbWordStatus = dudenRequest(myWord);
				System.out.println("Duden: " + myWord + " | "
						+ dbWordStatus.toString());
				return dbWordStatus;
			} catch (IOException e1) {
				// If the Duden request throws a exception(WordStatus could not
				// be resolved, the free dictionary request is executed
				try {
					dbWordStatus = freeDictionaryRequest(myWord);
					System.out.println("FreeDictionary: " + myWord + " | "
							+ dbWordStatus.toString());
					return dbWordStatus;
				} catch (Exception e2) {
					System.out.println("WRONG: " + myWord + " | "
							+ WordStatus.WRONG.toString());
					return WordStatus.WRONG;
				}
			}
		}
	}
}
