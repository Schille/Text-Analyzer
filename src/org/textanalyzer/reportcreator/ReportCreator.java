/**
 * 
 */
package org.textanalyzer.reportcreator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.util.List;

import javax.swing.JPanel;

import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;

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
		JPanel reportpanel = new JPanel();
		JLabel name = new JLabel();
		JLabel surname = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JLabel headline = new JLabel();
		JLabel wordmistakes = new JLabel();
		JPanel piechartupper = new JPanel();
		
		
		
		headline.setText("Report");
		headline.setFont(new Font("Dialog", 1 ,20));
		headline.setHorizontalAlignment(SwingConstants.CENTER);
		
		wordmistakes.setText("Verhältnis Rechtschreibfehler/Wortanzahl");
		DefaultPieDataset pieMistakes = new DefaultPieDataset();
		pieMistakes.setValue("Rechtschreibfehler", myResultset.getWrongWordCount());
		pieMistakes.setValue("Wortanzahl", myResultset.getWordCount());
		
		JFreeChart chart = ChartFactory.createPieChart
				(" ",pieMistakes,true,true,false );
		
		
		
		
		name.setText("Nachname: "+myProfile.getLastName());
		surname.setText("Vorname: "+myProfile.getFirstName());
		age.setText("Alter: "+String.valueOf(myProfile.getAge()));
		profession.setText("Profession: " +myProfile.getProfession());
		
		ChartPanel pieChart = new ChartPanel(chart);
		pieChart.setPreferredSize(new Dimension(300,200));
		
		
		
		piechartupper.setSize(100, 200);
		piechartupper.add(pieChart);
		
		
		reportpanel.setLayout(new java.awt.GridBagLayout());
		reportpanel.setSize(400, 400);
		
		reportpanel.add(headline);
       reportpanel.add(name);
       reportpanel.add(surname);
       reportpanel.add(age);
       reportpanel.add(profession);
       reportpanel.add(piechartupper);
		
		return reportpanel;
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
