/**
 * 
 */
package org.textanalyzer.analyzer;

import java.util.List;

import org.textanalyzer.database.IDocument;
import org.textanalyzer.database.IProfileInformation;

/**
 * @author Andreas Neumann
 *
 */
public interface IAnalyzeTaskInformation {

	void setDocument(IDocument myDocument);
	
	void setProfile(IProfileInformation myProfile);
	
	void setWordList(List<String> myWordList);
	
	List<String> getWordList();
	
	IProfileInformation getProfile();
	
	IDocument getDocument();
	
}
