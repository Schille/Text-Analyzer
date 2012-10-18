/**
 * 
 */
package org.textanalyzer.profileviewer;

/**
 * @author mschilonka
 *
 */
public interface IProfileInformation {

	String getFirstName();
	
	String getLastName();
	
	int getAge();
	
	String getProfession();
	
	void setFirstName(String myFirstName);
	
	void setLastName(String myLastName);
	
	void setAge(int myAge);
	
	void setProfession(String myProfession);
}
