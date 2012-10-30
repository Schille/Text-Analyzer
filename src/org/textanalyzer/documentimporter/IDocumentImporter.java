/**
 * 
 */
package org.textanalyzer.documentimporter;

/**
 * @author Sebastian Hirschl
 *
 */
public interface IDocumentImporter {
	
	IDocument invokeNewDocumentImport(DocumentFormat format, String path);
	
}
