/**
 * 
 */
package org.textanalyzer.database;

import java.util.List;

import org.textanalyzer.analyzer.IResultSet;

/**
 * @author Michael Schilonka
 *
 */
public interface IProfileInformation {

	String getFirstName();
	
	String getLastName();
	
	int getAge();
	
	String getProfession();
	
	List<IResultSet> getAnalyzedDocuments();
	
	void setAnalyzedDocuments(List<IResultSet> myResultSet);
	
	void addToAnalyzedDocuments(IResultSet myResultSet);
	
	void setFirstName(String myFirstName);
	
	void setLastName(String myLastName);
	
	void setAge(int myAge);
	
	void setProfession(String myProfession);
}
