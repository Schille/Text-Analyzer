package org.textanalyzer.frontend;

import java.awt.Font;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Katharina Sandrock
 * 
 */

public class FrontendImporter extends JFrame implements IFrontendImporter {

	
	    private JTextField dataPath = new JTextField();
	    private JButton startAnalysis = new JButton("Analyse starten");
	    private JButton browseButton = new JButton("Browse...");
	    private JLabel text_Dateiimport = new JLabel("Dateiimport...");
	    private JLabel text_EingabeDesTextes = new JLabel("Eingabe des Textes");
	    private JLabel text_TextInTextfeld = new JLabel("oder Text in Textfeld eingen/kopieren...");
	    private JLabel text_Textanalyse = new JLabel("Textanalyse");
	    private JTextArea textareaForBlank = new JTextArea();
	    private JScrollPane textareaPane;
	    private String filepath = "";
	    private String blankText = "";

	    
	    public void showImportWindow() {

	        setTitle("Datei importieren...");
	        setLayout(null);
	        setBounds(0, 0, 520, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        textareaPane = new JScrollPane(textareaForBlank);
	        textareaPane.setBounds(53, 210, 407, 162);


	        text_Textanalyse.setBounds(204, 28, 140, 35);
	        text_EingabeDesTextes.setBounds(195, 68, 160, 25);
	        text_Dateiimport.setBounds(50, 108, 93, 21);
	        dataPath.setBounds(52, 133, 312, 20);
	        browseButton.setBounds(381, 131, 88, 25);
	        text_TextInTextfeld.setBounds(50, 173, 254, 20);
	        startAnalysis.setBounds(210, 390, 124, 26);






	        text_Textanalyse.setFont(new Font("Tahoma", 0, 24));
	        text_EingabeDesTextes.setFont(new Font("Tahoma", 0, 18));
	        text_Dateiimport.setFont(new Font("Tahoma", 0, 14));
	        text_TextInTextfeld.setFont(new Font("Tahoma", 0, 14));


	        browseButton.setBackground(new java.awt.Color(204, 0, 0));
	        browseButton.setForeground(new java.awt.Color(255, 255, 255));

	        startAnalysis.setBackground(new java.awt.Color(204, 0, 0));
	        startAnalysis.setForeground(new java.awt.Color(255, 255, 255));

	        textareaForBlank.setLineWrap(true);
	        textareaForBlank.setColumns(20);
	        textareaForBlank.setRows(5);


	        add(startAnalysis);
	        add(text_Dateiimport);
	        add(dataPath);
	        add(browseButton);
	        add(text_EingabeDesTextes);
	        add(text_TextInTextfeld);
	        add(text_Textanalyse);
	        add(textareaPane);


	        browseButton.addActionListener(new java.awt.event.ActionListener() {

	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                browseButtonActionPerformed(evt);
	            }
	        });

	        startAnalysis.addActionListener(new java.awt.event.ActionListener() {

	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                startAnalysisActionPerformed(evt);

	            }
	        });

	        setVisible(true);
	        setResizable(false);


	    }

	    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        JFileChooser dc = new JFileChooser();
	        dc.setDialogTitle("Browse...");
	        dc.showOpenDialog(null);
	        File file = dc.getSelectedFile();
	        filepath = file.getAbsolutePath();
	        dataPath.setText(filepath);

	        filepath = dataPath.getText();
	        getFilePath();
	        System.out.println(filepath);

	    }

	    private void startAnalysisActionPerformed(java.awt.event.ActionEvent evt) {

	        filepath = dataPath.getText();
	        blankText = textareaForBlank.getText();

	        if (filepath.equals("") && blankText.equals("")) {
	            JOptionPane.showMessageDialog(null, "Bitte w�hle eine Textquelle !", "Fehler", JOptionPane.OK_CANCEL_OPTION);
	            return;
	        }
	        if (!filepath.equals("") && !blankText.equals("")) {
	            JOptionPane.showMessageDialog(null, "Bitte nur eine Textquelle w�hlen!", "Fehler", JOptionPane.OK_OPTION);
	            return;
	        }

	        setVisible(false);

	    }

	    @Override
	    public String getFilePath() {
	        return filepath;
	    }

		@Override
		public Date getImportDate() {
			return new Date();
		}
		
	    @Override
	    public String getText() {
	        return blankText;
	    }
	    
	    public List<String> getCostumWordList(){
	    	
	    	return null; 
	    }


	}

	
	
	


