/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;
import org.textanalyzer.reportcreator.ReportCreator;

/**
 * @author Robert Stein
 *
 */
public class TestReportCreator {

	
	@Test
	public void testgetGraphPanel(){
		ReportCreator reporter = new ReportCreator();
		//reporter.getGraphPanel(myProfile, myResultset);
		 
		//daprofile = new IProf
		
		
		  JFrame frame = new JFrame("FrameChart");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(reporter.getGraphPanel(myProfile, myResultset));
	        frame.pack();
	        frame.setVisible(true);
	}

}
