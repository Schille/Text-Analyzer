/**
 * 
 */
package org.textanalyzer.analyzer;

import org.textanalyzer.database.IResultSet;

/**
 * @author Andreas Neumann
 *
 */
public interface IAnalyzer {

	public IResultSet analyzeText(IAnalyzeTaskInformation myTask);
	
}
