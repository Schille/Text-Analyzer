package org.textanalyzer.frontend;

import java.util.Date;

/**
 * @author Katharina Sandrock
 * 
 */

public class FrontendImporter implements IFrontendImporter {

	@Override
	public void showImportWindow() {

	}

	@Override
	public String getFilePath() {
		return "";
	}

	@Override
	public Date getImportDate() {
		return new Date();
	}

	@Override
	public String getText() {
		return "";
	}
}
