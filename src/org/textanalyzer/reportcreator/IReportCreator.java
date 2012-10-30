/**
 * 
 */
package org.textanalyzer.reportcreator;

import java.util.List;

import javax.swing.JPanel;

import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;

/**
 * @author Robert Stein
 * 
 */
public interface IReportCreator {

	JPanel getGraphPanel(IProfileInformation myProfile, IResultSet myResultset);
	
	JPanel getGraphPanel(IProfileInformation myProfile, List<IResultSet> myResultset);
	
}
