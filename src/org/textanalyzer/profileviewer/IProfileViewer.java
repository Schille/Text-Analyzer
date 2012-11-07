/**
*
*/
package org.textanalyzer.profileviewer;

import javax.swing.JPanel;

import org.textanalyzer.database.IDocument;

/**
* @author Michael Schilonka
*
*/
public interface IProfileViewer {

JPanel getProfileViewer();

void updateContent(IDocument myDocument);

}