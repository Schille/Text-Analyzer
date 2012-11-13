/**
 * 
 */
package org.textanalyzer.tests;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JFrame;

import org.junit.Test;
import org.textanalyzer.analyzer.TextMood;
import org.textanalyzer.database.IDocument;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.documentimporter.DocumentFormat;
import org.textanalyzer.reportcreator.ReportCreator;

/**
 * @author Robert Stein
 *
 */
public class TestReportCreator {

	
	@Test
	public void testgetGraphPanel(){
		ReportCreator reporter = new ReportCreator();
		//reporter.getGraphPanel(myProfile, myResultset);
		IProfileInformation daprofile = new IProfileInformation() {
			
			@Override
			public void setProfession(String myProfession) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setLastName(String myLastName) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFirstName(String myFirstName) {
				// TODO Auto-generated method stub
				
			}
			
			
			@Override
			public void setAge(int myAge) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getProfession() {
				// TODO Auto-generated method stub
				return "Lehrer";
			}
			
			@Override
			public String getLastName() {
				return "Peter";
			
			}
			
			@Override
			public String getFirstName() {
				return "Klaus";
			
			}
			
			@Override
			public ArrayList<ResultSet> getAnalyzedDocuments() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getAge() {
				// TODO Auto-generated method stub
				return 22;
			}
			
			@Override
			public void addToAnalyzedDocuments(IResultSet myResultSet) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setId(long myID) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public long getId() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setAnalyzedDocuments(
					ArrayList<ResultSet> analyzedDocuments) {
				// TODO Auto-generated method stub
				
			}
		};
		
		IResultSet results = new IResultSet() {
			
			@Override
			public int getWrongWordCount() {
				// TODO Auto-generated method stub
				return 100;
			}
			
			@Override
			public int getWordCount() {
				// TODO Auto-generated method stub
				return 500;
			}
			
			@Override
			public TextMood getTextMood() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getPseudoIQ() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Map<String, Integer> getMostFrequentWord(int myNumber) {
				// TODO Auto-generated method stub
				HashMap<String,Integer> k = new HashMap<String, Integer>();
				k.put("Mongo", 10);
				k.put("Master", 9);
				k.put("Kuppe", 8);
				k.put("Map", 7);
				k.put("Gitarre", 6);
				k.put("Instrument", 5);
				k.put("Flöte", 4);
				k.put("Hund", 3);
				k.put("Katze", 2);
				k.put("Hund", 100);
				
				return k;
			}
			
			@Override
			public String getMostFrequentNomen() {
				// TODO Auto-generated method stub
				return "Mongo";
			}
			
			@Override
			public Map<String, Integer> getCustomWordCount() {
				// TODO Auto-generated method stub
				HashMap<String,Integer> k = new HashMap<String, Integer>();
				k.put("Mongo", 100);
				k.put("Master", 90);
				k.put("Kuppe", 28);
				k.put("Map", 37);
				k.put("Gitarre", 60);
				k.put("Instrument", 51);
				k.put("Flöte", 42);
				k.put("Hund", 33);
				k.put("Katze", 22);
				k.put("Hund", 12);
				
				return k;
			}


			@Override
			public int getAvaragePhraseLength() {
				// TODO Auto-generated method stub
				return 7;
			}

			@Override
			public IDocument getDocument() {
				// TODO Auto-generated method stub
				return new IDocument() {
					
					@Override
					public String getText() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public Date getImportDate() {
						// TODO Auto-generated method stub
						return new Date();
					}
					
					@Override
					public String getFileName() {
						// TODO Auto-generated method stub
						return "filename";
					}
					
					@Override
					public String getDocumentPath() {
						// TODO Auto-generated method stub
						return "path to file";
					}
					
					@Override
					public DocumentFormat getDocumentFormat() {
						// TODO Auto-generated method stub
						return DocumentFormat.PDF;
					}
				};
			}
		}; 
		
		
IResultSet results2 = new IResultSet() {
			
			@Override
			public int getWrongWordCount() {
				// TODO Auto-generated method stub
				return 200;
			}
			
			@Override
			public int getWordCount() {
				// TODO Auto-generated method stub
				return 400;
			}
			
			@Override
			public TextMood getTextMood() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getPseudoIQ() {
				// TODO Auto-generated method stub
				return 50;
			}
			
			@Override
			public Map<String, Integer> getMostFrequentWord(int myNumber) {
				// TODO Auto-generated method stub
				HashMap<String,Integer> k = new HashMap<String, Integer>();
				k.put("Mongo", 10);
				k.put("Master", 9);
				k.put("Kuppe", 8);
				k.put("Map", 7);
				k.put("Gitarre", 6);
				k.put("Instrument", 5);
				k.put("Flöte", 4);
				k.put("Hund", 3);
				k.put("Katze", 2);
				k.put("Hunde", 200);
				
				return k;
			}
			
			@Override
			public String getMostFrequentNomen() {
				// TODO Auto-generated method stub
				return "Mongo";
			}
			
			@Override
			public Map<String, Integer> getCustomWordCount() {
				// TODO Auto-generated method stub
				HashMap<String,Integer> k = new HashMap<String, Integer>();
				k.put("Mongo", 100);
				k.put("Master", 90);
				k.put("Kuppe", 28);
				k.put("Map", 37);
				k.put("Gitarre", 60);
				k.put("Instrument", 51);
				k.put("Flöte", 42);
				k.put("Hund", 33);
				k.put("Katze", 22);
				k.put("Hund", 12);
				
				return k;
			}


			@Override
			public int getAvaragePhraseLength() {
				// TODO Auto-generated method stub
				return 7;
			}

			@Override
			public IDocument getDocument() {
				// TODO Auto-generated method stub
				return new IDocument() {
					
					@Override
					public String getText() {
						// TODO Auto-generated method stub
						return null;
					}
					
					@Override
					public Date getImportDate() {
						// TODO Auto-generated method stub
						return new Date();
					}
					
					@Override
					public String getFileName() {
						// TODO Auto-generated method stub
						return "filename";
					}
					
					@Override
					public String getDocumentPath() {
						// TODO Auto-generated method stub
						return "path to file";
					}
					
					@Override
					public DocumentFormat getDocumentFormat() {
						// TODO Auto-generated method stub
						return DocumentFormat.PDF;
					}
				};
			}
		}; 
		
		LinkedList<IResultSet> testlist = new LinkedList<IResultSet>();
		testlist.add(results);
		testlist.add(results2);
		
		
		  JFrame frame = new JFrame("Report");
		  frame.setPreferredSize(new Dimension(600, 700));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(reporter.getGraphPanel(daprofile, testlist));
	        frame.pack();
	        frame.setVisible(true);

	        frame.setAlwaysOnTop(true);
	        
	  	  JFrame frame2 = new JFrame("Report");
		  frame2.setPreferredSize(new Dimension(600, 700));
	        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame2.getContentPane().add(reporter.getGraphPanel(daprofile, results));
	        frame2.pack();
	        frame2.setVisible(true);

	        frame2.setAlwaysOnTop(true);
	}

}
