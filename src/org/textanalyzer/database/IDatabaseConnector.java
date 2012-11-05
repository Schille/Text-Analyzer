/**
 * 
 */
package org.textanalyzer.database;

import java.util.List;

/**
 * @author Maximilian Quellmalz, Michael Schilonka
 *
 */
public interface IDatabaseConnector {
	
	void createObject(Object myObject);
	
	void removeObject(Object myObject);
	
	List<ProfileInformation> getAllProfiles();
	
	ProfileInformation getProfileInformation(int myId);
	
	void editProfile(int myId, ProfileInformation myProfile);
	
}
