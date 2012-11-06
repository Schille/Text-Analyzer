/**
 * 
 */
package org.textanalyzer.documentimporter;

import org.textanalyzer.database.IDocument;

/**
 * @author Sebastian Hirschl
 *
 */
public interface IDocumentImporter {
	
	IDocument invokeNewDocumentImport();
	
}
