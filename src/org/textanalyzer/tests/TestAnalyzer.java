/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.textanalyzer.analyzer.Analyzer;
import org.textanalyzer.analyzer.IAnalyzeTaskInformation;
import org.textanalyzer.analyzer.TextMood;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IDocument;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.documentimporter.DocumentFormat;

/**
 * @author Robert Stein
 *
 */
public class TestAnalyzer {

	@Test
	public void test() {
		Analyzer analyzer = new Analyzer();
		
		IResultSet testit = analyzer.analyzeText(new IAnalyzeTaskInformation() {

			
			@Override
			public void setWordList(List<String> myWordList) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setProfile(IProfileInformation myProfile) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setDocument(IDocument myDocument) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public List<String> getWordList() {
				ArrayList<String> list = new ArrayList<String>();
				list.add("Bär");
				list.add("lila");
				list.add("Hans");
				list.add("Karlsruhe");
						return list;
			}
			
			@Override
			public IProfileInformation getProfile() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public IDocument getDocument() {
				Document doc = new Document() {

					@Override
					public String getDocumentPath() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Date getImportDate() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public DocumentFormat getDocumentFormat() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public String getText() {
						
						return "Der & lilalaune Bär tanz fröhlich durch Karlsruhe. Dabei denkt er sich: \"Wo ist eigentlich Hans?\" " +
								"Er vermisst ihn wirklich sehr. Manchmal geht er auf seinen Balkon und schaut traurig in die Ferne..." +
								"Der Bär ist trotzdem sehr zuversichtlich, dass er seinen Freund bald wiedersieht.";
					}

					@Override
					public String getFileName() {
						// TODO Auto-generated method stub
						return null;
					}
					
				};
				return doc;
			}
		});
	
		
		assertEquals(44, testit.getWordCount());
		assertEquals(TextMood.NEGATIVE, testit.getTextMood());
		assertEquals("Bär", testit.getMostFrequentNomen());
	
	}

}
