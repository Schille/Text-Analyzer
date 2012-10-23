/**
 * 
 */
package org.textanalyzer.profileviewer;

/**
 * @author Michael Schilonka
 *
 */
public interface IProfileViewerFactory {

	IProfileViewer invokeNewProfileViewer(long myUserID);
	
}
