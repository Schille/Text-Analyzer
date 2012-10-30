/**
 * 
 */
package org.textanalyzer.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.textanalyzer.frontend.FrontendImporter;

/**
 * @author sandrock
 *
 */
public class TestFrontendImporter {

	@Test
	public void test() {
		FrontendImporter frontend = new FrontendImporter();
		frontend.showImportWindow();
		
		frontend.setVisible(true);
	}

}
