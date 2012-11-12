package org.textanalyzer.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ProfileInformation;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.documentimporter.DocumentFormat;
import org.textanalyzer.profilemanager.ProfileManager;

public class TestFrontendProfileManager {
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
		profile.setFirstName("Klaus");
		profile.setLastName("Peter");
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
		//assertEquals(1001,test1.saveProfileInformation(profile));
		test1.saveResultSet(1000,result);
		test1.saveResultSet(1000, result1);
		//test1.saveResultSet(1001,result);
		//test1.saveResultSet(1001, result1);


//		c = test1.countClass("ProfileInformation");
//		d = test1.countClass("ResultSet");
//
//		assertEquals(2, c);
//		assertEquals(4, d);

		myProfile = test1.getProfileInformation(1000);
		myResultSets = test1.getAllResultSets(myProfile.getId());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		test1.removeProfile(myProfile.getId());
	
		
//		myProfile = test1.getProfileInformation(1001);
//		myResultSets = test1.getAllResultSets(myProfile.getId());
//		

//		
//		test1.removeObject(myProfile);
//		test1.removeObject(myResultSets.get(0));
//		test1.removeObject(myResultSets.get(1));


		c = test1.countClass("ProfileInformation");
		d = test1.countClass("ResultSet");

		assertEquals(0, c);
		assertEquals(0, d);
		System.out.println("Deleted the stuff");
	}
	@Test
	public void test() {
		ProfileManager test = new ProfileManager();
		System.out.println("Test");
	}

}
