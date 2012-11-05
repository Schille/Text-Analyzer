/**
 * 
 */
package org.textanalyzer.profileviewer;

import java.util.List;

import javax.swing.JPanel;

import org.textanalyzer.database.DBHandle;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;

/**
 * @author Michael Schilonka
 *
 */
public class ProfileViewer implements IProfileViewer {

	private int userID;
	private JPanel ground;
	private DatabaseConnector connector;
	private IProfileInformation profileInformation;
	private List<IResultSet> resultSets;
	
	
	//-----------Constructor------------
	public ProfileViewer(int myUserID){
		userID = myUserID;
		connector = new DatabaseConnector();
		profileInformation = connector.getProfileInformation(myUserID);
		resultSets = connector.getAllResultSets(myUserID);
	}
	
	/* (non-Javadoc)
	 * @see org.textanalyzer.profileviewer.IProfileViewer#getProfileViewer()
	 */
	@Override
	public JPanel getProfileViewer() {
		return ground;
	}

}
