/**
 * 
 */
package org.textanalyzer.documentimporter;

import java.net.URI;
import java.util.Date;


/**
 * @author Sebastian 
 *
 */
public interface IDocument {

	URI getDocumentPath();
	
	Date getImportDate();
	
	DocumentFormat getDocumentFormat();
	
	String getText();
	
	String getFileName();
}
