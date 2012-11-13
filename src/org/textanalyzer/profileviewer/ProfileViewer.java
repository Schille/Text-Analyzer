/**
 *
 */
package org.textanalyzer.profileviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.textanalyzer.analyzer.AnalyzeTaskInformation;
import org.textanalyzer.analyzer.Analyzer;
import org.textanalyzer.database.DatabaseConnector;
import org.textanalyzer.database.IDocument;
import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.documentimporter.DocumentImporter;
import org.textanalyzer.frontend.WaitingDialog;
import org.textanalyzer.reportcreator.ReportCreator;

/**
 * @author Michael Schilonka & Robert Stein
 * 
 */
public class ProfileViewer implements IProfileViewer {

	private int userID;

	private JPanel ground;
	private DatabaseConnector connector;
	private IProfileInformation profileInformation;
	private List<IResultSet> resultSets;
	private DocumentImporter importer;
	private ProfileViewer this_viewer;
	private JButton new_analyse;
	private Analyzer analyzer;
	WaitingDialog waiter;
	private HashMap<String,ResultSet> resultmapper;

	
	
	//-----------Constructor------------
	public ProfileViewer(int myUserID){
		waiter = new WaitingDialog();
		analyzer = new Analyzer();
		userID = myUserID;
		connector = new DatabaseConnector();
		profileInformation = connector.getProfileInformation(userID);
		resultSets = connector.getAllResultSets(myUserID);
		importer = new DocumentImporter();
		this_viewer = this;
		resultmapper = new HashMap<String,ResultSet>();
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public JPanel getProfileViewer() {
		JPanel ground = new JPanel();
		JLabel headline = new JLabel();
		JLabel author = new JLabel();
		JLabel age = new JLabel();
		JLabel profession = new JLabel();
		JButton av_analyse = new JButton();
		new_analyse = new JButton();
		JList texte = new JList();
		ArrayList<String> dataname = new ArrayList<String>();
		JLabel notext = new JLabel();
		
		
		ground.setLayout(null);
		ground.setSize(500,400);
		
		headline.setText("Profilübersicht");
		headline.setFont(new Font("Arial", 1 ,24));
		headline.setLocation(180, 3);
		headline.setSize(400, 35);
		
		author.setText("Name: "+profileInformation.getLastName()+", "+profileInformation.getFirstName());
		author.setFont(new Font("Arial",0,16));
		author.setLocation(40, 50);
		author.setSize(250,30);
		
		age.setText("Alter: "+profileInformation.getAge());
		age.setFont(new Font("Arial",0,16));
		age.setLocation(40,80);
		age.setSize(250, 30);
		
		profession.setText("Profession: "+profileInformation.getProfession());
		profession.setFont(new Font("Arial",0,16));
		profession.setLocation(40,110);
		profession.setSize(250,30);
		
		notext.setText("Keine Texte vorhanden!");
		notext.setFont(new Font("Arial",0,16));
		notext.setSize(200,30);
		notext.setLocation(280,50);
		
		new_analyse.setText("Neue Textanalyse");
		new_analyse.setSize(180, 50);
		new_analyse.setLocation(40,170);
		new_analyse.setFont(new Font("Arial",0,16));
		new_analyse.setForeground(Color.white);
		new_analyse.setBackground(new Color(209,0,0));
		new_analyse.setFocusPainted(false);
		new_analyse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((JButton)arg0.getSource()).setEnabled(false);
				Thread a = new Thread(new Runnable() {
					
					@Override
					public void run() {
						importer.invokeNewDocumentImport(this_viewer);
						
					}
				});
				a.start();
			}
		});
		
		String multiLine = "<html><body><div align=\"center\"> Durchschnittsanalyse <br> starten</div> </body></html>";
		av_analyse.setText(multiLine);
		av_analyse.setSize(180, 50);
		av_analyse.setLocation(40,240);
		av_analyse.setForeground(Color.white);
		av_analyse.setBackground(new Color(209,0,0));
		av_analyse.setFont(new Font("Arial",0,16));
		av_analyse.setFocusPainted(false);
		
		av_analyse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<IResultSet>resultlist = connector.getAllResultSets(userID);
				buildReport(resultlist);
				
			}
		});
		
		texte.setSize(180, 300);
		texte.setLocation(280, 50);
		texte.setFont(new Font("Arial",0,16));
		
		Iterator<?> result = resultSets.iterator();
		if(result != null) {
		while(result.hasNext()) {
			ResultSet temp_res = (ResultSet)result.next();
			resultmapper.put(temp_res.getDocument().getFileName(), (ResultSet) temp_res);
			dataname.add(temp_res.getDocument().getFileName());	
		}
		texte.setListData(dataname.toArray());
		ground.add(texte);
		}
		else {
			ground.add(notext);
		}
		
		
		texte.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String key = ((JList)(arg0.getComponent())).getSelectedValue().toString();
				
				buildReport(resultmapper.get(key));
				
				
				
			}
		});
		
		ground.add(headline);
		ground.add(author);
		ground.add(age);
		ground.add(profession);
		ground.add(av_analyse);
		ground.add(new_analyse);
	

		return ground;
	}
	
	


	@Override
	public void updateContent(IDocument myDocument, List<String> myWordList) {
		//Start analysis here...
		profileInformation = connector.getProfileInformation(userID);
		AnalyzeTaskInformation task = new AnalyzeTaskInformation();
		task.setDocument(myDocument);
		task.setProfile(profileInformation);
		task.setWordList(myWordList);
		
		JFrame frame = (JFrame)SwingUtilities.getRoot(this.getProfileViewer());
		waiter.showWaiting(frame);
		IResultSet set = analyzer.analyzeText(task);
		waiter.dispose();
		
		connector.saveResultSet(userID, set);
		
		buildReport(set);
		
		this.new_analyse.setEnabled(true);
		
	}

	/**
	 * @param profileinfo
	 * @param resultlist
	 */
	public void buildReport(List<IResultSet> resultlist) {
		ReportCreator reporter = new ReportCreator();
		JFrame frame = reportFrame();
	    frame.getContentPane().add(reporter.getGraphPanel(profileInformation, resultlist));
	    frame.setVisible(true);
	}
	
	public void buildReport(IResultSet result) {
		ReportCreator reporter = new ReportCreator();
		JFrame frame = reportFrame();
	    frame.getContentPane().add(reporter.getGraphPanel(profileInformation, result));
	    frame.setVisible(true);

	    
}

	public JFrame reportFrame () {
		JFrame frame = new JFrame("Report");
		frame.setPreferredSize(new Dimension(620, 700));
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setResizable(false);
	    frame.pack();
	    frame.setAlwaysOnTop(true);
	    
	    return frame;
	}



	}


