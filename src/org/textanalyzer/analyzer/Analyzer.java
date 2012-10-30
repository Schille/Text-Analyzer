package org.textanalyzer.analyzer;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.database.IResultSet;
import org.textanalyzer.database.ResultSet;
import org.textanalyzer.documentimporter.DocumentFormat;
import org.textanalyzer.documentimporter.IDocument;

public class Analyzer implements IAnalyzer {

	// run the analysis with some Test Data
	public static void main(String[] args) {
		new Analyzer().analyzeText(new IAnalyzeTaskInformation() {
			@Override
			public void setWordList(List<String> myWordList) {}
			@Override
			public void setProfile(IProfileInformation myProfile) {}
			@Override
			public void setDocument(IDocument myDocument) {}
			@Override
			public List<String> getWordList() {String list[] = new String[1];list[0]="Hallo";return Arrays.asList(list);}
			@Override
			public IProfileInformation getProfile() {return new IProfileInformation() {
				@Override
				public void setProfession(String myProfession) {}
				@Override
				public void setLastName(String myLastName) {}
				@Override
				public void setFirstName(String myFirstName) {}
				@Override
				public void setAge(int myAge) {}
				@Override
				public String getProfession() {return "Student";}
				@Override
				public String getLastName() {return "Test";}
				@Override
				public String getFirstName() {return "Test";}
				@Override
				public int getAge() {return 20;}
				@Override
				public List<IResultSet> getAnalyzedDocuments() {
					// TODO Auto-generated method stub
					return null;
				}
				@Override
				public void setAnalyzedDocuments(List<IResultSet> myResultSet) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void addToAnalyzedDocuments(IResultSet myResultSet) {
					// TODO Auto-generated method stub
					
				}
			};}
			
			@Override
			public IDocument getDocument() {return new IDocument() {
				@Override
				public String getText() {return "Hallo Welt! Dies ist ein Test f�r mich";}
				@Override
				public Date getImportDate() {return new Date();}
				@Override
				public String getFileName() {return "test.pdf";}
				@Override
				public URI getDocumentPath() {return null;};
				@Override
				public DocumentFormat getDocumentFormat() {return DocumentFormat.PDF;}
			};}
		});
	}
	@Override
	public IResultSet analyzeText(IAnalyzeTaskInformation myTask) {
		
		/* Creating the Result Set */
		IResultSet result = new ResultSet();
		
		/* Get the text and transform to Stream */
		StringReader textReader = new StringReader(myTask.getDocument().getText());
		
		/* Read the text char by char */
		try {
			String word = "";
			int character;
			while((character = textReader.read()) != -1){
				// merge word
				if(character != ' '){
					word += (char) character;
				}
				// Do the Analysis
				else{
					if(word != ""){
						char[] punct = new char[0];
						//word.getChars(/*start*/, /*end*/, punct, 0);
						if(punct[0] == '.');
						// TODO Analyse /word/
					}
					word = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		textReader.close();

		// make the overall statistics
		return result;
	}

}
