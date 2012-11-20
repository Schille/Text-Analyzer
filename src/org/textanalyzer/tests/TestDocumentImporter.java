package org.textanalyzer.tests;

import org.junit.Test;
import org.textanalyzer.database.Document;
import org.textanalyzer.database.IDocument;
import org.textanalyzer.documentimporter.DocumentFormat;
import org.textanalyzer.documentimporter.DocumentImporter;
import org.textanalyzer.profileviewer.ProfileViewer;

/**
* @author Sebstian Hirschl
*/

public class TestDocumentImporter {

	@Test
	public void test() {
		ProfileViewer myViewer = new ProfileViewer(1000);
		DocumentImporter document = new DocumentImporter();
		IDocument mydoc = new Document();
		document.invokeNewDocumentImport(myViewer);
		assert(mydoc.getDocumentFormat() == DocumentFormat.DOC);
		System.out.println(mydoc.getText());
	}
	

}
