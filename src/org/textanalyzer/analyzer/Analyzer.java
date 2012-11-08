package org.textanalyzer.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.textanalyzer.analyzer.dictionary.Dictionary;
import org.textanalyzer.analyzer.dictionary.IDictionary;
import org.textanalyzer.analyzer.dictionary.WordStatus;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ResultSet;

public class Analyzer implements IAnalyzer {

	private ResultSet analysis;
	
	private int wordCount = 0;
	private int wrongWordCount = 0;
	private int sentenceCount = 0;

	IDictionary dict = new Dictionary();
	
	private Map<String, Integer> customWords;
	private List<WordCounter> nomen = new ArrayList<WordCounter>();
	private String text;
	private int textIndex;
	
	/* Some internal classes */
	private class MyWord{
		protected String word = "";
		protected String punctuation = "";
	}
	private class MySentence{
		protected String sentence = "";
		//protected String punctuation = "";
	}
	private class WordCounter{
		private String word;
		protected int counter=0;
		private WordStatus family;
		protected WordCounter(String word, WordStatus family) {this.word=word;this.family=family;}
		protected String getWord(){return this.word;};
	}
	
	// run the analysis with some Test Data
	public static void main(String[] args) {new Analyzer().analyzeText(new AnalyzeTaskInformation());}
	
	@Override
	public IResultSet analyzeText(IAnalyzeTaskInformation myTask) {
		/* Creating the Result Set */
		this.analysis = new ResultSet();
		
		/* Get the text */
		if(myTask.getDocument() == null || myTask.getDocument().getText() == null)
			throw new Error("No Document to analyze");
		else{
			text = myTask.getDocument().getText();
			analysis.setDocument(myTask.getDocument());
		}
		
		/* Get the custom Words */
		customWords = new HashMap<String, Integer>();
		for (String customWord : myTask.getWordList()) {
			customWords.put(customWord, 0);
		}
		
		/* Do the analysis */
		while(analyzeNextSentence() != null)
		
			
		// TODO Create map for NomenCounter

		analysis.setWordCount(wordCount);
		analysis.setWrongWordCount(wrongWordCount);
		if(sentenceCount > 0)
			analysis.setAvaragePhraseLength(wordCount/sentenceCount);
		else
			analysis.setAvaragePhraseLength(0);
		analysis.setCustomWordCount(new HashMap<String, Integer>(customWords));
		if(wordCount > 0)
			analysis.setPseudoIQ(((wordCount - wrongWordCount) * 100) / wordCount);
		else
			analysis.setPseudoIQ(0);
		analysis.setTextMood(TextMood.NEUTRAL); // TODO Calculate TextMood
		
		return this.analysis;
	}
	
	private MySentence analyzeNextSentence(){
		MyWord word;
		MySentence sentence = new MySentence();
		
		// Get the words of a sentence
		while(textIndex <= text.length()){
			if((word = analyzeNextWord()) != null){
				sentence.sentence = sentence.sentence.concat(word.word + " ");
				if(word.punctuation != "")
					break;
			}else
				break;
		}
		if(sentence.sentence.equals(""))
			return null;
		
		// Refresh sentenceCounter
		sentenceCount++;
		
		// Mistakes at sentence beginning
		if(!Character.isUpperCase(sentence.sentence.charAt(0)))
			wrongWordCount++;
		return sentence;
	}

	private MyWord analyzeNextWord(){
		MyWord word = new MyWord();
		WordStatus status = null;
		
		// Set punctuation-chars
		List<Character> punctuations = new ArrayList<Character>();
		punctuations.add('.');
		punctuations.add('!');
		punctuations.add('?');
		
		// Concat the word until a Space or a punctuation occurs
		for(;textIndex < text.length();textIndex++){
			if((punctuations.contains(text.charAt(textIndex)) || text.charAt(textIndex) == ' ')){
				if(word.word != "")
					break;
				else{
					continue;
				}
			}
			word.word = word.word.concat(text.substring(textIndex, textIndex+1));
		}
		
		// End of Text
		if(word.word == "")
			return null;
		
		// Get the punctuation
		if(textIndex < text.length() && punctuations.contains(text.charAt(textIndex))){
			for(;punctuations.contains(text.charAt(textIndex));textIndex++){
				word.punctuation = word.punctuation.concat(text.substring(textIndex, textIndex+1));
			}
		}
		
		// Refresh the wordcounter
		wordCount++;
		
		// Get Wrong words
		//WordStatus status = dict.getWordStatus(word.word);
		status = WordStatus.WRONG;
		if(status == WordStatus.NOMEN){
			for (WordCounter nomen : this.nomen) {
				if(nomen.word.equals(nomen)){
					nomen.counter++;
					return word;
				}
			}
			nomen.add(new WordCounter(word.word, WordStatus.NOMEN));
		}else if(status == WordStatus.WRONG)
			wrongWordCount++;
		return word;
	}

}
