package org.textanalyzer.database;

import java.util.Date;

import org.textanalyzer.documentimporter.DocumentFormat;

/**
 * Document
 * @author Michael Schilonka
 * @version 12.11.2012
 */

/**
 * @author Michael Schilonka
 * Setters and Getters for Document
 */
public class Document implements IDocument {

	// private class variables
	private String text;
	private String documentPath;
	private Date importDate;
	private DocumentFormat documentFormat;
	private String fileName;

	// ---------------------- Getters -----------------------
	@Override
	public String getDocumentPath() {
		return documentPath;
	}

	@Override
	public Date getImportDate() {
		return importDate;
	}

	@Override
	public DocumentFormat getDocumentFormat() {
		return documentFormat;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	// ---------------------- Setters -----------------------
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public void setDocumentFormat(DocumentFormat documentFormat) {
		this.documentFormat = documentFormat;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setText(String text) {
		this.text = text;
	}
}
