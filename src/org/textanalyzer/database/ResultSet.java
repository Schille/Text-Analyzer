package org.textanalyzer.database;

import java.util.HashMap;
import java.util.Map;

import org.textanalyzer.analyzer.TextMood;

/**
 * @author Michael Schilonka
 *
 */
public class ResultSet implements IResultSet {
	
	private int wordCount;
	private int wrongWordCount;
	private HashMap<String, Integer> mostFrequentWord = new HashMap<String, Integer>();
	private int pseudoIQ;
	private HashMap<String, Integer> customWordCount = new HashMap<String, Integer>();
	private int avaragePhraseLength;
	private String mostFrequentNomen;
	private TextMood textMood;
	private IDocument document;
	private int id;
	
	
	//----------------------------Getter Setter------------------------------------------------------
	public void setDocument(IDocument myDocument){
		document = myDocument;
	}
	
	public IDocument getDocument(){
		return document;
	}

	public void setMostFrequentWord(HashMap<String, Integer> mostFrequentWord) {
		this.mostFrequentWord = mostFrequentWord;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public void setWrongWordCount(int wrongWordCount) {
		this.wrongWordCount = wrongWordCount;
	}

	public void setPseudoIQ(int pseudoIQ) {
		this.pseudoIQ = pseudoIQ;
	}

	public void setCustomWordCount(HashMap<String, Integer> customWordCount) {
		this.customWordCount = customWordCount;
	}

	public void setAvaragePhraseLength(int avaragePhraseLength) {
		this.avaragePhraseLength = avaragePhraseLength;
	}
	
	public void setMostFrequentNomen(String mostFrequentNomen) {
		this.mostFrequentNomen = mostFrequentNomen;
	}

	public void setTextMood(TextMood textMood) {
		this.textMood = textMood;
	}


	@Override
	public int getWordCount() {
		return wordCount;
	}

	@Override
	public int getWrongWordCount() {
		return wrongWordCount;
	}

	@Override
	public Map<String, Integer> getMostFrequentWord(int myNumber) {
		return mostFrequentWord;
	}

	@Override
	public int getPseudoIQ() {
		return pseudoIQ;
	}

	@Override
	public Map<String, Integer> getCustomWordCount() {
		return customWordCount;
	}

	@Override
	public int getAvaragePhraseLength() {
		return avaragePhraseLength;
	}

	@Override
	public String getMostFrequentNomen() {
		return mostFrequentNomen;
	}

	@Override
	public TextMood getTextMood() {
		return textMood;
	}
	
	public HashMap<String, Integer> getMostFrequentWord() {
		return mostFrequentWord;
	}

	public void setId(int id) {
		this.id=id;
		
	}
	
	public int getId(){
		return this.id;
	}



	//END----------------------------Getter Setter------------------------------------------------------

}
