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
	
	long saveProfileInformation(IProfileInformation myObject);
	
	void saveResultSet(int myProfileID, IResultSet myObject);
	
	void removeObject(Object myObject);
	
	List<ProfileInformation> getAllProfiles();
	
	List<IResultSet> getAllResultSets(long myId);
	
	ProfileInformation getProfileInformation(int myId);
	
	void editProfile(int myId, ProfileInformation myProfile);
	
}
