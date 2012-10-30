/**
 * 
 */
package org.textanalyzer.reportcreator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		JLabel mostword = new JLabel();
		JLabel mood = new JLabel();
		JLabel pseudoiq = new JLabel();
		JLabel relationwords = new JLabel();
		JLabel mostusedwords = new JLabel();
		JLabel mostusedcustomwords = new JLabel();
		
		
		
		//reportpanel.setLayout(new java.awt.GridBagLayout());
		reportpanel.setLayout(null);
		
		headline.setText("Report");
		headline.setFont(new Font("Dialog", 1 ,30));
		headline.setLocation(240, 3);
		headline.setSize(200, 35);
		
		wordmistakes.setText("");
		DefaultPieDataset pieMistakes = new DefaultPieDataset();
		pieMistakes.setValue("Rechtschreibfehler", myResultset.getWrongWordCount());
		pieMistakes.setValue("Wortanzahl", myResultset.getWordCount()-myResultset.getWrongWordCount());
		
	
		
		
		JFreeChart chart = ChartFactory.createPieChart
				("",pieMistakes,true,true,false );
		
		
		PiePlot plot = new PiePlot();
		plot =(PiePlot)chart.getPlot();
		plot.setLabelGenerator(null);
		
		
		
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
		
		relationwords.setText("<html><center><b>Verhältnis der Gesamtwortanzahl / Rechtschreibfehler</b></center></html>");
		relationwords.setSize(340,30);
		relationwords.setLocation(290,275);
		
		aphraselength.setText("Durschn. Satzlänge: "+String.valueOf(myResultset.getAvaragePhraseLength())+" Wörter");
		aphraselength.setSize(250,30);
		aphraselength.setLocation(200,80);
		
		mostword.setText("Häufigstes Nomen: "+myResultset.getMostFrequentNomen());
		mostword.setSize(250,30);
		mostword.setLocation(200, 100);
		
		mostusedcustomwords.setText("<html>Häufigste Listenwörter - Wort (Anzahl): ");
		mostusedcustomwords.setSize(300, 200);
		mostusedcustomwords.setLocation(10, 320);
		
		Map<String,Integer> temp2 = myResultset.getCustomWordCount();
		for (Map.Entry<String, Integer> entry : temp2.entrySet()) {
			mostusedcustomwords.setText(mostusedcustomwords.getText()+"<br> - "+entry.getKey()+ " ("+entry.getValue()+")");

		}
		
		mostusedcustomwords.setText(mostusedcustomwords.getText()+"</html>");
		
		mood.setText("Grundstimmung: "+myResultset.getTextMood());
		mood.setSize(250, 30);
		mood.setLocation(390, 40);
		
		pseudoiq.setText("<html><b>Wertung: "+myResultset.getPseudoIQ()+"</b></html>");
		pseudoiq.setSize(200,30);
		pseudoiq.setLocation(390,60);
		
		
		mostusedwords.setText("<html>Liste der häufigsten Wörter - Wort (Anzahl): ");
		
		Map<String,Integer> temp = myResultset.getMostFrequentWord(10);
		for (Map.Entry<String, Integer> entry : temp.entrySet()) {
			mostusedwords.setText(mostusedwords.getText()+"<br> - "+entry.getKey()+ " ("+entry.getValue()+")");

		}
		mostusedwords.setText(mostusedwords.getText()+"</html>");
		mostusedwords.setSize(300,200);
		mostusedwords.setLocation(10, 120);
		
		
		
		ChartPanel pieChart = new ChartPanel(chart);
		pieChart.setPreferredSize(new Dimension(300,200));
		
		
		
		piechartupper.setSize(300, 200);
		piechartupper.add(pieChart);
		piechartupper.setLocation(300, 300);
		
		reportpanel.setSize(600, 700);
		reportpanel.setBackground(new Color(255,255,255));
		
		
		reportpanel.add(headline);
       reportpanel.add(name);
       reportpanel.add(surname);
       reportpanel.add(age);
       reportpanel.add(profession);
       reportpanel.add(wordcount);
       reportpanel.add(wrongwords);
       reportpanel.add(aphraselength);
       reportpanel.add(mostword);
       reportpanel.add(mood);
       reportpanel.add(pseudoiq);
       reportpanel.add(relationwords);
       reportpanel.add(mostusedwords);
       reportpanel.add(mostusedcustomwords);
       
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
