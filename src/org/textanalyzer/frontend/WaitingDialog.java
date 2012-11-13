/**
 * 
 */
package org.textanalyzer.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * @author Robert Stein
 *
 */
public class WaitingDialog extends JFrame{
	
	private WaitingDialog scope;
	/**
	 * Builds a small frame or dialog to show user that work is in progress.
	 * @param parent is needed to place frame in relation to the calling component.
	 */
	
	public void showWaiting() {
		scope = this;
		setLayout(null);	//Use absolute positioning
		setAlwaysOnTop(true);
		JButton cancel = new JButton("Abbrechen");
		
		
		JLabel plswait = new JLabel("Analyse läuft...");
		plswait.setSize(120,30);
		plswait.setLocation(90,15);
		
		cancel.setSize(120, 30);
		cancel.setLocation(80, 75);
		cancel.setForeground(Color.white);
		cancel.setBackground(new Color(209,0,0));
		
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				scope.setVisible(false);
			}
		});
		
		add(plswait);
		add(cancel);
		
		JProgressBar bar = new JProgressBar();
		bar.setIndeterminate(true);
		bar.setBounds(40, 0, 270,30);
		bar.setLocation(10,37);
		
		add(bar);
		
		setUndecorated(true);  			//No top-bar to hide or close this dialog
		setPreferredSize(new Dimension(290,125));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);

		setVisible(true);
		
		
	}
	
}
