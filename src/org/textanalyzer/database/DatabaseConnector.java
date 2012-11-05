/**
 * 
 */
package org.textanalyzer.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.orientechnologies.orient.core.command.OCommandRequest;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;



/**
 * @author Michael Schilonka & Maximilian Quellmalz
 *
 */
public class DatabaseConnector implements IDatabaseConnector{
	
	private DBHandle connector;
	
	public DatabaseConnector(){
		connector = DBHandle.createDB();
	}
	

	/**
	 * Creates an object in the database.
	 */
	@Override
	public void createObject(Object myObject) {
		connector.save(myObject);	
	}
	

	/**
	 * Removes a given object from database.
	 */
	@Override
	public void removeObject(Object myObject) {
		connector.delete(myObject);
	}
	

	/**
	 * Returns a List of all existing Profiles in the database.
	 */
	@Override
	public List<ProfileInformation> getAllProfiles() {
		 List<ProfileInformation> profileList = new LinkedList<ProfileInformation>();
		 // Array oder LinkedList
		 System.out.println("All Profiles");
		 for (ProfileInformation p : connector.browseClass(ProfileInformation.class)){
		 profileList.add(p);
		 }
		 return profileList;
	}

	

	@Override
	public ProfileInformation getProfileInformation(int myId) {

		ProfileInformation help = null;
		for (ProfileInformation p : connector.browseClass(ProfileInformation.class)){
			if (p.getId()==myId) {
				help=p;
			}			
			
		}
		return help;

	}

	
	@Override
	public void editProfile(int myId, ProfileInformation myProfile) {
		for (ProfileInformation p : connector.browseClass(ProfileInformation.class)){
			if (p.getId()==myId) {
				p = myProfile;
				connector.save(p);
			}			
		}
		
	}

	public long countClass(String myClassName){
		return connector.countClass(myClassName);
	}


	@Override
	public List<IResultSet> getAllResultSets(long myId) {
		 List<IResultSet> result = new LinkedList<IResultSet>();
		 for (ResultSet p : connector.browseClass(ResultSet.class)){
			 if(p.getId() == myId){
				 result.add(p);
		 	}
		 }
		 return result;
	}

}

