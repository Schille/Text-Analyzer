package org.textanalyzer.reportcreator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.textanalyzer.database.IResultSet;

/**
 * 
 * @author Robert Stein
 * @param <E>
 *
 */

public class Averager<E> {
	
	private LinkedList<IResultSet> resultlist;
	
	public Averager(List<IResultSet> myResultset) {
		resultlist = (LinkedList<IResultSet>) myResultset;
		
	}
	/**
	 * 
	 * @return SortedMap which contains a map of the most used words.
	 */
	
	static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int k = e1.getValue().compareTo(e2.getValue());
	                return -k;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	public LinkedHashMap<String,Integer> getWordMap() {
		
	    Map<String,Integer> mostWords = new TreeMap<String,Integer>();
		Iterator<?> k = resultlist.iterator();
		
		
		while(k.hasNext()) {
			Map<String,Integer> temp3 = ((IResultSet) k.next()).getMostFrequentWord(10);
			Iterator<String>h = temp3.keySet().iterator();
				while(h.hasNext()) {
					String m = h.next();
					if(mostWords.containsKey(m)) {
						mostWords.put(m, mostWords.get(m)+temp3.get(m));
					}
					else {
						mostWords.put(m, temp3.get(m));
					}
					
			}
		}
		
		
		SortedSet<Entry<String, Integer>> theone = entriesSortedByValues(mostWords);
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		for(Entry<String,Integer> key : theone) {
			result.put(key.getKey(), key.getValue());
		}
		return result;
		
	}
	
	/**
	 * 
	 * @return a map which contains the most used words from custom lists.
	 */
	public LinkedHashMap<String,Integer> getListMap() {
		
		
		Map<String,Integer> mostWords = new TreeMap<String,Integer>();
		Iterator<?> k = resultlist.iterator();
		
		
		while(k.hasNext()) {
			Map<String,Integer> temp3 = ((IResultSet) k.next()).getCustomWordCount();
			Iterator<String>h = temp3.keySet().iterator();
				while(h.hasNext()) {
					String m = h.next();
					if(mostWords.containsKey(m)) {
						mostWords.put(m, mostWords.get(m)+temp3.get(m));
					}
					else {
						mostWords.put(m, temp3.get(m));
					}
					
			}
		}
		
		
		SortedSet<Entry<String, Integer>> theone = entriesSortedByValues(mostWords);
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
		for(Entry<String,Integer> key : theone) {
			result.put(key.getKey(), key.getValue());
		}
		return result;
		
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
