/**
 * 
 */
package org.textanalyzer.database;

import java.net.URI;
import java.util.Date;

import org.textanalyzer.documentimporter.DocumentFormat;


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
