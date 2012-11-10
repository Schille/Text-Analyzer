package org.textanalyzer.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.*;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.textanalyzer.profilemanager.ProfileManager;
import org.textanalyzer.profileviewer.ProfileViewer;

/**
 * @author Katharina Sandrock
 * 
 */

public class FrontendProfileManager extends JFrame implements
		IFrontendProfileManager {

	// set variables for the banner content

	private JPanel bannerPanel = new JPanel();
	private JPanel contentPanel = new JPanel();
	private JLabel icon = new JLabel(new ImageIcon("lib/icon.png"));
	private JLabel bannerText = new JLabel(" analytiX ");

	// set variables for the navigation container

	private JPanel naviPanel = new JPanel();
	private JScrollPane naviScrollPane;
	private JButton addButton = new JButton(new ImageIcon("lib/addButton.jpg"));
	private JButton deleteButton = new JButton(new ImageIcon(
			"lib/deleteButton.jpg"));
	DefaultListModel listModel = new DefaultListModel();
	private JList authorList;
	
	
	private ProfileManager profileLogic;

	
	private AuthorRegistration authorRegistration;

	public FrontendProfileManager(ProfileManager myManager) {
		profileLogic = myManager;
		authorRegistration = new AuthorRegistration(profileLogic, this);
		showFrontendProfileManager();

	}
	
	

	@Override
	public void showFrontendProfileManager() {

		fillList();

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createBanner();
		createNavigation();
		createContentPanel();

		getContentPane().add(naviPanel);
		getContentPane().add(bannerPanel);

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

		authorList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
	
				contentPanel.removeAll();
				
				int key = authorList.getSelectedIndex();
				ProfileViewer profileView = new ProfileViewer((int)profileLogic.getUserID(key));
				JPanel profilePanel =  profileView.getProfileViewer();
				profilePanel.setLocation(0,0);
				profilePanel.setBackground(Color.white);
				
		profilePanel.setVisible(true);
		contentPanel.add(profilePanel);
		contentPanel.repaint();
				

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	
		setResizable(false);
		setPreferredSize(new Dimension(750, 550));
		setLocation(50, 50);
		pack();
		setVisible(true);
	}



	public void fillList() {
		listModel.removeAllElements();
		LinkedList<String> authors = profileLogic.getAuthorList();

		Iterator<String> iterate_author = authors.iterator();
		while (iterate_author.hasNext()) {
			String author_name = iterate_author.next();
			listModel.addElement(author_name);
		}
	}

	// create an fill the Panel which contains the Banner
	private void createBanner() {
		icon.setBounds(75, 25, 100, 100);

		bannerPanel.setBounds(0, 0, 750, 150);
		bannerPanel.setLayout(null);
		bannerPanel.setBackground(Color.white);
		bannerPanel.add(icon);

		bannerText.setFont(new Font("Tahoma", 0, 50));
		bannerText.setBounds(400, 50, 300, 50);

		bannerPanel.add(bannerText);
		bannerPanel.setVisible(true);
	}

	// create and fill the navigation Panel
	private void createNavigation() {
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

		authorList.setFont(new Font("Arial", 1, 16));
		naviPanel.add(naviScrollPane);
		
		naviPanel.setVisible(true);
	}

	// create content panel
	private void createContentPanel() {
		contentPanel.setBounds(250, 150, 500, 400);
		contentPanel.setBackground(Color.white);
		contentPanel.setLayout(null);
		contentPanel.setVisible(true);
		getContentPane().add(contentPanel);
	}

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		contentPanel.removeAll();

		contentPanel.add(authorRegistration);
		authorRegistration.setLocation(0,0);
		authorRegistration.setVisible(true);
		contentPanel.repaint();
		
		

	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		contentPanel.removeAll();
		createContentPanel();
		getContentPane().add(contentPanel);
		contentPanel.repaint();
		if (authorList.getSelectedIndex() == -1 ){
		JOptionPane.showMessageDialog(null, "Zum L\u00f6schen bitte zun\u00e4chst einen Autor aus der Liste w\u00e4hlen.", "Fehler", JOptionPane.ERROR_MESSAGE);
		} else {
		profileLogic.removeProfile(authorList.getSelectedIndex());
		listModel.remove(authorList.getSelectedIndex());
		}
		
	}

}
