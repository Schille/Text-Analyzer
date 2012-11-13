/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.textanalyzer.analyzer.AnalyzeTaskInformation;
import org.textanalyzer.analyzer.Analyzer;
import org.textanalyzer.analyzer.IAnalyzeTaskInformation;
import org.textanalyzer.analyzer.TextMood;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IResultSet;

/**
 * @author Robert Stein
 *
 */
public class TestAnalyzer {

	@Test
	public void test() {
		IAnalyzeTaskInformation myTask = new AnalyzeTaskInformation();
		Document doc = new Document();
		doc.setText("A C & A lilalaune S Bär tanzt fröhlich durch Karlsruhe. Dabei denkt er sich: \"Wo ist eigentlich Hans?\" " +
				"Er vermisst ihn wirklich sehr. Manchmal geht er auf seinen Balkon und schaut traurig in die Ferne..." +
				"Der Bär ist trotzdem sehr zuversichtlich, dass er seinen Freund bald wiedersieht.");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Bär");
		list.add("lila");
		list.add("Hans");
		list.add("Karlsruhe");
		myTask.setWordList(list);
		myTask.setDocument(doc);
		
		// Do the analysis
		IResultSet result = (new Analyzer()).analyzeText(myTask);
		
		Map<String, Integer> testcustom = new HashMap<String, Integer>();
		testcustom.put("Bär", 2);
		testcustom.put("lila", 0);
		testcustom.put("Hans", 1);
		testcustom.put("Karlsruhe", 1);
		
		Map<String,Integer> testmostfre = new HashMap<String, Integer>();
		testmostfre.put("er", 3);
		testmostfre.put("Bär", 2);
		testmostfre.put("ist", 2);
		testmostfre.put("seinen", 2);
		
		
		assertEquals(43, result.getWordCount());
		assertEquals(TextMood.NEGATIVE, result.getTextMood());
		assertEquals("Bär", result.getMostFrequentNomen());
		assertEquals(1, result.getWrongWordCount());
		assertEquals(testcustom, result.getCustomWordCount());
		assertEquals(8, result.getAvaragePhraseLength());
		assertEquals(testmostfre, result.getMostFrequentWord(4));
		assertEquals(0, result.getPseudoIQ());
	
	}

}
