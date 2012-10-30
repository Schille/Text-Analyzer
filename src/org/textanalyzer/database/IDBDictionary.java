/**
 * 
 */
package org.textanalyzer.database;

import org.textanalyzer.analyzer.dictionary.WordStatus;

/**
 * @author Michael Schilonk
 *
 */
public interface IDBDictionary {

	WordStatus getWordStatus(String myWord);
	
	void setWordStatus(String myWord, WordStatus myStatus);
	
}
