/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.textanalyzer.database.DatabaseConnector;
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
		profile.setId(100);
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
		ResultSet result = new ResultSet();
		result.setId(100);
		result.setAvaragePhraseLength(12);
		result.setPseudoIQ(40);
		result.setWordCount(500);
		result.setWrongWordCount(50);
		result.setMostFrequentWord(most);
		result.setCustomWordCount(custom);
		ResultSet result1 = new ResultSet();
		result1.setId(100);
		result1.setAvaragePhraseLength(42);
		result1.setPseudoIQ(455);
		result1.setWordCount(5500);
		result1.setWrongWordCount(550);
		result1.setMostFrequentWord(most);
		result1.setCustomWordCount(custom);

		test1.createObject(profile);
		test1.createObject(result);
		test1.createObject(result1);


		long c = test1.countClass("ProfileInformation");
		long d = test1.countClass("ResultSet");

		assertEquals(1, c);
		assertEquals(2, d);

		ProfileInformation myProfile = test1.getProfileInformation(100);
		List<IResultSet> myResultSets = test1.getAllResultSets(myProfile.getId());
		

		
		test1.removeObject(myProfile);
		test1.removeObject(myResultSets.get(0));
		test1.removeObject(myResultSets.get(1));


		c = test1.countClass("ProfileInformation");
		d = test1.countClass("ResultSet");

		assertEquals(0, c);
		assertEquals(0, d);

	}

}
