package org.textanalyzer.documentimporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocumentImporter implements IDocumentImporter {

	@Override
	public IDocument invokeNewDocumentImport(DocumentFormat format, String path) {

		File file = new File(path);

		if (format.equals("PDF")) {

		}
		if (format.equals("DOC")) {

			InputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				WordExtractor wordFile = new WordExtractor(fis);
				String test = wordFile.getText();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (format.equals("ODT")) {

		}
		if (format.equals("TXT")) {

		}
		if (format.equals("PLAIN_TEXT")) {

		}
		return null;
	}

}
