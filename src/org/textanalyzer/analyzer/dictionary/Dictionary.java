/**
 * 
 */
package org.textanalyzer.analyzer.dictionary;


//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream; 
import java.net.URL;
import java.util.HashMap;
//import java.net.URLConnection;
import java.util.Scanner;
/**
 * @author Brab
 *
 */
public class Dictionary implements IDictionary {

	/* (non-Javadoc)
	 * @see org.textanalyzer.analyzer.dictionary.IDictionary#getWordStatus(java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public WordStatus getWordStatus(String myWord) {
		HashMap<String,WordStatus> cache = new HashMap<String,WordStatus> ();
		if(cache.get(myWord)!=null){
			return cache.get(myWord);
			
		}
		
		InputStream is = null; 
        
		 try
		 {
		    String Abfrage ="";                                         //Variable f�r den Wiktionary Output
		    String var = myWord;                                      	//Zu �berpr�fendes Wort
		    WordStatus Art = WordStatus.WRONG;                    		//Wortart identifier
		    int i=0;                                                    
		    /*URL wird festgelegt
		     * &prop= welche Eigenschaft wird abgefragt (Kategorien zu denen das Wort zugeteilt wurde)
		     * &format= gibt das Format an welches zurückgeliefert werden soll (XML, HTML etc.)
		     */
		    URL url = new URL( " http://de.wiktionary.org/w/api.php?action=query&prop=categories&format=xml&titles="+var ); 
		    while(i<=1){
		    is = url.openStream(); //stream �ffnen 

		    Abfrage= new Scanner(is).useDelimiter( "\\Z" ).next(); //Den Stream in die Variable schreiben, bei einer leerstelle nicht stoppen
		    
			    /*Muss ergänzt werden
			     *Es können alle Kategorien abgefragt werden, also auch Präposition etc.
			     */
			    if(Abfrage.contains("Adjektiv")) Art = WordStatus.ADJECTIV;
			    else if(Abfrage.contains("Substantiv")) Art = WordStatus.NOMEN;
			    else if(Abfrage.contains("Verb")) Art = WordStatus.VERB;
			    else if(Abfrage.contains("F�llwort")) Art= WordStatus.FILLER;
			    return Art;
		   // System.out.println(Art);
		    //i++;
		   }
		 } 
		catch ( Exception e ) {
		 e.printStackTrace();
		 }
		 finally {
		 if ( is != null )
		 try { is.close(); } catch (IOException e ) {return WordStatus.WRONG; }
		 }
		return WordStatus.WRONG;
	}

}
