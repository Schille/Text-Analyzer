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
	public List<String> getWordList() {
		return wordList;
	}

	@Override
	public IProfileInformation getProfile() {
		return profile;
	}

	@Override
	public IDocument getDocument() {
		// For Debug:
		IDocument doc = new Document();
		//doc.setText("Damit Ihr indess erkennt, woher dieser ganze Irrthum gekommen ist, und weshalb man die Lust anklagt und den Schmerz lobet, so will ich Euch Alles eröffnen und auseinander setzen, was jener Begründer der Wahrheit und gleichsam Baumeister des glücklichen Lebens selbst darüber gesagt hat. Niemand, sagt er, verschmähe, oder hasse, oder fliehe die Lust als solche, sondern weil grosse Schmerzen ihr folgen, wenn man nicht mit Vernunft ihr nachzugehen verstehe. Ebenso werde der Schmerz als solcher von Niemand geliebt, gesucht und verlangt, sondern weil mitunter solche Zeiten eintreten, dass man mittelst Arbeiten und Schmerzen eine grosse Lust sich zu verschaften suchen müsse. Um hier gleich bei dem Einfachsten stehen zu bleiben, so würde Niemand von uns anstrengende körperliche Uebungen vornehmen, wenn er nicht einen Vortheil davon erwartete. Wer dürfte aber wohl Den tadeln, der nach einer Lust verlangt, welcher keine Unannehmlichkeit folgt, oder der einem Schmerze ausweicht, aus dem keine Lust hervorgeht?");
		return doc;
		// Later:
		//return document;
	}

}
