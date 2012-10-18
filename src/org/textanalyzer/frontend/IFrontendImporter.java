/**
 * 
 */
package org.textanalyzer.frontend;

import java.util.Date;

/**
 * @author Katharina Sandrock
 *
 */
public interface IFrontendImporter {

	void showImportWindow();
	
	String getFilePath();
	
	Date getImportDate();
	
	String getText();
	
}
