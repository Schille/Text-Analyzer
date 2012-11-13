package org.textanalyzer.database;

import java.util.ArrayList;

/**
 * Profile Information
 * @author Michael Schilonka
 * @author Maximilian Quellmalz
 * @version 12.11.2012
 */

/**
 * Setters and Getters for ProfileInformation
 */
public class ProfileInformation implements IProfileInformation {

	private long id;
	private String firstName;
	private String lastName;
	private int age;
	private String profession;
	private ArrayList<ResultSet> analyzedDocuments = new ArrayList<ResultSet>();

	@Override
	public void setId(long myID) {
		this.id = myID;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setAnalyzedDocuments(ArrayList<ResultSet> analyzedDocuments) {
		this.analyzedDocuments = analyzedDocuments;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String getProfession() {
		return profession;
	}

	@Override
	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public void addToAnalyzedDocuments(IResultSet myResultSet) {
		this.analyzedDocuments.add((ResultSet) myResultSet);

	}

	@Override
	public ArrayList<ResultSet> getAnalyzedDocuments() {
		return this.analyzedDocuments;
	}

}
