package org.textanalyzer.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @version 13.11.2012
 */

/**
 * the class FrontendImporter is responsible for the graphic appearance that is
 * displayed if the user wants to import a text to a author
 */

public class FrontendImporter extends JFrame implements IFrontendImporter {

	// variables which defining the elements that are needed to create the
	// FrontendImporter

	private JTextField dataPath = new JTextField();
	private JButton startAnalysis = new JButton("Analyse starten");
	private JButton browseButton = new JButton("Browse...");
	private JLabel text_Dateiimport = new JLabel("Dateiimport...");
	private JLabel text_customWords = new JLabel("Benutzer Wörter...");
	private JLabel text_EingabeDesTextes = new JLabel("Eingabe des Textes");
	private JLabel text_TextInTextfeld = new JLabel(
			"oder Text in Textfeld eingen/kopieren...");
	private JLabel text_Textanalyse = new JLabel("Textanalyse");
	private JTextArea textareaForBlank = new JTextArea();
	private JTextArea customWords = new JTextArea();
	private JScrollPane textareaPane;
	private String filepath = "";
	private String blankText = "";
    private boolean emptyClose = false;


	/**
	 * method showImportWindow displays the JFrame with all the elements that
	 * are needed to display the FrontendImporter
	 */
	public void showImportWindow() {

		setTitle("Datei importieren...");
		setLayout(null);
		setBounds(0, 0, 700, 480);
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        class WindowHandler extends WindowAdapter {
        	public void windowClosing(WindowEvent evt){
        		setEmptyClose(true);
        	}
        }
        addWindowListener(new WindowHandler());

		// textareaPane is the JScrollPane which is needed to allow scrolling in
		// the textareaForBlank

		textareaPane = new JScrollPane(textareaForBlank);
		textareaPane.setBounds(53, 210, 407, 162);

		// positioning of all the elements to the frame

		text_Textanalyse.setBounds(204, 28, 140, 35);
		text_EingabeDesTextes.setBounds(195, 68, 160, 25);
		text_Dateiimport.setBounds(50, 108, 93, 21);
		dataPath.setBounds(52, 133, 300, 20);
		browseButton.setBounds(365, 131, 93, 25);
		text_TextInTextfeld.setBounds(50, 173, 270, 20);
		startAnalysis.setBounds(187, 390, 140, 26);

		// set the font to the text elements

		text_Textanalyse.setFont(new Font("Arial", 0, 24));
		text_EingabeDesTextes.setFont(new Font("Arial", 0, 18));
		text_Dateiimport.setFont(new Font("Arial", 0, 14));
		text_TextInTextfeld.setFont(new Font("Arial", 0, 14));

		// give the buttons an standardized look

		browseButton.setBackground(new Color(209, 0, 0));
		browseButton.setForeground(new java.awt.Color(255, 255, 255));
		browseButton.setFont(new Font("Arial", 0, 14));
		startAnalysis.setBackground(new Color(209, 0, 0));
		startAnalysis.setForeground(new java.awt.Color(255, 255, 255));
		startAnalysis.setFont(new Font("Arial", 0, 14));

		// set the textarea

		textareaForBlank.setLineWrap(true);
		textareaForBlank.setColumns(20);
		textareaForBlank.setRows(5);

		text_customWords.setFont(new Font("Arial", 0, 14));
		text_customWords.setBounds(500, 80, 400, 80);

		// scrolltextareaPane ScrollPane which is needed to let the customWords
		// field scroll

		JScrollPane scrolltextareaPane = new JScrollPane(customWords);
		scrolltextareaPane.setBounds(500, 133, 150, 240);

		customWords.setLineWrap(false);
		customWords.setColumns(5);
		customWords.setRows(5);

		// adding all elements to the Frame

		add(scrolltextareaPane);
		add(text_customWords);
		add(startAnalysis);
		add(text_Dateiimport);
		add(dataPath);
		add(browseButton);
		add(text_EingabeDesTextes);
		add(text_TextInTextfeld);
		add(text_Textanalyse);
		add(textareaPane);

		// that the listeners to the buttons

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

	/**
	 * if the browse button is clicked the file chooser dialogue appears the
	 * selected file of the dialogue is entered to the dataPath text field in
	 * the end the variable "filepath" gets the content of the dataPath text
	 * field and the method getfilepath is called to pass the filepath
	 */
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

	/**
	 * startAnalysisActionPerformed is the action that will be performed if the
	 * startAnalysis button is clicked then it will be proved if one of the
	 * fields (either the dataPath field or the textareaForBlank field) is not
	 * empty if they both are empty or both are filled the user is informed
	 * about the wrong input given
	 */
	private void startAnalysisActionPerformed(java.awt.event.ActionEvent evt) {

		filepath = dataPath.getText();
		blankText = textareaForBlank.getText();

		if (filepath.equals("") && blankText.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Bitte w�hle eine Textquelle !", "Fehler",
					JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		if (!filepath.equals("") && !blankText.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Bitte nur eine Textquelle w�hlen!", "Fehler",
					JOptionPane.OK_OPTION);
			return;
		}

		setVisible(false);

	}

	/**
	 * returned the filepath to let it use from the documentimporter e.g.
	 */
	@Override
	public String getFilePath() {
		return filepath;
	}

	/**
	 * sets the date on which the report has been made
	 */
	@Override
	public Date getImportDate() {
		return new Date();
	}

	/**
	 * method getText delivers the content of the blankText field
	 */
	@Override
	public String getText() {
		return blankText;
	}

	/**
	 * if there is some input in the custom word list the text will be split on
	 * the enters and passed
	 */
	public List<String> getCustomWordList() {
		if (customWords.getText() != null)
			return Arrays.asList(customWords.getText().split("\n"));
		return new ArrayList<String>();
	}
	public boolean isEmptyClose() {
		return emptyClose;
	}

	public void setEmptyClose(boolean emptyClose) {
		this.emptyClose = emptyClose;
	}

}