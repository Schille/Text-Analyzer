/**
 * 
 */
package org.textanalyzer.analyzer;

import java.util.Map;

/**
 * @author Andreas Neumann
 *
 */
public interface IResultSet {

	int getWordCount();
	
	int getWrongWordCount();
	
	Map<String,Integer> getMostFrequentWord(int myNumber); 
	
	int getPseudoIQ();
	
	Map<String,Integer> getCustomWordCount();
	
	int avaragePhraseLength();
	
	String getMostFrequentNomen();
	
	TextMood getTextMood();
		
}
