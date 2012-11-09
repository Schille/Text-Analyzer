/**
*
*/
package org.textanalyzer.profileviewer;

import java.util.List;

import javax.swing.JPanel;

import org.textanalyzer.database.IDocument;

/**
* @author Michael Schilonka
*
*/
public interface IProfileViewer {

JPanel getProfileViewer();

void updateContent(IDocument myDocument, List<String> myWordList);

}