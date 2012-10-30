package org.textanalyzer.frontend;

import java.io.File;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Katharina Sandrock
 * 
 */

public class FrontendImporter extends JFrame implements IFrontendImporter {

	private javax.swing.JTextField dataPath;
	private javax.swing.JButton startAnalysis;
	private javax.swing.JLabel text_Dateiimport;
	private javax.swing.JLabel text_EingabeDesTextes;
	private javax.swing.JLabel text_TextInTextfeld;
	private javax.swing.JLabel text_Textanalyse;
	private javax.swing.JTextArea textareaForBlank;
	private javax.swing.JScrollPane textareaFrame;
	private String filepath = "";
	private String blankText ="";

	@Override
	public void showImportWindow() {

		textareaFrame = new javax.swing.JScrollPane();
		textareaForBlank = new javax.swing.JTextArea();
		javax.swing.JButton browseButton = new javax.swing.JButton();
		dataPath = new javax.swing.JTextField();
		text_Textanalyse = new javax.swing.JLabel();
		text_EingabeDesTextes = new javax.swing.JLabel();
		text_Dateiimport = new javax.swing.JLabel();
		text_TextInTextfeld = new javax.swing.JLabel();
		startAnalysis = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));

		textareaForBlank.setLineWrap(true);
		textareaForBlank.setColumns(20);
		textareaForBlank.setRows(5);
		textareaFrame.setViewportView(textareaForBlank);
		
		browseButton.setBackground(new java.awt.Color(204, 0, 0));
		browseButton.setForeground(new java.awt.Color(255, 255, 255));
		browseButton.setText("Browse...");
		browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				browseButtonMouseClicked(evt);
			}
		});
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
		startAnalysis.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				startAnalysisMouseClicked(evt);
			}
		});
		
	

		text_Textanalyse.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		text_Textanalyse.setText("Textanalyse");

		text_EingabeDesTextes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		text_EingabeDesTextes.setText("Eingabe des Textes");

		text_Dateiimport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		text_Dateiimport.setText("Dateiimport...");

		text_TextInTextfeld.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		text_TextInTextfeld.setText("oder Text in Textfeld eingeben/kopieren:");

		startAnalysis.setBackground(new java.awt.Color(204, 0, 0));
		startAnalysis.setForeground(new java.awt.Color(255, 255, 255));
		startAnalysis.setText("Analyse starten");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(text_EingabeDesTextes)
								.addGap(168, 168, 168))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(52, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		text_Textanalyse)
																.addGap(183,
																		183,
																		183))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						text_Dateiimport)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										dataPath,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										310,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										browseButton))
																				.addComponent(
																						textareaFrame,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						407,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						text_TextInTextfeld))
																.addGap(50, 50,
																		50))))
				.addGroup(
						layout.createSequentialGroup().addGap(209, 209, 209)
								.addComponent(startAnalysis)
								.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(19, 19, 19)
								.addComponent(text_Textanalyse)
								.addGap(18, 18, 18)
								.addComponent(text_EingabeDesTextes)
								.addGap(18, 18, 18)
								.addComponent(text_Dateiimport)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														dataPath,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(browseButton))
								.addGap(18, 18, 18)
								.addComponent(text_TextInTextfeld)
								.addGap(18, 18, 18)
								.addComponent(textareaFrame,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										163,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(startAnalysis)
								.addContainerGap(26, Short.MAX_VALUE)));

		browseButton.getAccessibleContext().setAccessibleName("");

	
		pack();
		setVisible(true);
		
	}



	private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {

		JFileChooser dc = new JFileChooser();
		dc.setDialogTitle("Browse...");
		dc.showOpenDialog(null);
		File file = dc.getSelectedFile();
		filepath = file.getAbsolutePath();
		dataPath.setText(filepath);

	}
	
	
	private void startAnalysisActionPerformed(java.awt.event.ActionEvent evt) {
	        
	}


	private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {
		filepath = dataPath.getText();
		getFilePath();
	}

	private void startAnalysisMouseClicked(java.awt.event.MouseEvent evt) {
		filepath = dataPath.getText();
		blankText = textareaForBlank.getText();		

		if(filepath.equals("") && blankText.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte wähle eine Textquelle !", "Fehler", JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		if(!filepath.equals("") && !blankText.equals("")){
			JOptionPane.showMessageDialog(null, "Bitte nur eine Textquelle wählen!", "Fehler", JOptionPane.OK_OPTION);
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

	public static void main(String args[]) {

	}
}
