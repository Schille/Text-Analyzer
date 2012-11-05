/**
 *
 */
package org.textanalyzer.profileviewer;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.textanalyzer.database.DBHandle;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;

/**
 * @author Michael Schilonka & Robert Stein
 * 
 */
public class ProfileViewer implements IProfileViewer {

	private long userID;

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

	/*
	 * (non-Javadoc)
	 * 
=======
	
	/* (non-Javadoc)
>>>>>>> 2d8a2abfe674e8368e50354d78bef46eef8c5adf
	 * @see org.textanalyzer.profileviewer.IProfileViewer#getProfileViewer()
	 */
	@Override
	public JPanel getProfileViewer() {
		JPanel ground = new JPanel();
		JLabel headline = new JLabel();
		JLabel author = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JButton av_analyse = new JButton();
		JButton new_analyse = new JButton();
		JList texte = new JList();
		ArrayList<String> dataname = new ArrayList<String>();
		
		
		ground.setLayout(null);
		ground.setSize(500,400);
		
		headline.setText("Profilübersicht");
		headline.setFont(new Font("Dialog", 1 ,20));
		headline.setLocation(180, 3);
		headline.setSize(400, 35);
		
		author.setText("Name: "+profileInformation.getLastName()+", "+profileInformation.getFirstName());
		author.setLocation(40, 50);
		author.setSize(250,30);
		
		age.setText("Alter: "+profileInformation.getAge());
		age.setLocation(40,70);
		age.setSize(250, 30);
		
		profession.setText("Profession: "+profileInformation.getProfession());
		profession.setLocation(40,90);
		profession.setSize(250,30);
		
		new_analyse.setText("Neue Textanalyse");
		new_analyse.setSize(200, 50);
		new_analyse.setLocation(40,170);
		new_analyse.setFocusPainted(false);
		
		av_analyse.setText("Durchschnittsanalyse starten");
		av_analyse.setSize(200, 50);
		av_analyse.setLocation(40,240);
		av_analyse.setFocusPainted(false);
		
		texte.setSize(180, 300);
		texte.setLocation(280, 50);
		
		Iterator<?> result = resultSets.iterator();
		while(result.hasNext()) {
			IResultSet temp_res = (IResultSet)result.next();
			dataname.add("something");	
		}
		texte.setListData(dataname.toArray());
		
		ground.add(headline);
		ground.add(author);
		ground.add(age);
		ground.add(profession);
		ground.add(av_analyse);
		ground.add(new_analyse);
		ground.add(texte);

		return ground;
	}



	}


