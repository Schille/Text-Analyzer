/**
 * 
 */
package org.textanalyzer.database;

import java.util.Map;

import org.textanalyzer.analyzer.TextMood;

/**
 * @author Michael Schilonka
 *
 */
public interface IResultSet {

	int getWordCount();
	
	int getWrongWordCount();
	
	Map<String,Integer> getMostFrequentWord(int myNumber); 
	
	int getPseudoIQ();
	
	Map<String,Integer> getCustomWordCount();
	
	int getAvaragePhraseLength();
	
	String getMostFrequentNomen();
	
	TextMood getTextMood();
	
	IDocument getDocument();
		
}
