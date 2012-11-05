/**
 * 
 */
package org.textanalyzer.profilemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.ProfileInformation;
import org.textanalyzer.frontend.FrontendProfileManager;

/**
 * @author Michael Schilonka
 *
 */
public class ProfileManager implements IProfileManager {

	private FrontendProfileManager profileGUI;
	private LinkedList<ProfileInformation> mapper;
	private DatabaseConnector connector;
	
	public ProfileManager(){
		connector = new DatabaseConnector();
		mapper = (LinkedList<ProfileInformation>) connector.getAllProfiles();
		profileGUI = new FrontendProfileManager(this);
	}
	
	
	public void createProfile(ProfileInformation myProfile){
		long id = connector.saveProfileInformation(myProfile);
		mapper.add(connector.getProfileInformation((int) id));
	}
	
	public void removeProfile(int myIndex){
		ProfileInformation tobedeleted = connector.getProfileInformation((int) mapper.remove(myIndex).getId());
		connector.removeObject(tobedeleted);
	}
	
	public LinkedList<String> getAuthorList(){
		LinkedList<String> result = new LinkedList<String>();
		
		for(ProfileInformation key : mapper){
			result.add(key.getFirstName() + " " + key.getLastName());
		}
		
		return result;
	}
	
	
}
