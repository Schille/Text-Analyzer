/**
 * 
 */
package org.textanalyzer.tests;

import java.awt.Dimension;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ProfileInformation;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.profileviewer.ProfileViewer;

/**
 * @author Robert Stein
 *
 */
public class TestProfileViewer {

	DatabaseConnector test1;
	ProfileInformation profile;
	ResultSet result1;
	ResultSet result;
	ProfileInformation myProfile;
	List<IResultSet> myResultSets;
	long c;
	long d;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 test1 = new DatabaseConnector();

		 profile = new ProfileInformation();
		profile.setAge(99999);
		profile.setFirstName("Peteasdasdr");
		profile.setLastName("Klauasdasds");
		profile.setProfession("Student");
		HashMap<String, Integer> most = new HashMap<String, Integer>();
		most.put("Haus", 20);
		most.put("Garten", 15);
		HashMap<String, Integer> custom = new HashMap<String, Integer>();
		custom.put("Schlauberger", 5);
		custom.put("Scheiße", 2);
		 result = new ResultSet();
		result.setAvaragePhraseLength(12);
		result.setPseudoIQ(40);
		result.setWordCount(500);
		result.setWrongWordCount(50);
		result.setMostFrequentWord(most);
		result.setCustomWordCount(custom);
		
				
				
		Document doc1 =		new Document(); 
		doc1.setFileName("stuff");
		doc1.setImportDate(new Date());
		result.setDocument(doc1);	
			
		 result1 = new ResultSet();
		result1.setAvaragePhraseLength(42);
		result1.setPseudoIQ(455);
		result1.setWordCount(5500);
		result1.setWrongWordCount(550);
		result1.setMostFrequentWord(most);
		result1.setCustomWordCount(custom);
		Document doc2 =		new Document(); 
		doc2.setFileName("funny");
		doc2.setImportDate(new Date());
		result1.setDocument(doc2);	

		test1.saveProfileInformation(profile);
		test1.saveResultSet(1000,result);
		test1.saveResultSet(1000, result1);
		test1.saveResultSet(1001,result);
		test1.saveResultSet(1001, result1);


		c = test1.countClass("ProfileInformation");
		d = test1.countClass("ResultSet");

		

		myProfile = test1.getProfileInformation(1000);
		myResultSets = test1.getAllResultSets(myProfile.getId());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		test1.removeProfile(myProfile.getId());
		


		c = test1.countClass("ProfileInformation");
		d = test1.countClass("ResultSet");

	
		System.out.println("Deleted the stuff");
	}

	@Test
	public void test() {
		ProfileViewer viewer = new ProfileViewer(1000);
		
		  JFrame frame = new JFrame("Viewer");
		  frame.setPreferredSize(new Dimension(500, 400));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(viewer.getProfileViewer());
	        frame.pack();
	        frame.setVisible(true);
	        
	        frame.isAlwaysOnTop();
	}

}
