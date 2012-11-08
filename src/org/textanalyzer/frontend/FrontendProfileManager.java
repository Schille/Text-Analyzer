package org.textanalyzer.frontend;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import javax.swing.*;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.textanalyzer.profilemanager.ProfileManager;

/**
 * @author Katharina Sandrock
 * 
 */

public class FrontendProfileManager extends JFrame implements
		IFrontendProfileManager {

	private JFrame authorRegistrationWindow = new JFrame("Profile Manager");

	// set variables for the banner content

	private JPanel bannerPanel = new JPanel();
	private JPanel contentPanel = new JPanel();
	private JLabel icon = new JLabel(
			new ImageIcon(
					"C:\\Users\\sandrock\\DHBW\\3. Semester\\Software-Engineering\\Coding\\icon.png"));
	private JLabel bannerText = new JLabel(" analytiX ");

	// set variables for the navigation container

	private JPanel naviPanel = new JPanel();
	private JScrollPane naviScrollPane;
	private JButton addButton = new JButton(
			new ImageIcon(
					"C:\\Users\\sandrock\\DHBW\\3. Semester\\Software-Engineering\\Coding\\addButton.jpg"));
	private JButton deleteButton = new JButton(
			new ImageIcon(
					"C:\\Users\\sandrock\\DHBW\\3. Semester\\Software-Engineering\\Coding\\deleteButton.jpg"));
	DefaultListModel listModel = new DefaultListModel();
	private JList authorList;
	private ProfileManager profileLogic;	

	public FrontendProfileManager(ProfileManager myManager) {
		profileLogic = myManager;
		showFrontendProfileManager();

	}

	@Override
	public void showFrontendProfileManager() {

		LinkedList authors = new LinkedList();
		authors = profileLogic.getAuthorList();

		Iterator<String> iterate_author = authors.iterator();
		while (iterate_author.hasNext()) {
			String author_name = iterate_author.next();
			listModel.addElement(author_name);
		}

		authorRegistrationWindow.setLayout(null);
		authorRegistrationWindow.setBounds(0, 0, 750, 550);
		authorRegistrationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create an fill the Panel which contains the Banner

		bannerPanel.setBounds(0, 0, 750, 150);
		bannerPanel.setLayout(null);
		bannerPanel.setBackground(Color.white);
		icon.setBounds(75, 25, 100, 100);
		bannerPanel.add(icon);
		bannerText.setFont(new Font("Tahoma", 0, 50));
		bannerText.setBounds(400, 50, 300, 50);
		bannerPanel.add(bannerText);
		authorRegistrationWindow.add(bannerPanel);

		// create and fill the navigation Panel

		naviPanel.setBounds(0, 150, 250, 400);
		naviPanel.setLayout(null);
		naviPanel.setBackground(Color.white);
		authorList = new JList(listModel);
		naviScrollPane = new JScrollPane(authorList);
		naviScrollPane.setBounds(25, 65, 200, 280);
		addButton.setBounds(50, 0, 50, 50);
		naviPanel.add(addButton);
		deleteButton.setBounds(150, 0, 50, 50);
		naviPanel.add(deleteButton);
		authorList.setFont(new Font("Dialog", 1, 16));
		naviPanel.add(naviScrollPane);

		authorRegistrationWindow.add(naviPanel);
		contentPanel.setBounds(250, 150, 500, 400);
		contentPanel.setBackground(Color.white);
		authorRegistrationWindow.add(contentPanel);

		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});

		
		setResizable(false);
		pack();
		setVisible(true);
	}

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		profileLogic.removeProfile(authorList.getSelectedIndex());
		listModel.remove(authorList.getSelectedIndex());
	}

}
