package org.textanalyzer.database;

import org.textanalyzer.analyzer.dictionary.WordStatus;

/**
 * Database Word
 * 
 * @author Michael Schilonka
 * @version 12.11.2012
 */
public class DBWord {

	private String word;
	private WordStatus wordStatus;

	public DBWord() {
	}

	public DBWord(String myWord, WordStatus myWordStatus) {
		word = myWord;
		wordStatus = myWordStatus;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the wordStatus
	 */
	public WordStatus getWordStatus() {
		return wordStatus;
	}

	/**
	 * @param wordStatus
	 *            the wordStatus to set
	 */
	public void setWordStatus(WordStatus wordStatus) {
		this.wordStatus = wordStatus;
	}

}
