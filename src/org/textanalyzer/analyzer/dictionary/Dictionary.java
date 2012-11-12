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

	DBDictionary db;
	int i=0;
	public Dictionary() {
		db = new DBDictionary();
	}

	@SuppressWarnings("resource")
	public WordStatus dudenRequest(String myRequest) throws IOException {

		WordStatus result;
		String requestString;
		try {
			requestString = URLDecoder.decode("http://www.duden.de/rechtschreibung/" + myRequest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			requestString = "http://www.duden.de/rechtschreibung/" + myRequest;
		}

		URL url = new URL(requestString);
		InputStream stream = url.openStream();
		String dudenResponse = new Scanner(stream).useDelimiter("\\A").next();
		
		if (dudenResponse.contains("<span class=\"wortart\">Adjektiv"))
			result = WordStatus.ADJECTIV;
		else if (dudenResponse.contains("<div class=\"field-item even\">Adjektiv"))
			result = WordStatus.ADJECTIV;
		else if (dudenResponse.contains("<span class=\"wortart\">Substantiv"))
			result = WordStatus.NOMEN;
		else if (dudenResponse.contains("<div class=\"field-item even\">Substantiv"))
			result = WordStatus.NOMEN;
		else if (dudenResponse.contains("<div class=\"field-item even\">schwaches Verb"))
			result = WordStatus.VERB;
		else if (dudenResponse.contains("<span class=\"wortart\">schwaches Verb"))
			result = WordStatus.VERB;
		else if (dudenResponse.contains("<div class=\"field-item even\">starkes Verb"))
			result = WordStatus.VERB;
		else if (dudenResponse.contains("<span class=\"wortart\">starkes Verb"))
			result = WordStatus.VERB;
		else
			result = WordStatus.OTHER;

			db.setWordStatus(myRequest, result); // Set WordStatus in local Database
			return result;
	}
	
	public WordStatus freeDictionaryRequest(String myRequest) throws Exception {
		String requestString;
		try {
			requestString = URLDecoder.decode("http://de.thefreedictionary.com/" + myRequest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			requestString = "http://de.thefreedictionary.com/" + myRequest;
		}

		URL url = new URL(requestString);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		InputStream stream = connection.getInputStream();
		String freeResponse = new Scanner(stream).useDelimiter("\\A").next();
		
		freeResponse = freeResponse.replace("/"+myRequest, " ");
		freeResponse = freeResponse.replace(">"+myRequest, " ");
		if(freeResponse.contains("Das Wort konnte im Wörterbuch nicht gefunden werden.")){
			stream.close();
			throw new Exception("Word not found!");
		}
			
		db.setWordStatus(myRequest, WordStatus.OTHER); // Set WordStatus in local Database
		return WordStatus.OTHER;
	}
	
	private WordStatus wiktionaryRequest(String myRequest) throws Exception{
		WordStatus result;
		String requestString;
		try {
			requestString = URLDecoder.decode("http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles=" + myRequest, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			requestString = "http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles=" + myRequest;
		}
		
		URL url_wiktionary = new URL(requestString);
		InputStream wikiStream = url_wiktionary.openStream();
		String wikiResponse = new Scanner(wikiStream).useDelimiter("\\A")
					.next();

		if (wikiResponse.contains("Adjektiv"))
			result = WordStatus.ADJECTIV;
		else if (wikiResponse.contains("Personalpronomen"))
			result = WordStatus.OTHER;
		else if (wikiResponse.contains("Pr�position"))
			result = WordStatus.OTHER;
		else if (wikiResponse.contains("Substantiv"))
			result = WordStatus.NOMEN;
		else if (wikiResponse.contains("Verb")||wikiResponse.contains("Konjugierte Form"))
			result = WordStatus.VERB;
		else if (wikiResponse.contains("Kategorie:Deutsch"))
			result = WordStatus.OTHER;
		else if (wikiResponse.contains("missing\" \"")){
			wikiStream.close();
			throw new Exception("Word not found!");
		}
		else{
			wikiStream.close();
			throw new Exception("Word not found!");
		}
		
		db.setWordStatus(myRequest, result);
		return result;
	}
	
	private String revertParticiple1(String myWord){
		return myWord.substring(0, myWord.length() - 1);
	}
	
	private String revertParticiple2(String myWord){
		return myWord.substring(2, myWord.length() - 1) + "en";
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

		WordStatus result = WordStatus.OTHER;
		//check the word status
		result = checkWord(myWord);
		
		//Just check other german word classes
		if(result == WordStatus.WRONG){
			
			//Check whether the word is in participle I
			result = checkWord(revertParticiple1(myWord));
			if(result != WordStatus.WRONG){
				return result;
			}
			
			//Check whether the word is in participle II
			String a = revertParticiple2(myWord);
			result = checkWord(a);
			if(result != WordStatus.WRONG){
				return result;
			}
			
		}
		
		return result;
	}

	/**
	 * @param myWord
	 * @return
	 */
	private WordStatus checkWord(String myWord) {
		System.out.println("\nLook up: " +myWord);
		WordStatus dbWordStatus = db.getWordStatus(myWord);
		
		if(dbWordStatus != null){
			System.out.println("DB: " + myWord + " | " + dbWordStatus.toString());
			return dbWordStatus;
		}
			
		try{
			dbWordStatus = wiktionaryRequest(myWord);
			System.out.println("Wiktionary: "+ myWord + " | " + dbWordStatus.toString());
			return dbWordStatus;
			
		}catch (Exception e) {
				try {
					dbWordStatus = dudenRequest(myWord);
					System.out.println("Duden: "+ myWord + " | " + dbWordStatus.toString());
					return dbWordStatus;
				} catch (IOException e1) {
					try {
						dbWordStatus = freeDictionaryRequest(myWord);
						System.out.println("FreeDictionary: "+ myWord + " | " + dbWordStatus.toString());
						return dbWordStatus;
					} catch (Exception e2) {
						System.out.println("WRONG: "+ myWord + " | " + WordStatus.WRONG.toString());
						return WordStatus.WRONG;
					}
				}
		}
	}
}
