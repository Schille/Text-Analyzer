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

/*
 * the class FrontendProfileManager defines the GUI of the ProfileManager
 * the class is a frame which is responsible for displaying the main view for the user
 * the interface that it uses is the IFrontendProfileManager
 */

public class FrontendProfileManager extends JFrame implements
		IFrontendProfileManager {

	/* set variables for the banner content
	 * @param bannerPanel JPanel which later gets the banner
	 * @param icon JLabel that contains an image (image is the icon of analytix)
	 * @param bannerText JLabel that gets the headline 
	 */
	
	private JPanel bannerPanel = new JPanel();
	private JLabel icon = new JLabel(new ImageIcon("lib/analyzer.jpg"));
	private JLabel bannerText = new JLabel(new ImageIcon("lib/banner.jpg"));
	private JLabel borderleft = new JLabel(new ImageIcon("lib/borderleft.jpg"));
	
	/* set variables for the navigation container
	*  @param naviPanel JPanel on which the navigation will be added 
	*  @param authorList JList of all the authors that are in the data base
	*  @param listModel sets DefaultListModel which is necessary to refresh if users delete authors
	*  @param naviScrollPane JScrollPane which is necessary for making the JList scrolling if the authorlist contains to much authors for the JList
	*  @param addButton JButton with an image of a plus - if clicked on it the user should be allowed to add an author to the list
	*  @param deleteButton JButton with an image of a minus - if clicked on it the user should be allowed to delete an author fro the list
	*/
	private JPanel naviPanel = new JPanel();
	private JList authorList;
	DefaultListModel listModel = new DefaultListModel();
	private JScrollPane naviScrollPane;
	private JButton addButton = new JButton(new ImageIcon("lib/addButton.jpg"));
	private JButton deleteButton = new JButton(new ImageIcon(
			"lib/deleteButton.jpg"));
	private JLabel borderleft2 = new JLabel(new ImageIcon("lib/borderleft2.jpg"));

	/* 
	 * @param contentPanel JPanel as placeholder for the content
	 */
	private JPanel contentPanel = new JPanel();
	
	/* 
	 * @param profileLogic of type ProfileManager - needed to get the methods of the class ProfileManager
	 */
	private ProfileManager profileLogic;
	
	/*
	 * @param authorRegistration of type AuthorRegistration needed because the AuthorRegistration has to get access to the ProfileManager, too
	 */
	private AuthorRegistration authorRegistration;
	
	
	/*
	 * constructor of the class FrontendProfileManager gets a ProfileManager
	 * calls the method showFrontendProfileManager
	 */
	public FrontendProfileManager(ProfileManager myManager) {
		profileLogic = myManager;
		authorRegistration = new AuthorRegistration(profileLogic, this);
		showFrontendProfileManager();

	}
	
	/*
	 * the method fillList fills the listModel with the authors that it gets from the ProfileManager (profileLogic)
	 * it fills the list until no author is available	
	 */
	public void fillList() {
		listModel.removeAllElements();
		LinkedList<String> authors = profileLogic.getAuthorList();

		Iterator<String> iterate_author = authors.iterator();
		while (iterate_author.hasNext()) {
			String author_name = iterate_author.next();
			listModel.addElement(author_name);
		}
	}
	
	/* 
	 * the method showFrontendProfileManager is responsible for the displaying of all possible functions 
	 */
	@Override
	public void showFrontendProfileManager() {

		fillList();

		/*
		 * first sets some general functions to the JFrame
		 * JFrame gets no Layout and with clicking of the close button the JFrame is closed
		 */
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * calls of the methods createBanner(), createNavigation() and createContentPanel() to create all the panel which should be appear on the JFrame
		 * therefore after that they have to be added
		 */
		createBanner();
		createNavigation();
		createContentPanel();

		getContentPane().add(naviPanel);
		getContentPane().add(bannerPanel);

		/*
		 * now some Listeners to the buttons are set which are calling methods described later
		 */
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
		
		/*
		 * on the list (authorList) an MouseListener is needed
		 */
		authorList.addMouseListener(new MouseListener() {

			/*
			 * method that show the profileViewer of the selected author of the list
			 */
			@Override
			public void mouseClicked(MouseEvent e) {

				contentPanel.removeAll();
				int key = authorList.getSelectedIndex();
				ProfileViewer profileView = new ProfileViewer(
						(int) profileLogic.getUserID(key));
				JPanel profilePanel = profileView.getProfileViewer();
				profilePanel.setLocation(0, 0);
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
		
		/*
		 * some more general functions are added to the Frame
		 * the window should not be resizable because a preferred size is set, which is enough for all panels that will appear
		 */

		setResizable(false);
		setPreferredSize(new Dimension(750, 550));
		setLocation(50, 50);
		pack();
		setVisible(true);
	}



	/*
	 * create an fill the Panel which contains the banner
	 */
	private void createBanner() {
		
		bannerPanel.setBounds(0, 0, 750, 150);
		bannerPanel.setLayout(null);
		bannerPanel.setBackground(Color.white);
		icon.setBounds(75, 0, 144, 140);
		borderleft.setBounds(0,0,31,150);
		bannerPanel.add(icon);
		bannerPanel.add(borderleft);
		
		//bannerText.setFont(new Font("Arial", 0, 50));
		bannerText.setBounds(270, 40, 436, 65);

		bannerPanel.add(bannerText);
		bannerPanel.setVisible(true);
	}

	/*
	 *  create and fill the navigation panel
	 */
	private void createNavigation() {
		naviPanel.setBounds(0, 150, 250, 400);
		naviPanel.setLayout(null);
		naviPanel.setBackground(Color.white);

		borderleft2.setBounds(0,0,26,400);
		naviPanel.add(borderleft2);
		
		authorList = new JList(listModel);
		naviScrollPane = new JScrollPane(authorList);
		naviScrollPane.setBounds(30, 65, 200, 280);
		
		addButton.setBounds(55, 0, 50, 50);
		naviPanel.add(addButton);
		deleteButton.setBounds(155, 0, 50, 50);
		naviPanel.add(deleteButton);

		authorList.setFont(new Font("Arial", 1, 16));
		naviPanel.add(naviScrollPane);

		naviPanel.setVisible(true);
	}

	// create the content panel
	private void createContentPanel() {
		contentPanel.setBounds(250, 150, 500, 400);
		contentPanel.setBackground(Color.white);
		contentPanel.setLayout(null);
		JLabel help = new JLabel();
		help.setBounds(25,25,100,100);
		help.setFont(new Font("Arial",1,25));
		contentPanel.add(help);
		contentPanel.setVisible(true);
		getContentPane().add(contentPanel);
		
	}

	/*
	 * the method "addButtonActionPerformed" shows the JPanel of the AuthorRegistration class when clicking on the addButton
	 * @see AuthorRegistration class
	 */
	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {

		contentPanel.removeAll();
		contentPanel.add(authorRegistration);
		authorRegistration.setLocation(0, 0);
		authorRegistration.setVisible(true);
		contentPanel.repaint();

	}

	/*
	 * deleteButtonActionPerformed is the method that will be called if the user clicks on the deleteButton
	 * if the user has not selected an element from the list the user gets the advice to do it
	 * if the user has selected an element of the list it will be removed (remove an author(
	 */
	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		contentPanel.removeAll();
		createContentPanel();
		getContentPane().add(contentPanel);
		contentPanel.repaint();
		if (authorList.getSelectedIndex() == -1) {
			JOptionPane
					.showMessageDialog(
							null,
							"Zum L\u00f6schen bitte zun\u00e4chst einen Autor aus der Liste w\u00e4hlen.",
							"Fehler", JOptionPane.ERROR_MESSAGE);
		} else {
			profileLogic.removeProfile(authorList.getSelectedIndex());
			listModel.remove(authorList.getSelectedIndex());
		}

	}

}
