package org.textanalyzer.reportcreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.textanalyzer.database.IResultSet;

/**
 * 
 * @author Robert Stein
 *
 */

public class Averager {
	
	private LinkedList<IResultSet> resultlist;
	
	public Averager(List<IResultSet> myResultset) {
		resultlist = (LinkedList<IResultSet>) myResultset;
		
	}
	/**
	 * 
	 * @return SortedMap which contains a map of the most used words.
	 */
	public LinkedHashMap<String,Integer> getWordMap() {
		
		
		
		LinkedHashMap<String, Integer> mostWords = new LinkedHashMap<String, Integer>();
		Iterator<?> k = resultlist.iterator();
		
		
		while(k.hasNext()) {
			Map<String,Integer> temp3 = ((IResultSet) k.next()).getMostFrequentWord(10);
		for(int i = 0; i < 10 && temp3.entrySet().iterator()
				.hasNext(); i++) {
			Map.Entry<String, Integer> temp_entry = null;
			
			temp_entry = temp3.entrySet().iterator().next();
		
			for (Map.Entry<String, Integer> entry : temp3.entrySet()) {
				if(temp_entry != null && temp_entry.getValue() <= entry.getValue())
					temp_entry = entry;
			}
			
			temp3.remove(temp_entry.getKey());
			mostWords.put(temp_entry.getKey(), temp_entry.getValue());
			}
		}
		
		
		return mostWords;
		
	}
	
	/**
	 * 
	 * @return a map which contains the most used words from custom lists.
	 */
	public SortedMap<String,Integer> getListMap() {
		
		SortedMap<String,Integer> listWord = new TreeMap<String,Integer>();
		
		Iterator<?> iterator = resultlist.iterator();
		while(iterator.hasNext()) {
			IResultSet temp_res = (IResultSet)iterator.next();
			Map<String,Integer> temp_most = temp_res.getCustomWordCount();
			
			for (Map.Entry<String, Integer> entry : temp_most.entrySet()) {
				if(listWord.containsKey(entry.getKey())){
				listWord.put(entry.getKey(), listWord.get(entry.getKey()) + entry.getValue());
				}
				else {
					listWord.put(entry.getKey() , entry.getValue() );
				}
			}
			
		}
		
		return listWord;
	}
	
	/**
	 * optional
	 * @return the most occurring mood of all texts of the given List
	 */
	public String averageMood() {
		String mood = new String();
		SortedMap<String,Integer> moods = new TreeMap<String,Integer>();
		SortedMap<Integer,String> moods_ordered = new TreeMap<Integer,String>();
		
		Iterator<?> iterator = resultlist.iterator();
		while(iterator.hasNext()) {
			IResultSet temp_res = (IResultSet)iterator.next();
			try {
			if(moods.containsKey(temp_res.getTextMood().name())) {
				moods.put(temp_res.getTextMood().name(), moods.get(temp_res.getTextMood().name())+1);
			}
			else {
				moods.put(temp_res.getTextMood().name(), 0);
			}
			}
			catch(NullPointerException e) {
				System.err.println("There is no mood for the given text.");
				return "";
			}
		}
			
		
		
		for (Map.Entry<String, Integer> entry : moods.entrySet()) {
			moods_ordered.put(entry.getValue(), entry.getKey());
		}
		mood = moods_ordered.get(moods_ordered.firstKey());
		
		return mood;
	}


}
