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
	
	void createProfile(IProfileInformation myProfil);
	
	void removeProfile(IProfileInformation myProfil);
	
	List<IProfileInformation> getAllProfiles();
	
	IProfileInformation getProfileInformation(int myId);
	
	void editProfile(int myId, IProfileInformation myProfile);
	
}
