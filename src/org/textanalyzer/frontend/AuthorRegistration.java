package org.textanalyzer.frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AuthorRegistration extends JFrame {

    private javax.swing.JLabel authornameLabel;
    private javax.swing.JLabel professionLabel;
    private javax.swing.JLabel ageLabel;
    private javax.swing.JPanel authorRegistrationPanel;
    private javax.swing.JButton submit;
    private javax.swing.JTextField authorname;
    private javax.swing.JTextField profession;
    private javax.swing.JTextField age;

   
    public AuthorRegistration(){
    	
    	super("Hinzufügen eines Benutzers");
    	authorRegistrationPanel = new JPanel();
    	setContentPane(authorRegistrationPanel);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(100,100);
    	getContentPane().add(authornameLabel);
    	getContentPane().add(authorname);
    	getContentPane().add(professionLabel);
    	getContentPane().add(profession);
    	getContentPane().add(ageLabel);
    	getContentPane().add(age);
    	getContentPane().add(submit);
    	authornameLabel.setText("Name des Autors:");
    	authorname.getText();
    	professionLabel.setText("Beruf: ");
    	profession.getText();
    	ageLabel.setText("Alter: ");
    	age.getText();
    	
    	pack();
    	setVisible(true);
    	    	
    	
    }
}
