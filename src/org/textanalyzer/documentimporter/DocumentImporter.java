package org.textanalyzer.documentimporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.textanalyzer.frontend.FrontendImporter;

public class DocumentImporter implements IDocumentImporter {
	private boolean correct = true;

	@Override
	/**
	 * This method invokes a new document import and processes the file for relevant information. 
	 * After that the information is transfered into a Document which is returned.
	 * @return Document
	 */
	public IDocument invokeNewDocumentImport() {
		Document document = new Document();
		
		//loop for checking if the user entered a correct file
		do {
			FrontendImporter frontend = new FrontendImporter();
			frontend.showImportWindow();
			
			// pause thread while the user browses the file
			while (frontend.isShowing()) {
				
				try {
					Thread.sleep(20);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//open a new file with the entered file path and create an input stream 
			File file = new File(frontend.getFilePath());
			InputStream fis = null;
			
			// try to open a new inputStream and read the file
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			// split the string in order to check which file was selected
			String filename = file.getName();
			String extension = filename.substring(
					filename.lastIndexOf(".") + 1, filename.length());
			
			//Import function for a *.pdf file
			if (extension.equalsIgnoreCase("pdf")) {
				PDDocument pdfDocument = null;
				
				try {
					pdfDocument = new PDDocument();
					pdfDocument = PDDocument.load(file);
					PDFTextStripper pdfText = new PDFTextStripper();
					document.setText(pdfText.getText(pdfDocument));
					
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
				
			// Import function for *.doc files
			} else if (extension.equalsIgnoreCase("doc")) {		
				
				// try to extract the text of the word file
				try {
					WordExtractor wordFile = new WordExtractor(fis);
					String text = wordFile.getText();
					document.setText(text);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				document.setDocumentFormat(DocumentFormat.DOC);
				
			// Import function for *.odt files
			} else if (extension.equalsIgnoreCase("odt")) {
				
				OpenDocumentParser odt = new OpenDocumentParser();
				
				try {
					document.setText(odt.getText(frontend.getFilePath()));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			// Import function for *.txt files
			} else if (extension.equalsIgnoreCase("txt")) {
				
				String outputString = null;
				String buffer = null;
				
				try {
					BufferedReader out = new BufferedReader(new FileReader(frontend.getFilePath()));
					
					try {
						
						while((buffer = out.readLine()) != null) {
							outputString = outputString + buffer + "\n";
							buffer = null;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				document.setDocumentFormat(DocumentFormat.TXT);
				document.setText(outputString);
				
			// Import function for plain text
			} else if (!frontend.getText().isEmpty()) {
				// Plain Text
				document.setDocumentFormat(DocumentFormat.PLAIN_TEXT);
				document.setText(frontend.getText());
				
			// If non of the conditions was reached set correct false and make the while loop start over again
			} else {
				correct = false;
				JOptionPane.showMessageDialog(null,
						"Fehlerhafte Eingabe, bitte wählen sie neu!", null,
						JOptionPane.OK_OPTION);
			}
			document.setDocumentPath(frontend.getFilePath());
			document.setFileName(file.getName());
			
		} while (!correct);
		
		return document;

	}
}
