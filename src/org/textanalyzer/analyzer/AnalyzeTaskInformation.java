package org.textanalyzer.analyzer;

import java.util.List;

import org.textanalyzer.database.IProfileInformation;
import org.textanalyzer.documentimporter.Document;
import org.textanalyzer.documentimporter.IDocument;

public class AnalyzeTaskInformation implements IAnalyzeTaskInformation {
	IDocument document;
	IProfileInformation profile;
	List<String> wordList;

	@Override
	public void setDocument(IDocument myDocument) {
		this.document = myDocument;
	}

	@Override
	public void setProfile(IProfileInformation myProfile) {
		this.profile = myProfile;
	}

	@Override
	public void setWordList(List<String> myWordList) {
		this.wordList = myWordList;
	}

	@Override
	public List<String> getWordList(int count) {
		return wordList;
	}

	@Override
	public IProfileInformation getProfile() {
		return profile;
	}

	@Override
	public IDocument getDocument() {
		// For Debug:
		return new Document();
		// Later:
		//return document;
	}

}
