package org.textanalyzer.documentimporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.textanalyzer.frontend.FrontendImporter;

public class DocumentImporter implements IDocumentImporter {
	private boolean correct = true;

	@Override
	public IDocument invokeNewDocumentImport() {
		Document document = new Document();

		do {
			FrontendImporter frontend = new FrontendImporter();
			frontend.showImportWindow();
			while (frontend.isShowing()) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			File file = new File(frontend.getFilePath());
			String filename = file.getName();
			String extension = filename.substring(
					filename.lastIndexOf(".") + 1, filename.length());

			if (extension.equalsIgnoreCase("pdf")) {

				document.setDocumentFormat(DocumentFormat.PDF);

			} else if (extension.equalsIgnoreCase("doc")) {
				// Import function for *.doc files
				// needs WordExtractor import
				InputStream fis = null;
				// try to open a new inputStream and read the file
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
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

			} else if (extension.equalsIgnoreCase("odt")) {

				document.setDocumentFormat(DocumentFormat.ODT);

			} else if (extension.equalsIgnoreCase("txt")) {

				document.setDocumentFormat(DocumentFormat.TXT);

			} else if (!frontend.getText().isEmpty()) {
				// Plain Text
				document.setDocumentFormat(DocumentFormat.PLAIN_TEXT);
				document.setText(frontend.getText());
			} else {
				correct = false;
				JOptionPane.showMessageDialog(null, "Fehlerhafte Eingabe, bitte wählen sie neu!", null, JOptionPane.OK_OPTION);
			}
			document.setDocumentPath(frontend.getFilePath());
			document.setFileName(file.getName());
		} while (!correct);
		return document;

	}
}
