/**
 * 
 */
package org.textanalyzer.reportcreator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
	@SuppressWarnings("deprecation")
	@Override
	public JPanel getGraphPanel(IProfileInformation myProfile, IResultSet myResultset) {
		JPanel reportpanel = new JPanel();
		JPanel piechartupper = new JPanel();
		
		
		JLabel name = new JLabel();
		JLabel surname = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JLabel headline = new JLabel();
		JLabel aphraselength = new JLabel();
		JLabel wordcount = new JLabel();
		JLabel wrongwords = new JLabel();
		JLabel mostword = new JLabel();
		JLabel mood = new JLabel();
		JLabel pseudoiq = new JLabel();
		JLabel relationwords = new JLabel();
		JLabel mostusedwords = new JLabel();
		JLabel mostusedcustomwords = new JLabel();
		JLabel filename = new JLabel();
		JLabel filepath = new JLabel();
		JLabel filedate = new JLabel();
		JLabel fileformat = new JLabel();
		JSeparator horline1 = new JSeparator(JSeparator.HORIZONTAL);
		JSeparator horline2 = new JSeparator(JSeparator.HORIZONTAL);
	      
	      
	    horline1.setSize(580,10);
	    horline1.setLocation(10,130);
		
	    horline2.setSize(580,10);
	    horline2.setLocation(10,610);
		
		
		//Set LayoutManager null for better manual positioning
		reportpanel.setLayout(null);
		
		headline.setText("Report");
		headline.setFont(new Font("Dialog", 1 ,30));
		headline.setLocation(240, 3);
		headline.setSize(200, 35);
		
		
		//Creating the dataset for the piechart
		DefaultPieDataset pieMistakes = new DefaultPieDataset();
		//setting the values for the 'categories' of the chart
		pieMistakes.setValue("Rechtschreibfehler", myResultset.getWrongWordCount());
		pieMistakes.setValue("restliche Worte", myResultset.getWordCount()-myResultset.getWrongWordCount());
		
		//Creating chart from dataset.
		JFreeChart chart = ChartFactory.createPieChart
				("",pieMistakes,true,true,false );
		
		//Setting plot options for the chart
		PiePlot plot = new PiePlot();
		plot = (PiePlot)chart.getPlot();
		plot.setLabelGenerator(null);	//Don't generate Labels within the graphic
		
		/**
		 * Setting text, size and position of various labels
		 * Get data from ProfileInformation and ResultSet
		 */
		
		name.setText("Nachname: "+myProfile.getLastName());
		name.setSize(200, 30);
		name.setLocation(10, 40);
		
		surname.setText("Vorname: "+myProfile.getFirstName());
		surname.setSize(200, 30);
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
		wrongwords.setSize(200, 30);
		wrongwords.setLocation(200, 60);
		
		relationwords.setText("<html><center><b>Anteil Rechtschreibfehler</b></center></html>");
		relationwords.setSize(340,30);
		relationwords.setLocation(350,135);
		
		
		aphraselength.setText("Durschn. Satzlänge: "+String.valueOf(myResultset.getAvaragePhraseLength())+" Wörter");
		aphraselength.setSize(250,30);
		aphraselength.setLocation(200,80);
		
		mostword.setText("Häufigstes Nomen: "+myResultset.getMostFrequentNomen());
		mostword.setSize(250,30);
		mostword.setLocation(200, 100);
		
		mostusedcustomwords.setText("<html>Häufigste Listenwörter - Wort (Anzahl): ");
		mostusedcustomwords.setSize(300, 200);
		mostusedcustomwords.setLocation(10, 320);
		
		//Some logic to sort the given map by value
		
		Map<String,Integer> temp2 = myResultset.getCustomWordCount();
		//Reversed sorted map, start with the largest key
		
		
		LinkedHashMap<String, Integer> orderCustom = new LinkedHashMap<String, Integer>();
		
		for(int i = 0; i < 10 && temp2.entrySet().iterator()
				.hasNext(); i++) {
			Map.Entry<String, Integer> temp_entry = null;
			
			temp_entry = temp2.entrySet().iterator().next();
		
			for (Map.Entry<String, Integer> entry : temp2.entrySet()) {
				if(temp_entry != null && temp_entry.getValue() <= entry.getValue())
					temp_entry = entry;
			}
			
			temp2.remove(temp_entry.getKey());
			orderCustom.put(temp_entry.getKey(), temp_entry.getValue());
		}
		
		
		
		//Iterate over all map entries and put the key and value into the label
		Iterator<?> iterator = orderCustom.keySet().iterator();
		  while (iterator.hasNext()) {
		  Object key = iterator.next();
			mostusedcustomwords.setText(mostusedcustomwords.getText()+"<br> - "+orderCustom.get(key)+ " ("+key+")");
		  }
		  

		mostusedcustomwords.setText(mostusedcustomwords.getText()+"</html>");
		
		mood.setText("Grundstimmung: "+myResultset.getTextMood());
		mood.setSize(250, 30);
		mood.setLocation(390, 40);
		
		pseudoiq.setText("<html><b>Wertung: "+myResultset.getPseudoIQ()+"/100</b></html>");
		pseudoiq.setSize(200,30);
		pseudoiq.setLocation(390,60);
		
		
		//Same thing here, ordering a list of words and put result into a label
		mostusedwords.setText("<html>Liste der häufigsten Wörter - Wort (Anzahl): ");
		
		Map<String,Integer> temp3 = myResultset.getMostFrequentWord(10);
			
		LinkedHashMap<String, Integer> orderFrequent = new LinkedHashMap<String, Integer>();
		
		for(int i = 0; i < 10 && temp3.entrySet().iterator()
				.hasNext(); i++) {
			Map.Entry<String, Integer> temp_entry = null;
			
			temp_entry = temp3.entrySet().iterator().next();
		
			for (Map.Entry<String, Integer> entry : temp3.entrySet()) {
				if(temp_entry != null && temp_entry.getValue() <= entry.getValue())
					temp_entry = entry;
			}
			
			temp3.remove(temp_entry.getKey());
			orderFrequent.put(temp_entry.getKey(), temp_entry.getValue());
		}
		
        DefaultCategoryDataset barchartset = new DefaultCategoryDataset();

		
		Iterator<?> iterator2 = orderFrequent.keySet().iterator();
		  while (iterator2.hasNext()) {
		  Object key = iterator2.next();
			mostusedwords.setText(mostusedwords.getText()+"<br> - "+orderFrequent.get(key)+ " ("+key+")");
			barchartset.setValue(orderFrequent.get(key),"",(key.toString()));
		  }
		
		mostusedwords.setText(mostusedwords.getText()+"</html>");
		mostusedwords.setSize(300,200);
		mostusedwords.setLocation(10, 130);
		
		
		filename.setText("Dateiname: "+myResultset.getDocument().getFileName());
		filename.setSize(150,30);
		filename.setLocation(10,625);
		
		filepath.setText("Verzeichnis: "+myResultset.getDocument().getDocumentPath());
		filepath.setSize(300,30);
		filepath.setLocation(10,650);
		
		filedate.setText("Importiert: "+myResultset.getDocument().getImportDate().toLocaleString());
		filedate.setSize(250,30);
		filedate.setLocation(380, 625);
		
		fileformat.setText("Format: "+myResultset.getDocument().getDocumentFormat());
		fileformat.setSize(150,30);
		fileformat.setLocation(230, 625);
		
        //Creating barchart dataset, most likely the piechart
		ChartPanel pieChart = new ChartPanel(chart);
		pieChart.setPreferredSize(new Dimension(300,200));
		
		JFreeChart barchart = ChartFactory.createBarChart("", "Wort", "Anzahl der häufigsten Wörter", barchartset, PlotOrientation.HORIZONTAL, false,  true, false);
        
        ChartPanel barchartpanel = new ChartPanel(barchart);
        barchartpanel.setPreferredSize(new Dimension(340,200));
		
        JPanel barchartupper = new JPanel();
        barchartupper.setBackground(new Color(255,255,255));
        
        barchartupper.setSize(340, 205);
        barchartupper.add(barchartpanel);
        barchartupper.setLocation(255, 370);
        
        reportpanel.add(barchartupper);

        
		piechartupper.setSize(300, 205);
		piechartupper.add(pieChart);
		piechartupper.setLocation(290, 160);
		piechartupper.setBackground(new Color(255,255,255));
		
		reportpanel.setSize(600, 700);
		reportpanel.setBackground(new Color(255,255,255));
		
		
		
		//Put all components onto the panel
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
       reportpanel.add(horline1);
       reportpanel.add(horline2);
       reportpanel.add(relationwords);
       reportpanel.add(mostusedwords);
       reportpanel.add(mostusedcustomwords);
       reportpanel.add(filename);
       reportpanel.add(filepath);
       reportpanel.add(filedate);
       reportpanel.add(fileformat);
       
       reportpanel.add(piechartupper);
       

		
		return reportpanel;
	}

	/* (non-Javadoc)
	 * @see org.textanalyzer.reportcreator.IReportCreator#getGraphPanel(org.textanalyzer.database.IProfileInformation, java.util.List)
	 */
	@Override
	public JPanel getGraphPanel(IProfileInformation myProfile, List<IResultSet> myResultset) {
		JPanel reportpanel = new JPanel();
		JPanel piechartupper = new JPanel();
		
		JLabel name = new JLabel();
		JLabel surname = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JLabel headline = new JLabel();
		JLabel aphraselength = new JLabel();
		JLabel wordcount = new JLabel();
		JLabel wrongwords = new JLabel();
		JLabel mood = new JLabel();
		JLabel pseudoiq = new JLabel();
		JLabel relationwords = new JLabel();
		JLabel mostusedwords = new JLabel();
		JLabel mostusedcustomwords = new JLabel();
		JSeparator horline1 = new JSeparator(JSeparator.HORIZONTAL);
		JSeparator horline2 = new JSeparator(JSeparator.HORIZONTAL);
	      
	      
	    horline1.setSize(580,10);
	    horline1.setLocation(10,125);
		
	    horline2.setSize(580,10);
	    horline2.setLocation(10,610);
	    
		JLabel hint = new JLabel();
		
		reportpanel.setBackground(new Color(255,255,255));

		
		headline.setText("Report");
		headline.setFont(new Font("Dialog", 1 ,30));
		headline.setLocation(240, 3);
		headline.setSize(200, 35);
		
		hint.setText("Alle hier dargestellten Werte wurden als Durchschnitt des gesamten Profils ermittelt.");
		hint.setFont(new Font("Dialog",1,10));
		hint.setLocation(80, 630);
		hint.setSize(500,30);
		
		
		
		reportpanel.setLayout(null);
		
		name.setText("Nachname: "+myProfile.getLastName());
		name.setSize(200, 30);
		name.setLocation(10, 40);
		
		surname.setText("Vorname: "+myProfile.getFirstName());
		surname.setSize(200, 30);
		surname.setLocation(10, 60);
		
		age.setText("Alter: "+String.valueOf(myProfile.getAge()));
		age.setSize(150, 30);
		age.setLocation(10, 80);
		
		profession.setText("Profession: " +myProfile.getProfession());
		profession.setSize(150, 30);
		profession.setLocation(10, 100);
		
		mostusedwords.setText("<html>Liste der häufigsten Wörter - Wort (Anzahl): ");
		
		 DefaultCategoryDataset barchartset = new DefaultCategoryDataset();
		 DefaultPieDataset pieMistakes = new DefaultPieDataset();
		 
		 int temp_wordcount = 0;
		 int temp_wrongwords = 0;
		 int temp_result = 0;
		 int temp_avphrase = 0;
		 
		 
		 Iterator<?> itresult = myResultset.iterator();
		 int i = 0;
		 while(itresult.hasNext()) {
			 IResultSet tempresult = (IResultSet)itresult.next();
			 temp_wordcount = temp_wordcount + tempresult.getWordCount();
			 temp_wrongwords = temp_wrongwords + tempresult.getWrongWordCount();
			 temp_result = temp_result + tempresult.getPseudoIQ();
			 temp_avphrase = temp_avphrase + tempresult.getAvaragePhraseLength();
			 i++;
		 }
		 
		 temp_wordcount = temp_wordcount/i;
		 temp_wrongwords = temp_wrongwords/i;
		 temp_result = temp_result/i;
		 temp_avphrase = temp_avphrase/i;
		 
		 
		 relationwords.setText("<html><center><b>Anteil Rechtschreibfehler</b></center></html>");
		 relationwords.setSize(340,30);
		 relationwords.setLocation(350,125);
		 
			pieMistakes.setValue("Rechtschreibfehler", temp_wrongwords);
			pieMistakes.setValue("restliche Worte", temp_wordcount-temp_wrongwords);
			
			Averager av_cases = new Averager(myResultset);
			
			SortedMap<Integer, String> orderFrequent = new TreeMap<Integer,String>(Collections.reverseOrder());
			

			for (Map.Entry<String, Integer> entry : av_cases.getWordMap().entrySet()) {
				orderFrequent.put(entry.getValue(), entry.getKey());
			}
			
			Iterator<?> iterator2 = orderFrequent.keySet().iterator();
			  while (iterator2.hasNext()) {
			  Object key = iterator2.next();
				mostusedwords.setText(mostusedwords.getText()+"<br> - "+orderFrequent.get(key)+ " ("+key+")");
				barchartset.setValue(Integer.parseInt(key.toString()),"", orderFrequent.get(key));
			  }
			  
				mostusedwords.setText(mostusedwords.getText()+"</html>");
				mostusedwords.setSize(300,200);
				mostusedwords.setLocation(10, 120);
				
				
				
				mostusedcustomwords.setText("<html>Häufigste Listenwörter - Wort (Anzahl): ");
				mostusedcustomwords.setSize(300, 200);
				mostusedcustomwords.setLocation(10, 320);
				
				Map<String,Integer> temp2 = av_cases.getListMap();
				SortedMap<Integer,String> orderCustom = new TreeMap<Integer, String>(Collections.reverseOrder());
				
				for (Map.Entry<String, Integer> entry : temp2.entrySet()) {
					orderCustom.put(entry.getValue(), entry.getKey());
				}
				
				Iterator<?> iterator = orderCustom.keySet().iterator();
				  while (iterator.hasNext()) {
				  Object key = iterator.next();
					mostusedcustomwords.setText(mostusedcustomwords.getText()+"<br> - "+orderCustom.get(key)+ " ("+key+")");
				  }
				  
				mostusedcustomwords.setText(mostusedcustomwords.getText()+"</html>");
			
			
			JFreeChart chart = ChartFactory.createPieChart
			("",pieMistakes,true,true,false );
			
			
			PiePlot plot = new PiePlot();
			plot =(PiePlot)chart.getPlot();
			plot.setLabelGenerator(null);
			
			ChartPanel pieChart = new ChartPanel(chart);
			pieChart.setPreferredSize(new Dimension(300,200));
			
			piechartupper.setSize(300, 205);
			piechartupper.add(pieChart);
			piechartupper.setLocation(290, 150);
			piechartupper.setBackground(new Color(255,255,255));
		 
		 wordcount.setText("Wortanzahl: " + String.valueOf(temp_wordcount));
			wordcount.setSize(150,30);
			wordcount.setLocation(200, 40);
			
			wrongwords.setText("Rechtschreibfehler: "+String.valueOf(temp_wrongwords));
			wrongwords.setSize(200, 30);
			wrongwords.setLocation(200, 60);
		 
		 	aphraselength.setText("Durchschn. Satzlänge: "+String.valueOf(temp_avphrase)+" Wörter");
			aphraselength.setSize(290,30);
			aphraselength.setLocation(200,80);
			
			pseudoiq.setText("<html><b>Wertung: "+temp_result+"/100</b></html>");
			pseudoiq.setSize(200,30);
			pseudoiq.setLocation(390,60);
			
			mood.setText("Grundstimmung: "+av_cases.averageMood());
			mood.setSize(250, 30);
			mood.setLocation(390, 40);
			
			JFreeChart barchart = ChartFactory.createBarChart("", "Wort", "Anzahl der häufigsten Wörter", barchartset, PlotOrientation.HORIZONTAL, false,  true, false);
	        
	        ChartPanel barchartpanel = new ChartPanel(barchart);
	        barchartpanel.setPreferredSize(new Dimension(340,200));
			
	        JPanel barchartupper = new JPanel();
	        barchartupper.setBackground(new Color(255,255,255));
	        
	        barchartupper.setSize(340, 205);
	        barchartupper.add(barchartpanel);
	        barchartupper.setLocation(255, 370);
	        
	        reportpanel.add(barchartupper);
			
		 
			
		   reportpanel.add(headline);
	       reportpanel.add(name);
	       reportpanel.add(surname);
	       reportpanel.add(age);
	       reportpanel.add(profession);
	       reportpanel.add(wordcount);
	       reportpanel.add(wrongwords);
	       reportpanel.add(aphraselength);
	       reportpanel.add(pseudoiq);
	       reportpanel.add(piechartupper);
	       reportpanel.add(horline1);
	       reportpanel.add(horline2);
		 reportpanel.add(hint);
		 reportpanel.add(relationwords);
		 reportpanel.add(mostusedwords);
		 reportpanel.add(mostusedcustomwords);
		 reportpanel.add(mood);
		
		
		return reportpanel;
	}

}
