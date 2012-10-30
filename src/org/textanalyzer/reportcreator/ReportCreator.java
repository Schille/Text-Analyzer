/**
 * 
 */
package org.textanalyzer.reportcreator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.BorderLayout;
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
		JPanel piechartupper = new JPanel();
		
		
		JLabel name = new JLabel();
		JLabel surname = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JLabel headline = new JLabel();
		JLabel wordmistakes = new JLabel();
		JLabel aphraselength = new JLabel();
		JLabel wordcount = new JLabel();
		JLabel wrongwords = new JLabel();
		
		//reportpanel.setLayout(new java.awt.GridBagLayout());
		reportpanel.setLayout(null);
		
		headline.setText("Report");
		headline.setFont(new Font("Dialog", 1 ,30));
		headline.setLocation(200, 3);
		headline.setSize(200, 100);
		
		wordmistakes.setText("Verhältnis Rechtschreibfehler/Wortanzahl");
		DefaultPieDataset pieMistakes = new DefaultPieDataset();
		pieMistakes.setValue("Rechtschreibfehler", myResultset.getWrongWordCount());
		pieMistakes.setValue("Wortanzahl", myResultset.getWordCount());
		
		JFreeChart chart = ChartFactory.createPieChart
				(" ",pieMistakes,true,true,false );
		
		
		
		name.setText("Nachname: "+myProfile.getLastName());
		name.setSize(150, 30);
		name.setLocation(10, 40);
		
		surname.setText("Vorname: "+myProfile.getFirstName());
		surname.setSize(150, 30);
		surname.setLocation(10, 60);
		
		age.setText("Alter: "+String.valueOf(myProfile.getAge()));
		age.setSize(150, 30);
		age.setLocation(10, 80);
		
		profession.setText("Profession: " +myProfile.getProfession());
		profession.setSize(150, 30);
		profession.setLocation(10, 100);
		
		wordcount.setText("Wortanzahl: " + String.valueOf(myResultset.getWordCount()));
		wordcount.setSize(150,30);
		wordcount.setLocation(200, 40);
		
		wrongwords.setText("Rechtschreibfehler: "+String.valueOf(myResultset.getWrongWordCount()));
		wrongwords.setSize(150, 30);
		wrongwords.setLocation(200, 60);
		
		//aphraselength.setText("Durschn. Satzlänge"+.String.valueOf(myResultset.))
		aphraselength.setSize(150,30);
		aphraselength.setLocation(200,80);
		
		
		ChartPanel pieChart = new ChartPanel(chart);
		pieChart.setPreferredSize(new Dimension(300,200));
		
		
		
		piechartupper.setSize(300, 200);
		piechartupper.add(pieChart);
		piechartupper.setLocation(300, 300);
		
		headline.setVerticalAlignment(SwingConstants.TOP);
		reportpanel.setSize(600, 700);
		
		reportpanel.add(headline);
       reportpanel.add(name);
       reportpanel.add(surname);
       reportpanel.add(age);
       reportpanel.add(profession);
       reportpanel.add(wordcount);
       reportpanel.add(wrongwords);
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
