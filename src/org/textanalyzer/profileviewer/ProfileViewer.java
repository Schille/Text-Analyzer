/**
 *
 */
package org.textanalyzer.profileviewer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.textanalyzer.database.DBHandle;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.IDocument;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.documentimporter.DocumentImporter;
import org.textanalyzer.reportcreator.ReportCreator;

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
	private DocumentImporter importer;
	private ProfileViewer this_viewer;
	private JButton new_analyse;

	
	
	//-----------Constructor------------
	public ProfileViewer(int myUserID){
		
		userID = myUserID;
		connector = new DatabaseConnector();
		profileInformation = connector.getProfileInformation(myUserID);
		resultSets = connector.getAllResultSets(myUserID);
		importer = new DocumentImporter();
		this_viewer = this;
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public JPanel getProfileViewer() {
		JPanel ground = new JPanel();
		JLabel headline = new JLabel();
		JLabel author = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JButton av_analyse = new JButton();
		new_analyse = new JButton();
		JList texte = new JList();
		ArrayList<String> dataname = new ArrayList<String>();
		JLabel notext = new JLabel();
		
		
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
		
		notext.setText("Keine Texte vorhanden!");
		notext.setSize(200,30);
		notext.setLocation(280,50);
		
		new_analyse.setText("Neue Textanalyse");
		new_analyse.setSize(200, 50);
		new_analyse.setLocation(40,170);
		new_analyse.setFocusPainted(false);
		new_analyse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((JButton)arg0.getSource()).setEnabled(false);
				Thread a = new Thread(new Runnable() {
					
					@Override
					public void run() {
						importer.invokeNewDocumentImport(this_viewer);
						
					}
				});
				a.start();
			}
		});
		
		av_analyse.setText("Durchschnittsanalyse starten");
		av_analyse.setSize(200, 50);
		av_analyse.setLocation(40,240);
		av_analyse.setFocusPainted(false);
		
		av_analyse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IProfileInformation profileinfo = connector.getProfileInformation((int)userID);
				List<IResultSet>resultlist = connector.getAllResultSets(userID);
				ReportCreator reporter = new ReportCreator();
				
				  JFrame frame = new JFrame("Report");
				  frame.setPreferredSize(new Dimension(600, 700));
			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        frame.getContentPane().add(reporter.getGraphPanel(profileinfo, resultlist));
			        frame.pack();
			        frame.setVisible(true);

			        frame.setAlwaysOnTop(true);
				
			}
		});
		
		texte.setSize(180, 300);
		texte.setLocation(280, 50);
		
		Iterator<?> result = resultSets.iterator();
		if(result != null) {
		while(result.hasNext()) {
			IResultSet temp_res = (IResultSet)result.next();
			dataname.add(temp_res.getDocument().getFileName());	
		}
		texte.setListData(dataname.toArray());
		ground.add(texte);
		}
		else {
			ground.add(notext);
		}
		
		ground.add(headline);
		ground.add(author);
		ground.add(age);
		ground.add(profession);
		ground.add(av_analyse);
		ground.add(new_analyse);
	

		return ground;
	}

	@Override
	public void updateContent(IDocument myDocument) {
		//Start analysis here...
		System.out.println(myDocument.getText());
		this.new_analyse.setEnabled(true);
	}



	}


