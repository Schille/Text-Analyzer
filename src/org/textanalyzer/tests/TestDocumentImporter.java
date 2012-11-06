package org.textanalyzer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IDocument;
import org.textanalyzer.documentimporter.DocumentFormat;
import org.textanalyzer.documentimporter.DocumentImporter;

public class TestDocumentImporter {

	@Test
	public void test() {
		DocumentImporter document = new DocumentImporter();
		IDocument mydoc = new Document();
		mydoc = document.invokeNewDocumentImport();
		assert(mydoc.getDocumentFormat() == DocumentFormat.DOC);
		System.out.println(mydoc.getText());
	}
	

}
