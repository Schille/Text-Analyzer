package org.textanalyzer.frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Katharina Sandrock
 * @version 18.11.2012
 */

/* 
 * the class HelpPanel constructs a JPanel on which the user gets information how to use the software
 * it works like a welcome page
 */
public class HelpPanel extends JPanel{
	
	JLabel helpAddButton = new JLabel("<html><body>Drücke diesen Button um einen Autor zur Liste<br> hinzuzufügen.</body></html>");
	JLabel addIcon = new JLabel(new ImageIcon("lib/addButton.jpg"));
	JLabel helpDeleteButton = new JLabel("<html><body>Wähle einen Autor aus der Liste und drücke diesen <br> Button um ihn aus der Liste zu entfernen.</body></html>");
	JLabel deleteIcon = new JLabel(new ImageIcon("lib/deleteButton.jpg"));
	JLabel helpList = new JLabel("<html><body>Wähle einen Autor aus der Liste um sein Profil zu <br> sehen, einen neuen Text zu analysieren oder <br>seine Gesamtstatistik anzuschauen. </body></html>");
	JLabel arrow = new JLabel(new ImageIcon("lib/arrow.png"));

	public HelpPanel() {
	
		setBackground(Color.white);
		setLayout(null);
		

		//set the position of all the elements
		 
		helpAddButton.setBounds(110,53,350,45);
		helpAddButton.setFont(new Font("Arial",0,16));
		addIcon.setBounds(40,50,50,50);
		helpDeleteButton.setFont(new Font("Arial",0,16));
		helpDeleteButton.setBounds(110,142,370,40);
		deleteIcon.setBounds(40,135,50,50);
		helpList.setFont(new Font("Arial",0,16));
		helpList.setBounds(110,231,360,65);
		arrow.setBounds(40,245,54,31);
		
		// add the elements to the panel
		 
		add(helpAddButton);
		add(addIcon);
		add(helpDeleteButton);
		add(deleteIcon);
		add(helpList);
		add(arrow);
		
	}
	
	
	

}
