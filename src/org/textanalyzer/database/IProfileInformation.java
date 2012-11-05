/**
 * 
 */
package org.textanalyzer.database;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Michael Schilonka
 *
 */
public interface IProfileInformation {

	String getFirstName();
	
	String getLastName();
	
	int getAge();
	
	String getProfession();
	
	ArrayList<ResultSet> getAnalyzedDocuments();
	
	
	void addToAnalyzedDocuments(IResultSet myResultSet);
	
	void setFirstName(String myFirstName);
	
	void setLastName(String myLastName);
	
	void setAge(int myAge);
	
	void setProfession(String myProfession);

	void setId(long myID);

	long getId();

	void setAnalyzedDocuments(ArrayList<ResultSet> analyzedDocuments);
}
