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
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ProfileInformation;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.frontend.FrontendProfileManager;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.record.ODatabaseRecord;

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
		ODatabaseRecordThreadLocal.INSTANCE.set((ODatabaseRecord) connector.getUnderlying().getUnderlying());
	}
	
	public void createProfile(ProfileInformation myProfile){
		long id = connector.saveProfileInformation(myProfile);
		mapper.add(connector.getProfileInformation((int) id));
	}
	
	public void removeProfile(int myIndex){
		ProfileInformation tobedeleted = connector.getProfileInformation((int) mapper.remove(myIndex).getId());
		connector.removeProfile(tobedeleted.getId());

	}
	
	public LinkedList<String> getAuthorList(){
		LinkedList<String> result = new LinkedList<String>();
		
		for(ProfileInformation key : mapper){
			result.add(key.getFirstName() + " " + key.getLastName());
		}
		
		return result;
	}
	
	public long getUserID(int myIndex){
		return mapper.get(myIndex).getId();
	}
	
	public void run(){
		while(profileGUI.isVisible()){
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		connector.closeDB();
		System.out.println("DB closed.");
		
	}
	
	
	public static void main(String[] args){
		ProfileManager manager = new ProfileManager();
		manager.run();
	}
	
	
}
