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

	private static int POSITIVEBORDER = 5;
	private static int NEGATIVEBORDER = 5;
	
	private ResultSet analysis;
	
	private int wordCount = 0;
	private int wrongWordCount = 0;
	private int sentenceCount = 0;
	private int textAttitude = 0;

	IDictionary dict = new Dictionary();
	private String[] positiveWords = {"freudig"};
	private String[] negativeWords = {"traurig"};
	
	private Map<String, Integer> customWords = new HashMap<String, Integer>();
	private Map<String, Integer> fullWordList = new HashMap<String, Integer>();
	private Map<String, Integer> nomen = new HashMap<String, Integer>();
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
	
	@Override
	public IResultSet analyzeText(IAnalyzeTaskInformation myTask) {
		/* Creating the Result Set */
		this.analysis = new ResultSet();
		
		/* Get the text */
		if(myTask.getDocument() == null || myTask.getDocument().getText() == null)
			throw new Error("No Document to analyze");
		else{
			text = myTask.getDocument().getText();
			this.analysis.setDocument(myTask.getDocument());
		}
		
		/* Get the custom Words */
		customWords = new HashMap<String, Integer>();
		for (String customWord : myTask.getWordList()) {
			customWords.put(customWord, 0);
		}
		
		/* Do the analysis */
		while(analyzeNextSentence() != null)

		this.analysis.setWordCount(wordCount);
		this.analysis.setWrongWordCount(wrongWordCount);
		if(sentenceCount > 0)
			this.analysis.setAvaragePhraseLength(wordCount/sentenceCount);
		else
			this.analysis.setAvaragePhraseLength(0);
		if(wordCount > 0)
			this.analysis.setPseudoIQ(((wordCount - wrongWordCount) * 100) / wordCount);
		else
			this.analysis.setPseudoIQ(0);
		this.analysis.setMostFrequentWord(new HashMap<String, Integer>(fullWordList));
		this.analysis.setCustomWordCount(new HashMap<String, Integer>(customWords));
		this.analysis.setTextMood(textAttitude>=POSITIVEBORDER?TextMood.POSITIVE:textAttitude<=NEGATIVEBORDER?TextMood.NEGATIVE:TextMood.NEUTRAL);
		
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
		Integer counter;
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
		if(word.word == "")
			return null;
		
		// Text Attitude
		for (String attWord : positiveWords) {
			if(attWord.equals(word.word)){
				textAttitude++;
				break;
			}
		}
		for (String attWord : negativeWords) {
			if(attWord.equals(word.word)){
				textAttitude--;
				break;
			}
		}
		
		// Get the punctuation
		if(punctuations.contains(text.charAt(textIndex))){
			for(;textIndex < text.length()&&punctuations.contains(text.charAt(textIndex));textIndex++){
				word.punctuation = word.punctuation.concat(text.substring(textIndex, textIndex+1));
			}
		}

		// Refresh the word counter
		wordCount++;
		counter = fullWordList.remove(word.word);
		if(counter == null)
			fullWordList.put(word.word, 1);
		else
			fullWordList.put(word.word, ++counter);

		// Refresh the custom word counter
		counter = customWords.remove(word.word);
		if(counter != null)
			customWords.put(word.word, ++counter);
		
		// Get Wrong words
		status = WordStatus.WRONG;
		status = dict.getWordStatus(word.word);
		if(status == WordStatus.NOMEN){
			counter = nomen.remove(word.word);
			if(counter == null)
				nomen.put(word.word, 1);
			else
				nomen.put(word.word, ++counter);
		}else if(status == WordStatus.WRONG)
			wrongWordCount++;
		return word;
	}

}
