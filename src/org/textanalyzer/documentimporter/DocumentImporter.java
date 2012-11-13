package org.textanalyzer.documentimporter;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.textanalyzer.database.Document;
import org.textanalyzer.frontend.FrontendImporter;
import org.textanalyzer.profileviewer.ProfileViewer;

public class DocumentImporter implements IDocumentImporter, ActionListener {
	private boolean correct = true;
	private FrontendImporter frontend;
	private ProfileViewer viewer;

	public DocumentImporter() {
		
	}

	@Override
	/**
	 * This method invokes a new document import and processes the file for relevant information. 
	 * After that the information is transfered into a Document which is returned.
	 * @param ProfileViewer
	 * @return Document
	 */
	public void invokeNewDocumentImport(ProfileViewer myProfileViewer) {
		viewer = myProfileViewer;
		frontend = new FrontendImporter(this);
		frontend.showImportWindow();
	}

	/**
	 * @param myProfileViewer
	 * @throws HeadlessException
	 */
	private void invokeAction() throws HeadlessException {

		List<String> customWordList;
		Document document = new Document();

		// loop for checking if the user entered a correct file

		customWordList = frontend.getCustomWordList();

		// open a new file with the entered file path and create an input
		// stream
		File file = new File(frontend.getFilePath());
		InputStream fis = null;

		// split the string in order to check which file was selected
		String filename = file.getName();
		String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

		// Import function for a *.pdf file
		if (extension.equalsIgnoreCase("pdf")) {
			PDDocument pdfDocument = null;

			try {
				pdfDocument = new PDDocument();
				pdfDocument = PDDocument.load(file);
				PDFTextStripper pdfText = new PDFTextStripper();
				document.setText(pdfText.getText(pdfDocument));

			} catch (FileNotFoundException e1) {
				correct = false;

			} catch (IOException e) {

				e.printStackTrace();

			} finally {
				if (pdfDocument != null) {
					try {
						pdfDocument.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			document.setDocumentFormat(DocumentFormat.PDF);
			document.setDocumentPath(frontend.getFilePath());

			// Import function for *.doc files
		} else if (extension.equalsIgnoreCase("doc")) {
			// try to open a new inputStream and read the file
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				correct = false;
			}

			// try to extract the text of the word file
			try {
				WordExtractor wordFile = new WordExtractor(fis);
				String text = wordFile.getText();
				document.setText(text);

			} catch (IOException e) {
				e.printStackTrace();
			}
			document.setDocumentFormat(DocumentFormat.DOC);
			document.setDocumentPath(frontend.getFilePath());

			// Import function for *.odt files
		} else if (extension.equalsIgnoreCase("odt")) {

			OpenDocumentParser odt = new OpenDocumentParser();

			try {
				document.setText(odt.getText(frontend.getFilePath()));

			} catch (FileNotFoundException e1) {
				correct = false;
			} catch (Exception e) {

			}
			document.setDocumentPath(frontend.getFilePath());

			// Import function for *.txt files
		} else if (extension.equalsIgnoreCase("txt")) {

			String outputString = "";
			String buffer = "";

			try {
				BufferedReader out = new BufferedReader(new FileReader(frontend.getFilePath()));

				try {

					while ((buffer = out.readLine()) != null) {
						outputString = outputString + buffer + "\n";
						buffer = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				frontend.setVisible(true);
			}
			document.setDocumentFormat(DocumentFormat.TXT);
			document.setText(outputString);
			document.setDocumentPath(frontend.getFilePath());

			// Import function for plain text
		} else if (!frontend.getText().isEmpty()) {
			// Plain Text
			document.setDocumentFormat(DocumentFormat.PLAIN_TEXT);
			document.setText(frontend.getText());
			String plainTextName = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Dateinamen für den Text ein!", "Name des Textes", JOptionPane.PLAIN_MESSAGE);
			document.setFileName(plainTextName);

			// If non of the conditions was reached set correct false and
			// make the while loop start over again
		} 
		if (frontend.getText().isEmpty()) {
			document.setFileName(file.getName());
		}
		document.setImportDate(new Date());

		Updater updater = new Updater(viewer, document, customWordList);
		Thread Job = new Thread(updater, "ProfileUpdaThread");
		Job.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frontend.setVisible(false);
		invokeAction();
		
	}
	
	class Updater implements Runnable{
		private Document doc;
		private List<String> custom;
		private ProfileViewer viewer;
		
		public Updater(ProfileViewer myViewer, Document myDocument, List<String> myCustomWordList){
			viewer = myViewer;
			custom = myCustomWordList;
			doc = myDocument;
		}

		@Override
		public void run() {
			viewer.updateContent(doc, custom);
			
		}
	}
}
