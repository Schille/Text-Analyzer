package org.textanalyzer.tests;

import org.junit.Test;
import org.textanalyzer.frontend.FrontendImporter;
import org.textanalyzer.frontend.WaitingDialog;

public class TestWaitingDialog {

	@Test
	public void test() {
		WaitingDialog testdia = new WaitingDialog();
		

		testdia.showWaiting();
		
		
		System.out.println("test");
	}

}
