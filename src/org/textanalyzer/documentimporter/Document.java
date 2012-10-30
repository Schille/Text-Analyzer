package org.textanalyzer.documentimporter;

import java.net.URI;
import java.util.Date;

public class Document implements IDocument {

	private String text;
	private String documentPath;
	private Date importDate;
	private DocumentFormat documentFormat;
	private String fileName;
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

	public void setText(String text){
		this.text = text;
	}
}
