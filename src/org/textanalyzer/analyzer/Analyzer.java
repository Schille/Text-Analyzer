/**
 * @author Andreas Neumann, Robert Stein
 */

package org.textanalyzer.analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
	private boolean firstWord;

	IDictionary dict = new Dictionary();

	private String[] positiveWords = {"freudig", "gut", "hervorragend", "schön", "glücklich", "froh", "begeistert", "fröhlich", "glücklich","sorgenfrei", "sorgenlos", "fidel", "heiter", "vergnügt", "optimistisch", "unbetrübt","besonnen", "harmonisch", "zufrieden", "siegreich", "vielversprechend", "günstig", "positiv", "lieb", "freundlich", "lebenslustig", "lustig", "froh", "beflüglet", "drollig"};
	private String[] negativeWords = {"Hass","traurig", "schlecht", "bedrückt", "bekümmert", "betroffen", "betrübt", "depressiv", "deprimiert", "desolat", "düster", "freudlos", "negativ", "unangenehm", "quälend", "Schmerz", "Qual", "Jammer", "enttäuscht", "elend", "schwermütig", "Tod", "tot", "verzweifelt", "wütend", "zornig", "katastrophal", "trist", "trüb", "pessimistisch", "grauenvoll"};
	
	
	private Map<String, Integer> customWords = new HashMap<String, Integer>();
	private Map<String, Integer> fullWordList = new HashMap<String, Integer>();
	private Map<String, Integer> nomen = new HashMap<String, Integer>();
	private String text;
	private int textIndex = 0;
	
	/* Some internal classes */
	private class MyWord{
		protected String word = "";
		protected String punctuation = "";
	}
	private class MySentence{
		protected String sentence = "";
		//protected String punctuation = "";
	}
	
	public void textPreprocess() {
		text = text.replaceAll("[^a-zA-Z_._!_?_ä_ö_ü_ß_Ä_Ö_Ü]", " ");
		text = text.replaceAll("[.]+", ". ");
		text = text.replaceAll("[?]+", "? ");
		text = text.replaceAll("[!]+", "! ");
		for (int i=0; i<text.length()/2; i++ ){
			text = text.replaceAll("[^a-zA-Z_ä_ö_ü_ß_Ä_Ö_Ü]{1}[a-zA-Z_ä_ö_ü_ß_Ä_Ö_Ü]{1}[^a-zA-Z_ä_ö_ü_ß_Ä_Ö_Ü]{1}"," ");
		}
		if (text.substring(1, 2).matches(" ")){
			text = text.substring(2, text.length());
			text = text.replaceAll("\\s+"," ");
		}
		else {
			text = text.replaceAll("\\s+"," ");
		}
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
		textPreprocess();
		System.out.println("Analyzer:99 -> Text: " + text);
		/* Get the custom Words */
		customWords = new HashMap<String, Integer>();
		for (String customWord : myTask.getWordList()) {
			customWords.put(customWord, 0);
		}
		
		/* Do the analysis */
		while(analyzeNextSentence() != null)

		this.analysis.setWordCount(wordCount);
		this.analysis.setWrongWordCount(wrongWordCount);
		this.analysis.setPseudoIQ(0);
		if(sentenceCount > 0)
			this.analysis.setAvaragePhraseLength(wordCount/sentenceCount);
		else
			this.analysis.setAvaragePhraseLength(0);
		
		double wordc = wordCount;
		double sentc = sentenceCount;
		double wrongWordc = wrongWordCount;
		
		if(wordCount > 0)
			this.analysis.setPseudoIQ(
					(int) (100*((sentc*(wordc/sentc)-wrongWordc)/1000F)/((sentc*(wordc/sentc)-wrongWordc)/1000F+0.1F)));
		String [] freuquentWordArray = {"der", "die", "das", "dem", "den", "ein", "eine", "und", "oder", "weil", "Der", "Die", "Das", "Dem", "Den", "Ein", "Eine", "Und", "Oder", "Weil"};
		
		for (int i=freuquentWordArray.length;i>0;i--){
		if (fullWordList.containsValue(freuquentWordArray[i]))
			fullWordList.remove(freuquentWordArray[i]);
			}
		this.analysis.setMostFrequentWord(new HashMap<String, Integer>(fullWordList));
		
		this.analysis.setCustomWordCount(new HashMap<String, Integer>(customWords));
		this.analysis.setTextMood(textAttitude>=POSITIVEBORDER?TextMood.POSITIVE:textAttitude<=NEGATIVEBORDER?TextMood.NEGATIVE:TextMood.NEUTRAL);
		
		if(!nomen.isEmpty()) {
		SortedMap<Integer,String> orderFreNom = new TreeMap<Integer, String>(Collections.reverseOrder()); 		
		for (Entry<String, Integer> entry : nomen.entrySet()) {
			orderFreNom.put(entry.getValue(), entry.getKey());
		}
		
		this.analysis.setMostFrequentNomen(orderFreNom.get(orderFreNom.firstKey()));
		}
		else {
			this.analysis.setMostFrequentNomen("");
		}
		return this.analysis;
	}
	
	private MySentence analyzeNextSentence(){
		MyWord word;
		MySentence sentence = new MySentence();
		firstWord = true;
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
			if(attWord.contains(word.word)){
				textAttitude++;
				break;
			}
		}
		for (String attWord : negativeWords) {
			if(attWord.contains(word.word)){
				textAttitude--;
				break;
			}
		}
		
		// Get the punctuation
		for(;textIndex < text.length() && punctuations.contains(text.charAt(textIndex));textIndex++){
			word.punctuation = word.punctuation.concat(text.substring(textIndex, textIndex+1));
		}
	

		// Refresh the word counter
		wordCount++;
		System.out.println("Analyzer:211 -> WordCount: "+wordCount);
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
		if(firstWord == true && !Character.isUpperCase(word.word.charAt(0)))
				wrongWordCount++;
		else{
			status = WordStatus.WRONG;
			status = dict.getWordStatus(word.word);
			if(status == WordStatus.NOMEN){
				counter = nomen.remove(word.word);
				if(counter == null)
					nomen.put(word.word, 1);
				else
					nomen.put(word.word, ++counter);
			}else if(status == WordStatus.WRONG)
				if(firstWord == true) {
					status = dict.getWordStatus(word.word.toLowerCase());
					if(status == WordStatus.WRONG) {
						wrongWordCount++;
					}
					else {
						if(fullWordList.containsKey(word.word.toLowerCase()))
							fullWordList.put(word.word.toLowerCase(), fullWordList.get(word.word.toLowerCase())+1);
					}
				}
				else {
				wrongWordCount++;
				}
		}
		System.out.println("Analyzer:250 -> Wrong words: "+wrongWordCount);
		firstWord = false;
		return word;
	}

}
