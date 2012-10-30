package org.textanalyzer.analyzer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.textanalyzer.analyzer.AnalyzeTaskInformation;
import org.textanalyzer.analyzer.IAnalyzeTaskInformation;
import org.textanalyzer.analyzer.IAnalyzer;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ResultSet;

public class Analyzer implements IAnalyzer {

	private IResultSet analysis;

	private class MyWord{
		protected String word;
		protected String punctuation;
		protected boolean inputEnd;
	}
	private class MySentence{
		protected String sentence;
		protected String punctuation;
		protected boolean inputEnd;
	}
	
	// run the analysis with some Test Data
	public static void main(String[] args) {new Analyzer().analyzeText(new AnalyzeTaskInformation());}
	
	@Override
	public IResultSet analyzeText(IAnalyzeTaskInformation myTask) {
		
		/* Creating the Result Set */
		this.analysis = new ResultSet();
		
		/* Get the text and transform to Stream */
		if(myTask.getDocument() == null || myTask.getDocument().getText() == null)
			throw new Error("No Document to analyze");
		Reader textReader = new StringReader(myTask.getDocument().getText());
		
		/* Do the analysis */
		try {
			analyze(textReader);
			textReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.analysis;
	}
	
	private MyWord analyzeWord(Reader input) throws IOException{
		// Buffer-Variables
		int character = input.read();
		MyWord word = new MyWord();
		word.inputEnd = false;
		word.word = "";
		
		// read the characters of a word
		while(character != ' ' && character != -1){
			// merge word
			word.word += (char) character;
			character = input.read();
		}
		if(character == -1){
			word.inputEnd = true;
		}
		word.punctuation = getPunctuation(word.word);
		if(word.punctuation != null){
			word.word = word.word.substring(0, word.word.length()-word.punctuation.length());
			// TODO Analyze the word
			System.out.println(word.word);
			System.out.println(word.punctuation);
		}
		return word;
	}
	
	private MySentence analyzeSentence(Reader input) throws IOException{
		// Set the punctuation
		MyWord word = analyzeWord(input);
		MySentence sentence = new MySentence();
		sentence.sentence = "";
		
		// Read the words of a sentence
		while(!word.inputEnd && word.punctuation == ""){
			sentence.sentence += word.word + " ";
			word = analyzeWord(input);
		}
		sentence.sentence += word.word;
		sentence.punctuation = word.punctuation;
		sentence.inputEnd = word.inputEnd;
		
		// TODO Analyze the sentence
		
		return sentence;
	}
	
	private String getPunctuation(String word){
		char[] punctuations = {'.', '!', '?'};
		char[] punct = new char[1];
		
		word.getChars(word.length()-1, word.length(), punct, 0);
		
		for (char c : punctuations) {
			if(c == punct[0]){
				return getPunctuation(word.substring(0, word.length()-1))+(new String(punct));
			}
		}
		return "";
	}
	
	private void analyze(Reader input) throws IOException{
		MySentence sentence = analyzeSentence(input);
		String text = "";
		while(sentence != null && !sentence.inputEnd){
			text += sentence.sentence + sentence.punctuation + " ";
			sentence = analyzeSentence(input);
		}
		text += sentence.sentence + sentence.punctuation;
		
		System.out.println(text);
	}

}
