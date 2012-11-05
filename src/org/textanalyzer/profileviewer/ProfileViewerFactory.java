/**
 * 
 */
package org.textanalyzer.profileviewer;

/**
 * @author Michael Schilonka
 *
 */
public class ProfileViewerFactory implements IProfileViewerFactory {

	/* (non-Javadoc)
	 * @see org.textanalyzer.profileviewer.IProfileViewerFactory#invokeNewProfileViewer(long)
	 */
	@Override
	public IProfileViewer invokeNewProfileViewer(long myUserID) {
		return new ProfileViewer(myUserID);
	}

}
