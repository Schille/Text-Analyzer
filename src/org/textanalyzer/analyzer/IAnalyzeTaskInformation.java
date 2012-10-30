/**
 * 
 */
package org.textanalyzer.analyzer;

import java.util.List;

import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.documentimporter.IDocument;

/**
 * @author Andreas Neumann
 *
 */
public interface IAnalyzeTaskInformation {

	void setDocument(IDocument myDocument);
	
	void setProfile(IProfileInformation myProfile);
	
	void setWordList(List<String> myWordList);
	
	List<String> getWordList(int count);
	
	IProfileInformation getProfile();
	
	IDocument getDocument();
	
}
