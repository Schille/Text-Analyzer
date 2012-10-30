package org.textanalyzer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.textanalyzer.documentimporter.Document;
import org.textanalyzer.documentimporter.DocumentFormat;
import org.textanalyzer.documentimporter.DocumentImporter;
import org.textanalyzer.documentimporter.IDocument;

public class TestDocumentImporter {

	@Test
	public void test() {
		DocumentImporter document = new DocumentImporter();
		IDocument mydoc = new Document();
		mydoc = document.invokeNewDocumentImport();
		System.out.println(mydoc.getText());
	}
	

}
