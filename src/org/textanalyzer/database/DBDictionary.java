/**
 * 
 */
package org.textanalyzer.database;

import java.util.List;

import org.textanalyzer.analyzer.dictionary.WordStatus;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * @author Michael Schilonka
 *
 */
public class DBDictionary implements IDBDictionary {

	/* (non-Javadoc)
	 * @see org.textanalyzer.database.IDBDictionary#getWordStatus(java.lang.String)
	 */
	@Override
	public WordStatus getWordStatus(String myWord) {
		return null;

		
	}

	/* (non-Javadoc)
	 * @see org.textanalyzer.database.IDBDictionary#setWordStatus(java.lang.String, org.textanalyzer.analyzer.dictionary.WordStatus)
	 */
	@Override
	public void setWordStatus(String myWord, WordStatus myStatus) {
		ODocument word = new ODocument("Word");
		word.field("Word", myWord);
		word.field("WordStatus", myStatus);
		word.save();
	}
	


}
