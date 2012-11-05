/**
 * 
 */
package org.textanalyzer.profileviewer;

import javax.swing.JPanel;

/**
 * @author Michael Schilonka
 *
 */
public class ProfileViewer implements IProfileViewer {

	private long userID;
	private JPanel ground;
	
	
	//-----------Constructor------------
	public ProfileViewer(long myUserID){
		userID = myUserID;
	}
	
	/* (non-Javadoc)
	 * @see org.textanalyzer.profileviewer.IProfileViewer#getProfileViewer()
	 */
	@Override
	public JPanel getProfileViewer() {
		return ground;
	}

}
