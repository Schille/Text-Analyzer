
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
	private JLabel icon = new JLabel(
			new ImageIcon(
					"../icon.png"));
	private JLabel bannerText = new JLabel(" analytiX ");

	// set variables for the navigation container

	private JPanel naviPanel = new JPanel();
	private JScrollPane naviScrollPane;
	private JButton addButton = new JButton(
			new ImageIcon(
					"../addButton.jpg"));
	private JButton deleteButton = new JButton(
			new ImageIcon(
					"../deleteButton.jpg"));
	DefaultListModel listModel = new DefaultListModel();
	private JList authorList;
	private ProfileManager profileLogic;	
	private ProfileViewer profileView;

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

		setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createBanner();
        createNavigation();
        createContentPanel();


        add(naviPanel);
        add(contentPanel);
        add(bannerPanel);



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
		
		authorList.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				int key = authorList.getSelectedIndex();
				profileView = new ProfileViewer(key);
				
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
		setPreferredSize(new Dimension(750,550));
		setLocation(50,50);
		pack();
		setVisible(true);
	}
	

    //create an fill the Panel which contains the Banner
    private void createBanner(){
        icon.setBounds(75, 25, 100, 100);

        bannerPanel.setBounds(0, 0, 750, 150);
        bannerPanel.setLayout(null);
        bannerPanel.setBackground(Color.white);
        bannerPanel.add(icon);

        bannerText.setFont(new Font("Tahoma", 0, 50));
        bannerText.setBounds(400, 50, 300, 50);

        bannerPanel.add(bannerText);
        
    }

    //create and fill the navigation Panel
    private void createNavigation(){
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
    }

    //create content panel
    private void createContentPanel(){
        contentPanel.setBounds(250, 150, 500, 400);
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(null);
    }

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
		JPanel authorRegistrateWindow = new JPanel();
		authorRegistrateWindow.setLayout(null);
		authorRegistrateWindow.setBounds(250, 150, 500, 400);
		authorRegistrateWindow.setBackground(Color.red);
		authorRegistrateWindow.setLayout(null);
		add(authorRegistrateWindow);
		add(addButton);
		
		
		
	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		profileLogic.removeProfile(authorList.getSelectedIndex());
		listModel.remove(authorList.getSelectedIndex());
	}

}
