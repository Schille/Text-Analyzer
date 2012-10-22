/**
 * 
 */
package org.textanalyzer.analyzer;

/**
 * @author Andreas Neumann
 *
 */
public interface IAnalyzer {

	public IResultSet analyzeText(IAnalyzeTaskInformation myTask);
	
}
