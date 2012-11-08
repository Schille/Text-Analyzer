/**
 * 
 */
package org.textanalyzer.frontend;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * @author Robert Stein
 *
 */
public class WaitingDialog extends JFrame{
	
	/**
	 * Builds a small frame or dialog to show user that work is in progress.
	 * @param parent is needed to place frame in relation to the calling component.
	 */
	
	public void showWaiting(Component parent) {
		
		setLayout(null);	//Use absolute positioning
		setAlwaysOnTop(true);
		
		JLabel plswait = new JLabel("Bitte warten...");
		plswait.setSize(100,30);
		plswait.setLocation(115,15);
		
		add(plswait);
		
		JProgressBar bar = new JProgressBar();
		bar.setIndeterminate(true);
		bar.setBounds(40, 0, 275, 30);
		bar.setLocation(10,37);
		
		add(bar);
		
		setUndecorated(true);  			//No top-bar to hide or close this dialog
		setPreferredSize(new Dimension(300,100));
		setLocationRelativeTo(parent);
		setResizable(false);
		pack();
		setVisible(true);
		
		
	}
	


}
