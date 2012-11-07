/**
 * 
 */
package org.textanalyzer.analyzer.dictionary;


//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream; 
import java.net.URL;
//import java.net.URLConnection;
import java.util.Scanner;

import org.textanalyzer.database.DBDictionary;



/**
 * @author Brab
 *
 */
public class Dictionary implements IDictionary{

	/* (non-Javadoc)
	 * @see org.textanalyzer.analyzer.dictionary.IDictionary#getWordStatus(java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public WordStatus getWordStatus(String myWord) {

		
		InputStream stream = null; 
        
		 try
		 {
		    String Abfrage ="";                                         //Variable für den Wiktionary Output
		    String var = myWord;                                      	//Zu überprüfendes Wort
		    WordStatus Art = WordStatus.WRONG;                    		//Wortart identifier
		    int i=0;
		    DBDictionary db = new DBDictionary();
		    
		    WordStatus dbWordStatus=db.getWordStatus(myWord);
		    
		    
		    if(dbWordStatus!=null){										//Lokale DB abfragen
		    	return dbWordStatus;									//Rückgabe aus lokaler DB
		    } else {
		    			    	
				    /* URL wird festgelegt
				     * &prop= welche Eigenschaft wird abgefragt (Kategorien zu denen das Wort zugeteilt wurde)
				     * &format= gibt das Format an welches zurückgeliefert werden soll (XML, HTML etc.)
				     */
				
		    	URL url = new URL( "http://www.duden.de/rechtschreibung/"+var ); 
				    
				    
				    while(i<=1){
				    	stream = url.openStream(); //stream öffnen 
		
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
					    else if(Abfrage.contains("<div class=\"field-item even\">unregelmÃ¤ÃŸiges Verb")) Art = WordStatus.VERB;
					    else if(Abfrage.contains("Präposition")) Art = WordStatus.PREPOSITION;
					    else if(Abfrage.contains("Füllwort")) Art= WordStatus.FILLER;
					    else if(Abfrage.contains("missing\" \"")) Art= WordStatus.FILLER;			//Wort nicht vorhanden
					    
					    
					    System.out.println(Abfrage);
					    db.setWordStatus(myWord, Art);
					    return Art;
				    
				    
				    }
			}
		 } 
		catch ( Exception e ) {
		 e.printStackTrace();
		 }
		 finally {
		 if ( stream != null )
		 try { stream.close(); } catch (IOException e ) {return WordStatus.WRONG; }
		 }
		return WordStatus.WRONG;
	}

}
