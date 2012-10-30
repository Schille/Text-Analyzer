/**
 * 
 */
package org.textanalyzer.reportcreator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.List;

import javax.swing.JPanel;

import org.textanalyzer.analyzer.IResultSet;
import org.textanalyzer.database.IProfileInformation;

/**
 * @author Robert Stein
 *
 */
public class ReportCreator implements IReportCreator {

	/* (non-Javadoc)
	 * @see org.textanalyzer.reportcreator.IReportCreator#getGraphPanel(org.textanalyzer.database.IProfileInformation, org.textanalyzer.analyzer.IResultSet)
	 */
	@Override
	public JPanel getGraphPanel(IProfileInformation myProfile, IResultSet myResultset) {
		JFrame frame = new JFrame("FrameChart");
		JLabel name = new JLabel();
		JLabel surname = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		
		name.setText(myProfile.getLastName());
		surname.setText(myProfile.getFirstName());
		age.setText(String.valueOf(myProfile.getAge()));
		profession.setText(myProfile.getProfession());
		
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.textanalyzer.reportcreator.IReportCreator#getGraphPanel(org.textanalyzer.database.IProfileInformation, java.util.List)
	 */
	@Override
	public JPanel getGraphPanel(IProfileInformation myProfile, List<IResultSet> myResultset) {
		// TODO Auto-generated method stub
		return null;
	}

}
