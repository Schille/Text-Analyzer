package org.textanalyzer.database;

import java.util.HashMap;
import java.util.Map;

import org.textanalyzer.analyzer.TextMood;

public class ResultSet implements IResultSet {
	
	private int wordCount;
	private int wrongWordCount;
	private HashMap<String, Integer> mostFrequentWord = new HashMap<String, Integer>();
	private int pseudoIQ;
	private HashMap<String, Integer> customWordCount = new HashMap<String, Integer>();
	private int avaragePhraseLength;
	private String mostFrequentNomen;
	private TextMood textMood;
	
	
	protected HashMap<String, Integer> getMostFrequentWord() {
		return mostFrequentWord;
	}

	protected void setMostFrequentWord(HashMap<String, Integer> mostFrequentWord) {
		this.mostFrequentWord = mostFrequentWord;
	}

	protected void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	protected void setWrongWordCount(int wrongWordCount) {
		this.wrongWordCount = wrongWordCount;
	}

	protected void setPseudoIQ(int pseudoIQ) {
		this.pseudoIQ = pseudoIQ;
	}

	protected void setCustomWordCount(HashMap<String, Integer> customWordCount) {
		this.customWordCount = customWordCount;
	}

	protected void setAvaragePhraseLength(int avaragePhraseLength) {
		this.avaragePhraseLength = avaragePhraseLength;
	}

	protected void setMostFrequentNomen(String mostFrequentNomen) {
		this.mostFrequentNomen = mostFrequentNomen;
	}

	protected void setTextMood(TextMood textMood) {
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

}
