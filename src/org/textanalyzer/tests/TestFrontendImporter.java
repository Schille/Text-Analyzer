/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.textanalyzer.documentimporter.DocumentImporter;
import org.textanalyzer.frontend.FrontendImporter;

/**
 * @author Sebastian Hirschl
 *
 */
public class TestFrontendImporter {

	@Test
	public void test() {
		DocumentImporter importer = new DocumentImporter();
		FrontendImporter frontend = new FrontendImporter(importer);
		frontend.showImportWindow();
		
		frontend.setVisible(true);
	}

}
