/**
 * 
 */
package org.textanalyzer.documentimporter;

import org.textanalyzer.database.IDocument;
import org.textanalyzer.profileviewer.ProfileViewer;

/**
 * @author Sebastian Hirschl
 *
 */
public interface IDocumentImporter {
	
	void  invokeNewDocumentImport(ProfileViewer myProfileViewer);
	
}
