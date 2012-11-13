package org.textanalyzer.database;

import java.util.List;

import org.textanalyzer.analyzer.dictionary.WordStatus;

import com.orientechnologies.orient.core.sql.OCommandSQL;

/**
 * Database Dictionary
 * 
 * @author Michael Schilonka
 * @version 12.11.2012
 */
public class DBDictionary implements IDBDictionary {

	/**
	 * @param connector
	 *            object of type DBHandle
	 */
	private DBHandle connector;

	/**
	 * Constructor of DBHandle. Validates the database.
	 */
	public DBDictionary() {
		connector = DBHandle.createDB();
	}

	/**
	 * Returns the word status for a given word. Overrides function
	 * getWordStatus from IDBDictionary.
	 * 
	 * @see org.textanalyzer.database.IDBDictionary#getWordStatus(java.lang.String)
	 * @return word status of given word
	 */
	@Override
	public WordStatus getWordStatus(String myWord) {
		List<DBWord> result = connector.command(
				new OCommandSQL("select * from DBWord where word = ?"))
				.execute(myWord);
		if (result.isEmpty())
			return null;
		return result.get(0).getWordStatus();
	}

	/**
	 * Sets the word status for a word. Overrides method setWordStatus from
	 * IDBDictionary.
	 * 
	 * @see org.textanalyzer.database.IDBDictionary#setWordStatus(java.lang.String,
	 *      org.textanalyzer.analyzer.dictionary.WordStatus)
	 */
	@Override
	public void setWordStatus(String myWord, WordStatus myStatus) {
		if (getWordStatus(myWord) == null) {
			connector.save(new DBWord(myWord, myStatus));
		}
	}
}
