package org.textanalyzer.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.text.MaskFormatter;

import org.textanalyzer.database.ProfileInformation;
import org.textanalyzer.profilemanager.ProfileManager;


public class AuthorRegistration extends JPanel {

    private javax.swing.JLabel headLine = new JLabel("Anlegen eines neuen Autors");
    private javax.swing.JLabel firstNameLabel = new JLabel("Vorname: ");
    private javax.swing.JLabel lastNameLabel = new JLabel("Nachname: ");
    private javax.swing.JLabel professionLabel = new JLabel("Beruf: ");
    private javax.swing.JLabel ageLabel = new JLabel("Alter: ");
    private javax.swing.JButton submit = new JButton("Autor anlegen");
    private javax.swing.JTextField firstNameField = new JTextField();
    private javax.swing.JTextField lastNameField = new JTextField();
    private javax.swing.JTextField professionField = new JTextField();
    private javax.swing.JTextField ageField = new JTextField();
    private ProfileManager manager;
    private FrontendProfileManager frontendManager;

   
    public AuthorRegistration(ProfileManager myManager, FrontendProfileManager myProfManager){
    	manager = myManager;
    	frontendManager = myProfManager;
    	setLayout(null);
		setSize(500, 400);
		setBackground(Color.white);
		
		//set the headline
    	headLine.setBounds(86,27,317,33);
    	headLine.setFont(new Font("Arial",0,24));
    	
    	//set the Labels that inform the users about the needed input
    	firstNameLabel.setBounds(100,100,93,22);
    	firstNameLabel.setFont(new Font("Arial",0,16));
    	lastNameLabel.setBounds(100,150,93,22);
    	lastNameLabel.setFont(new Font("Arial",0,16));
    	professionLabel.setBounds(100,200,93,22);
    	professionLabel.setFont(new Font("Arial",0,16));
    	ageLabel.setBounds(100,250,93,22);
    	ageLabel.setFont(new Font("Arial",0,16));
    	
    	
    	//set the three text fields
    	firstNameField.setBounds(200,100,190,25);
    	lastNameField.setBounds(200,150,190,25);
    	professionField.setBounds(200,200,190,25);
    	ageField.setBounds(200,250,190,25);
    	
    	//set the Button to submit the input
    	submit.setBounds(200,300,130,30);
    	submit.setForeground(Color.white);
    	submit.setBackground(Color.red);
    	submit.setFont(new Font("Arial",0,15));
    	
		
		// add an ActionListener to the Button
    	submit.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			submitButtonActionPerformed(evt);
    		}
    	});
    	
		
	
		add(headLine);
		add(firstNameField);
		add(lastNameField);
		add(professionField);
		add(ageField);
    	add(firstNameLabel);
    	add(lastNameLabel);
    	add(professionLabel);
    	add(ageLabel);
    	add(submit);
    	setVisible(true);
    	
    	
    }
    
    


	protected void submitButtonActionPerformed(ActionEvent evt) {
		
		ProfileInformation myProfileInfo = new ProfileInformation();
		String authorFirstName = firstNameField.getText();
		String authorLastName = lastNameField.getText();
		String profession = professionField.getText();
		
		
		if (validateString(authorFirstName)){
			myProfileInfo.setFirstName(authorFirstName);
	
			
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie den Vornamen des Autors an. Achten Sie bitte dabei darauf, dass er nur Buchstaben enth\u00e4lt und keine Zahlen oder Sonderzeichen.", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		if (validateString(authorLastName)){
			myProfileInfo.setLastName(authorLastName);
			
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie den Nachnamen des Autors an. Achten Sie bitte dabei darauf, dass er nur Buchstaben enth\u00e4lt und keine Zahlen oder Sonderzeichen.", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		if (validateString(profession)){
			myProfileInfo.setProfession(profession);
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Beruf an. Achten Sie bitte dabei darauf, dass er nur Buchstaben enth\u00e4lt und keine Zahlen oder Sonderzeichen.", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		validateAge(ageField.getText());
		if (validateAge(ageField.getText())){
			int age = new Integer(ageField.getText());
			myProfileInfo.setAge(age);
			
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie ein Alter an. Achten Sie bitte darauf, dass Sie nur Zahlen verwenden.", "Fehler", JOptionPane.ERROR_MESSAGE);

		}
		manager.createProfile(myProfileInfo);
		frontendManager.fillList();
		firstNameField.setText("");
		lastNameField.setText("");
		professionField.setText("");
		ageField.setText("");
		
		}


	private boolean validateAge(String input) {
	
			return input.matches("^[0-9]+$");
			
		
	}


	private boolean validateString(String input) {
			return input.matches("^[a-zA-Z]+$");
			}

}

