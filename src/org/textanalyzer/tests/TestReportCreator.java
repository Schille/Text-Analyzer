/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.junit.Test;
import org.textanalyzer.analyzer.TextMood;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;
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
			public void setAnalyzedDocuments(List<IResultSet> myResultSet) {
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
			public List<IResultSet> getAnalyzedDocuments() {
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
				return null;
			}
			
			@Override
			public String getMostFrequentNomen() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Map<String, Integer> getCustomWordCount() {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public int getAvaragePhraseLength() {
				// TODO Auto-generated method stub
				return 0;
			}
		}; 
		
		
		  JFrame frame = new JFrame("FrameChart");
		  frame.setPreferredSize(new Dimension(600, 700));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(reporter.getGraphPanel(daprofile, results));
	        frame.pack();
	        frame.setVisible(true);
	        
	        frame.setAlwaysOnTop(true);
	        frame.setAlwaysOnTop(true);
	}

}
