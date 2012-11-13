/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ProfileInformation;
import org.textanalyzer.database.ResultSet;

/**
 * @author Michael Schilonka
 * 
 */
public class TestDatabaseConnector {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		DatabaseConnector test1 = new DatabaseConnector();

		ProfileInformation profile = new ProfileInformation();
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
		
		
		ResultSet result = new ResultSet();
		result.setAvaragePhraseLength(12);
		result.setPseudoIQ(40);
		result.setWordCount(500);
		result.setWrongWordCount(50);
		result.setMostFrequentWord(most);
		result.setCustomWordCount(custom);

		Document doc1 = new Document();
		doc1.setFileName("Datei1.pdf");
		
		result.setDocument(doc1);
		
//		ResultSet result1 = new ResultSet();
//		result1.setAvaragePhraseLength(42);
//		result1.setPseudoIQ(455);
//		result1.setWordCount(5500);
//		result1.setWrongWordCount(550);
//		result1.setMostFrequentWord(most);
//		result1.setCustomWordCount(custom);

		assertEquals(1000,test1.saveProfileInformation(profile));
		//assertEquals(1001,test1.saveProfileInformation(profile));
		test1.saveProfileInformation(profile);
		test1.saveProfileInformation(profile);
		test1.saveResultSet(1000,result);
		test1.saveResultSet(1000,result);
//		test1.saveResultSet(1000, result1);
//		test1.saveResultSet(1001, result1);


		long c = test1.countClass("ProfileInformation");
		long d = test1.countClass("ResultSet");
		System.out.println(c);
		System.out.println(d);
	

		assertEquals(3, c);
		assertEquals(2, d);

		ProfileInformation myProfile = test1.getProfileInformation(1000);
		List<IResultSet> myResultSets = test1.getAllResultSets(myProfile.getId());
		
		
		assertNotNull(myResultSets.get(0).getDocument());
		assertEquals("Datei1.pdf", myResultSets.get(0).getDocument().getFileName());

		//test1.removeObject(myResultSets.get(0).getDocument());
		//test1.removeObject(myResultSets.get(0));
		//test1.removeObject(myResultSets.get(1));
		//test1.removeObject(myProfile);
		//test1.removeObject(myResultSets.get(0));
		//test1.removeObject(myResultSets.get(0).getDocument());
		
		test1.editProfile(1000, myProfile);
		System.out.println(test1.getAllProfiles().get(0).getAge());
		
		test1.removeProfile(1000);
		test1.removeProfile(1001);

		test1.saveProfileInformation(profile);

		
		


		
//		myProfile = test1.getProfileInformation(1001);
//		myResultSets = test1.getAllResultSets(myProfile.getId());

//		test1.removeObject(myResultSets.get(0));
//		test1.removeObject(myProfile);



		c = test1.countClass("ProfileInformation");
		d = test1.countClass("ResultSet");
		long e = test1.countClass("Document");

		assertEquals(2, c);
		assertEquals(0, d);
		assertEquals(0, e);

	}

}
