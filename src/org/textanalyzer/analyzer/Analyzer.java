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
		
		// TODO Remove
		/* Get the text and transform to Stream */
		//Reader textReader = new StringReader(myTask.getDocument().getText());
		
		/* Do the analysis */
		while(analyzeNextSentence() != null)
		
		/*try {
			analyze(textReader);
			textReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		analysis.setWordCount(wordCount);
		analysis.setWrongWordCount(wrongWordCount);
		analysis.setAvaragePhraseLength(wordCount/sentenceCount);
		analysis.setCustomWordCount(new HashMap<String, Integer>(customWords));
		analysis.setPseudoIQ(50); // TODO Calculate IQ
		//IQ = ((wordCount - wrongWordCount) * 100) / wordCount;
		analysis.setTextMood(TextMood.NEUTRAL); // TODO Calculate TextMood
		
		return this.analysis;
	}
	
	private MySentence analyzeNextSentence(){
		MyWord word;
		MySentence sentence = new MySentence();
		
		while(textIndex >= text.length()){
			sentence.sentence.concat(" ");
			sentence.sentence.concat((word = analyzeNextWord()).word);
			if(word.punctuation != "")
				break;
		}
		if(sentence.sentence.equals(""))
			return null;
		
		sentenceCount++;
		return sentence;
	}

	private MyWord analyzeNextWord(){
		MyWord word = new MyWord();
		
		List<Character> punctuations = new ArrayList<Character>();
		punctuations.add('.');
		punctuations.add('!');
		punctuations.add('?');
		
		while(textIndex <= text.length() && punctuations.contains(text.charAt(textIndex))){
			
			word.word.concat(text.substring(textIndex-1, textIndex));
		}
		if(word.word == "")
			return null;
		
		// TODO punctuation
		
		wordCount++;
		
		WordStatus status = dict.getWordStatus(word.word);
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
	
	/*private String getPunctuation(int index){
		char[] punctuations = {'.', '!', '?'};
		char[] punct = new char[1];
		
		text.getChars(index-1, index, punct, 0);
		
		for (char c : punctuations) {
			if(c == punct[0]){
				return getPunctuation(index+1);
			}
		}
		return "";
	}*/

}
