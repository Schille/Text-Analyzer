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
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

/**
 * @author Michael Schilonka
 *
 */
public class DBDictionary implements IDBDictionary {

	
	private DBHandle connector;
	
	public DBDictionary(){
		connector = DBHandle.createDB();
	}
	
	/* (non-Javadoc)
	 * @see org.textanalyzer.database.IDBDictionary#getWordStatus(java.lang.String)
	 */
	@Override
	public WordStatus getWordStatus(String myWord) {

		List<DBWord> result = connector.command(
				  new OCommandSQL("select * from DBWord where word = ?")).execute(myWord);
		if(result.isEmpty())
			return null;
		return result.get(0).getWordStatus();
	}

	/* (non-Javadoc)
	 * @see org.textanalyzer.database.IDBDictionary#setWordStatus(java.lang.String, org.textanalyzer.analyzer.dictionary.WordStatus)
	 */
	@Override
	public void setWordStatus(String myWord, WordStatus myStatus) {
		if(getWordStatus(myWord) == null){
			connector.save(new DBWord(myWord, myStatus));
		}
	}
	


}
