package org.textanalyzer.database;

import org.textanalyzer.analyzer.dictionary.WordStatus;

/**
 * Interface for database dictionary
 * @author Michael Schilonka
 * @version 12.11.2012
 */
public interface IDBDictionary {

	WordStatus getWordStatus(String myWord);
	
	void setWordStatus(String myWord, WordStatus myStatus);
	
}
