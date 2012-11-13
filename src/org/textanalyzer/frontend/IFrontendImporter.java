/**
 * 
 */
package org.textanalyzer.frontend;

import java.util.Date;

/**
 * @author Katharina Sandrock
 * @version 13.11.2012
 */
public interface IFrontendImporter {

	void showImportWindow();
	
	String getFilePath();
	
	Date getImportDate();
	
	String getText();
	
}
