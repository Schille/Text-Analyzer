package org.textanalyzer.database;

import java.util.List;

/**
 * Interface for database connection
 * 
 * @author Michael Schilonka
 * @author Maximilian Quellmalz
 * @version 12.11.2012
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
