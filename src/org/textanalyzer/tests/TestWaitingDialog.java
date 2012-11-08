package org.textanalyzer.tests;

import org.junit.Test;
import org.textanalyzer.frontend.FrontendImporter;
import org.textanalyzer.frontend.WaitingDialog;

public class TestWaitingDialog {

	@Test
	public void test() {
		WaitingDialog testdia = new WaitingDialog();
		
		FrontendImporter k = new FrontendImporter();
		k.showImportWindow();
		k.setLocation(100, 100);
		testdia.showWaiting(k);
		
		
		System.out.println("test");
	}

}
