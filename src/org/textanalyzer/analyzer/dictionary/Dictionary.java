/**
 * 
 */
package org.textanalyzer.analyzer.dictionary;

//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLConnection;
import java.util.Scanner;

import org.textanalyzer.database.DBDictionary;



/**
 * @author Brab
 * 
 */
public class Dictionary implements IDictionary{

	DBDictionary db;
	
	public Dictionary(){
		db = new DBDictionary();
	}
	/*private DBDictionary dbDict;
	
	public Dictionary(){
		dbDict = new DBDictionary();
	}*/
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.textanalyzer.analyzer.dictionary.IDictionary#getWordStatus(java.lang
	 * .String)
	 */
	@SuppressWarnings("resource")
	@Override
	public WordStatus getWordStatus(String myWord) {
		
		//Declaration
		String Abfrage ="";
	    String wikiAbfrage ="";  //Variable f�r den Wiktionary Output
	    String var = myWord;                                      	//Zu �berpr�fendes Wort
	    WordStatus Art = WordStatus.WRONG;                    		//Wortart identifier
	    int i=0;
	    
		InputStream stream = null; 
        InputStream wikiStream = null;
		 
        try
		 {	
        	WordStatus dbWordStatus=db.getWordStatus(myWord);
		    		    
		    if(dbWordStatus!=null){										//Lokale DB abfragen
		    	return dbWordStatus;									//R�ckgabe aus lokaler DB
		    } else {		    			    	
		    	
		    		URL url = new URL( "http://www.duden.de/rechtschreibung/"+var ); 
		         
				    	stream = url.openStream(); //stream �ffnen 
				    	Abfrage= new Scanner(stream).useDelimiter( "\\Z" ).next(); //Den Stream in die Variable schreiben, bei einer leerstelle nicht stoppen

				    	//chwaches Verb
				    	if(Abfrage.contains("<span class=\"wortart\">Adjektiv")) Art = WordStatus.ADJECTIV;
				    	else if(Abfrage.contains("<div class=\"field-item even\">Adjektiv")) Art = WordStatus.ADJECTIV;
					    else if(Abfrage.contains("<span class=\"wortart\">Substantiv")) Art = WordStatus.NOMEN;
					    else if(Abfrage.contains("<div class=\"field-item even\">Substantiv")) Art = WordStatus.NOMEN;				    	
				    	else if(Abfrage.contains("<div class=\"field-item even\">schwaches Verb")) Art = WordStatus.VERB;
					    else if(Abfrage.contains("<span class=\"wortart\">schwaches Verb")) Art = WordStatus.VERB;
					    else if(Abfrage.contains("<div class=\"field-item even\">starkes Verb")) Art = WordStatus.VERB;
					    else if(Abfrage.contains("<span class=\"wortart\">starkes Verb")) Art = WordStatus.VERB;
					    else if(Abfrage.contains("<div class=\"field-item even\">unregelmäßiges Verb"))Art = WordStatus.VERB;
					    else Art=WordStatus.WRONG;	
				    	db.setWordStatus(var, Art);
					    return Art;	
					    		    
				    }
		} catch (java.io.FileNotFoundException e){
			try{
			URL url_wiktionary = new URL( "http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles="+var );
			System.out.println("Wiki Schleife");
			while(i<=1){
		    	wikiStream = url_wiktionary.openStream();
		    	//Abfrage= new Scanner(stream).useDelimiter( "\\Z" ).next();
		    	wikiAbfrage = new Scanner(wikiStream).useDelimiter( "\\Z" ).next();

				if (wikiAbfrage.contains("Adjektiv"))
				Art = WordStatus.ADJECTIV;
				else if (wikiAbfrage.contains("Substantiv"))
				Art = WordStatus.NOMEN;
				else if (wikiAbfrage.contains("Verb"))
				Art = WordStatus.VERB;
				else if (wikiAbfrage.contains("Pr�position"))
				Art = WordStatus.PREPOSITION;
				else if (wikiAbfrage.contains("F�llwort"))
				Art = WordStatus.FILLER;
				else if (wikiAbfrage.contains("missing\" \""))
				Art = WordStatus.WRONG; // Wort nicht vorhanden}
				else Art= WordStatus.WRONG;
				System.out.println("Wiki:"+Art);
				db.setWordStatus(var, Art);
				return Art;
	    }
		}
		 catch (Exception e1) {
			e.printStackTrace();
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e2) {
					return WordStatus.WRONG;
				}
		}
		
	} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return Art;

	}}
