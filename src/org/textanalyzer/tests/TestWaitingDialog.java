package org.textanalyzer.tests;

import org.junit.Test;
import org.textanalyzer.frontend.WaitingDialog;

public class TestWaitingDialog {
/**
* @author Robert Stein
*/
	@Test
	public void test() {
		WaitingDialog testdia = new WaitingDialog();
		

		testdia.showWaiting();
		
		
		System.out.println("test");
	}

}
