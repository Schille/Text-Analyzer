/**
 * 
 */
package org.textanalyzer.database;

import org.textanalyzer.analyzer.dictionary.WordStatus;

/**
 * @author Michael Schilonk
 *
 */
public class DBWord {
	
	public DBWord(){}
	
	public DBWord(String myWord, WordStatus myWordStatus){
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
	 * @param word the word to set
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
	 * @param wordStatus the wordStatus to set
	 */
	public void setWordStatus(WordStatus wordStatus) {
		this.wordStatus = wordStatus;
	}
	private String word;
	private WordStatus wordStatus;
	
}
