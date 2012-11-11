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

/*
 * the class AuthorRegistration acts as a JPanel which should allow the user to add an author to the software
 * therefore the user has to enter four facts of the author: the first name, the last name, the profession an the age
 * by clicking on the submit button the author and his/her characteristics should be added to the list 
 * therefore the class need the class ProfileManager which the AuthorRegistration class pass the author and his/her characteristics
 */

public class AuthorRegistration extends JPanel {

	/* 
	 * setting all the variables which should appear on the JLabel
	 * @param headLine JLabel which gives the user the overview of the functionality of the side
	 * @param firstNameLabel, lastNameLabel, professionLabel, ageLabel : JLabels which inform the user which input they should type in the text field aside
	 * @param firstNameField, lastNameField, professionNameField, ageField : JFields which will get the input of the user that will describe the author
	 */
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
    
    /*
     * constructor which is responsible to create the JPanel which allows to input the needed data
     */
    
   
    public AuthorRegistration(ProfileManager myManager, FrontendProfileManager myProfManager){
    	manager = myManager;
    	frontendManager = myProfManager;
    	setLayout(null);
		setSize(500, 400);
		setBackground(Color.white);
		
		/*
		 * set the headline
		 */
    	headLine.setBounds(86,27,317,33);
    	headLine.setFont(new Font("Arial",0,24));
    	
    	/*
    	 * set the Labels that inform the users about the needed input
    	 */
    	firstNameLabel.setBounds(100,100,93,22);
    	firstNameLabel.setFont(new Font("Arial",0,16));
    	lastNameLabel.setBounds(100,150,93,22);
    	lastNameLabel.setFont(new Font("Arial",0,16));
    	professionLabel.setBounds(100,200,93,22);
    	professionLabel.setFont(new Font("Arial",0,16));
    	ageLabel.setBounds(100,250,93,22);
    	ageLabel.setFont(new Font("Arial",0,16));
    	
    	
    	/*
    	 * set the three text fields
    	 */
    	firstNameField.setBounds(200,100,190,25);
    	lastNameField.setBounds(200,150,190,25);
    	professionField.setBounds(200,200,190,25);
    	ageField.setBounds(200,250,190,25);
    	
    	/*
    	 * set the Button to submit the input
    	 */
    	submit.setBounds(200,300,130,30);
    	submit.setForeground(Color.white);
    	submit.setBackground(Color.red);
    	submit.setFont(new Font("Arial",0,15));
    	
		
		/*
		 *  add an ActionListener to the Button
		 */
    	submit.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			submitButtonActionPerformed(evt);
    		}
    	});
    	
		
    	/*
    	 * add all created elements to the JPanel and set it visible
    	 */
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
    
    
    /*
     * submitButtonActionPerformed is the method performed when the submit button is clicked
     * 
     */

	protected void submitButtonActionPerformed(ActionEvent evt) {
		
		ProfileInformation myProfileInfo = new ProfileInformation();
		
		/*
		 * @param authorFirstName, authorLastname, profession gets the input that the user writes into the JTextFields
		 */
		String authorFirstName = firstNameField.getText();
		String authorLastName = lastNameField.getText();
		String profession = professionField.getText();
		
		/* 
		 * the three variables (authorFirstName, authorLastname, profession) will be checked with the methods validateString
		 * if validateString is true the variables are passed to the ProfileManager
		 * if not the user gets an error message
		 */
		
		if (validateString(authorFirstName)){
			myProfileInfo.setFirstName(authorFirstName);
	
			
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie den Vornamen des Autors an. Achten Sie bitte dabei darauf, dass er nur Buchstaben enth\u00e4lt und keine Zahlen oder Sonderzeichen.", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
		
		if (validateString(authorLastName)){
			myProfileInfo.setLastName(authorLastName);
			
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie den Nachnamen des Autors an. Achten Sie bitte dabei darauf, dass er nur Buchstaben enth\u00e4lt und keine Zahlen oder Sonderzeichen.", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (validateString(profession)){
			myProfileInfo.setProfession(profession);
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Beruf an. Achten Sie bitte dabei darauf, dass er nur Buchstaben enth\u00e4lt und keine Zahlen oder Sonderzeichen.", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/*
		 * the ageField is validated with the method validateAge
		 * if it delivers true the text can be passed into an integer without problems and it will be passed to the ProfileManager
		 * otherwise the user is informed about that problem
		 */
		if (validateAge(ageField.getText())){
			int age = new Integer(ageField.getText());
			myProfileInfo.setAge(age);
			
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie ein Alter an. Achten Sie bitte darauf, dass Sie nur Zahlen verwenden.", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		manager.createProfile(myProfileInfo);
		frontendManager.fillList();
		
		/*
		 * after the submit the text fields should be empty again
		 */
		firstNameField.setText("");
		lastNameField.setText("");
		professionField.setText("");
		ageField.setText("");
		
		}


		/*
		 * the method validateAge checks with a regular expression if the given String just contains numbers
		 */
	private boolean validateAge(String input) {
	
			return input.matches("^[0-9]+$");
			
		
	}


		/*
		 * the method validateString checks with a regular expression if the given String just contains letter
		 * since the software is made for Germans also the "umlaute" and the ß is allowed for the inut 
		 */
	private boolean validateString(String input) {
			return input.matches("^[a-zA-Zäöüß]+$");
			}

}

