/**
 * 
 */
package org.textanalyzer.documentimporter;

import java.util.Date;


/**
 * @author Sebastian 
 *
 */
public interface IDocument {

	String getDocumentPath();
	
	Date getImportDate();
	
	DocumentFormat getDocumentFormat();
	
	String getText();
	
	String getFileName();
}
