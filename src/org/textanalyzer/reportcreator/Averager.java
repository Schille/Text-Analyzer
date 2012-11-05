package org.textanalyzer.reportcreator;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	private ArrayList<IResultSet> resultlist;
	
	public Averager(List<IResultSet> myResultset) {
		resultlist = (ArrayList<IResultSet>) myResultset;
		
	}
	
	public SortedMap<String,Integer> getWordMap() {
		
		SortedMap<String,Integer> mostWords = new TreeMap<String,Integer>();
		
		Iterator<?> iterator = resultlist.iterator();
		while(iterator.hasNext()) {
			IResultSet temp_res = (IResultSet)iterator.next();
			Map<String,Integer> temp_most = temp_res.getMostFrequentWord(10);
			
			for (Map.Entry<String, Integer> entry : temp_most.entrySet()) {
				if(mostWords.containsKey(entry.getKey())){
				mostWords.put(entry.getKey(), mostWords.get(entry.getKey()) + entry.getValue());
				}
				else {
					mostWords.put(entry.getKey() , entry.getValue() );
				}
			}
			
		}
		
		return mostWords;
		
	}
	
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


}
