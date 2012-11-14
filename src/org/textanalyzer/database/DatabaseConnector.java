package org.textanalyzer.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Database Connector
 * 
 * @author Michael Schilonka
 * @author Maximilian Quellmalz
 * @version 12.11.2012
 */

public class DatabaseConnector implements IDatabaseConnector {

	private OObjectDatabaseTx connector;

	/**
	 * Creates new instance of database.
	 * 
	 * @param connector
	 *            new instance of database
	 */
	public DatabaseConnector() {
		connector = DBHandle.getDB();
	}

	/**
	 * Overrides method removeObject from IDatabaseConnector. Removes a given
	 * object from database.
	 * 
	 * @see IDatabaseConnector#removeObject(Object)
	 */
	@Override
	public void removeObject(Object myObject) {
		connector.delete(myObject);
	}
	
	public void closeDB() {
		connector.close();
	}

	/**
	 * Removes a given profile (ProfileInformation & ResultSets) by a given Id
	 * from database.
	 */
	public void removeProfile(long myID) {
		ProfileInformation tobedeleted = getProfileInformation((int) myID);
		removeObject(tobedeleted);
		for (ResultSet p : connector.browseClass(ResultSet.class)) {
			if (p.getId() == myID) {
				removeObject(p);
				removeObject(p.getDocument());
			}
		}
	}

	/**
	 * Overrides function getAllProfiles from IDatabaseConnector. Returns a List
	 * of all existing profiles in the database.
	 * 
	 * @see IDatabaseConnector#getAllProfiles()
	 * @return list of all existing profiles
	 */
	@Override
	public List<ProfileInformation> getAllProfiles() {
		List<ProfileInformation> profileList = new LinkedList<ProfileInformation>();
		for (ProfileInformation p : connector
				.browseClass(ProfileInformation.class)) {
			ProfileInformation tmp = new ProfileInformation();
			tmp.setAge(p.getAge());
			tmp.setAnalyzedDocuments(p.getAnalyzedDocuments());
			tmp.setFirstName(p.getFirstName());
			tmp.setId(p.getId());
			tmp.setLastName(p.getLastName());
			tmp.setProfession(p.getProfession());
			profileList.add(tmp);
		}
		return profileList;
	}

	/**
	 * Overrides function getProfileInformation from IDatabaseConnector. Returns
	 * a an existing profile in the database by id.
	 * 
	 * @see IDatabaseConnector#getProfileInformation(int)
	 * @return profile by id
	 */
	@Override
	public ProfileInformation getProfileInformation(int myId) {
		ProfileInformation help = null;
		for (ProfileInformation p : connector
				.browseClass(ProfileInformation.class)) {
			if (p.getId() == myId) {
				help = p;
			}
		}
		return help;
	}

	/**
	 * Overrides method editProfile from IDatabaseConnector. Assigns new profile
	 * information to a given object of ProfileInformation by id.
	 * 
	 * @see IDatabaseConnector#editProfile(int, ProfileInformation)
	 */
	@Override
	public void editProfile(int myId, ProfileInformation myProfile) {
		for (ProfileInformation p : connector
				.browseClass(ProfileInformation.class)) {
			if (p.getId() == myId) {
				p = myProfile;
				connector.save(p);
			}
		}

	}

	/**
	 * Help function. Counts instances of a given class name in the database.
	 * 
	 * @return number of instances of given class
	 */
	public long countClass(String myClassName) {
		return connector.countClass(myClassName);
	}

	/**
	 * Overrides function getAllResultSets from IDatabaseConnector. Returns a
	 * List of all existing ResultSets by id in the database.
	 * 
	 * @see IDatabaseConnector#getAllResultSets(long)
	 * @return list of all existing result sets for a given id
	 */
	@Override
	public List<IResultSet> getAllResultSets(long myId) {
		List<IResultSet> result = new LinkedList<IResultSet>();
		for (ResultSet p : connector.browseClass(ResultSet.class)) {
			if (p.getId() == myId) {
				// result.add((IResultSet)
				// connector.getUserObjectByRecord((OIdentifiable) p,
				// "ResultSet"));
				ResultSet tmp = new ResultSet();
				tmp.setAvaragePhraseLength(p.getAvaragePhraseLength());
				tmp.setCustomWordCount((HashMap<String, Integer>) p
						.getCustomWordCount());
				tmp.setDocument(p.getDocument());
				tmp.setId(p.getId());
				tmp.setMostFrequentNomen(p.getMostFrequentNomen());
				tmp.setMostFrequentWord(p.getMostFrequentWord());
				tmp.setPseudoIQ(p.getPseudoIQ());
				tmp.setTextMood(p.getTextMood());
				tmp.setWordCount(p.getWordCount());
				tmp.setWrongWordCount(p.getWrongWordCount());
				result.add(tmp);
			}
		}
		return result;
	}

	/**
	 * Overrides function saveProfileInformation from IDatabaseConnector. Saves
	 * new profile information object in the database.
	 * 
	 * @see IDatabaseConnector#saveProfileInformation(IProfileInformation)
	 * @return id of saved object
	 */
	@Override
	public long saveProfileInformation(IProfileInformation myObject) {
		long helpid = 1000;

		if ((connector.countClass("ProfileInformation")) == 0) {
			helpid = 999;
		} else {
			for (ProfileInformation p : connector
					.browseClass(ProfileInformation.class)) {
				if (helpid < p.getId()) {
					helpid = p.getId();
				}
			}
		}
		myObject.setId(helpid + 1);
		System.out.println(helpid + 1);
		connector.save(myObject);
		return helpid + 1;
	}

	/**
	 * Overrides function saveResultSet from IDatabaseConnector. Saves new
	 * result set object in the database.
	 * 
	 * @see IDatabaseConnector#saveResultSet(int, IResultSet)
	 */
	@Override
	public void saveResultSet(int myProfileID, IResultSet myObject) {
		((ResultSet) myObject).setId(myProfileID);
		connector.save(myObject);
	}
	
	public ODatabaseDocument getUnderlying(){
		return connector.getUnderlying();
	}
}
