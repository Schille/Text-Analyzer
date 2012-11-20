/**
 * 
 */
package org.textanalyzer.analyzer;

import org.textanalyzer.database.IResultSet;

/**
 * @author Andreas Neumann
 * @author Michael Schilonka
 *
 */
public interface IAnalyzer {

	public IResultSet analyzeText(IAnalyzeTaskInformation myTask);
	
}
