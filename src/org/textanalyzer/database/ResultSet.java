package org.textanalyzer.database;

import java.util.HashMap;
import java.util.Map;

import org.textanalyzer.analyzer.TextMood;

/**
 * Profile Information
 * @author Michael Schilonka
 * @version 12.11.2012
 */

/**
 * Setters and Getters for ResultSet
 */
public class ResultSet implements IResultSet {

	private int wordCount;
	private int wrongWordCount;
	private HashMap<String, Integer> mostFrequentWord;
	private int pseudoIQ;
	private HashMap<String, Integer> customWordCount;
	private int avaragePhraseLength;
	private String mostFrequentNomen;
	private TextMood textMood;
	private IDocument document;
	private int id;

	public ResultSet() {
		mostFrequentWord = new HashMap<String, Integer>();
		customWordCount = new HashMap<String, Integer>();
	}

	// ----------------------------Getter
	// Setter------------------------------------------------------
	public void setDocument(IDocument myDocument) {
		document = myDocument;
	}

	public IDocument getDocument() {
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

//		LinkedHashMap<String, Integer> orderWords = new LinkedHashMap<String, Integer>();
//
//		for (int i = 0; i < myNumber
//				&& mostFrequentWord.entrySet().iterator().hasNext(); i++) {
//			Map.Entry<String, Integer> temp_entry = null;
//
//			temp_entry = mostFrequentWord.entrySet().iterator().next();
//
//			for (Map.Entry<String, Integer> entry : mostFrequentWord.entrySet()) {
//				if (temp_entry != null
//						&& temp_entry.getValue() <= entry.getValue())
//					temp_entry = entry;
//			}
//
//			mostFrequentWord.remove(temp_entry.getKey());
//			orderWords.put(temp_entry.getKey(), temp_entry.getValue());
//		}

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
		this.id = id;

	}

	public int getId() {
		return this.id;
	}

	// END----------------------------Getter
	// Setter------------------------------------------------------

}
