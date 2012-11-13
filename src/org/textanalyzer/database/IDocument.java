/**
 * 
 */
package org.textanalyzer.database;

import java.util.Date;

import org.textanalyzer.documentimporter.DocumentFormat;

/**
 * Interface for Document
 * 
 * @author Sebastian Hirschl
 * @version 12.11.2012
 */
public interface IDocument {

	String getDocumentPath();

	Date getImportDate();

	DocumentFormat getDocumentFormat();

	String getText();

	String getFileName();
}
