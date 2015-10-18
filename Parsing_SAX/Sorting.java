package Parsing_SAX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Sorting {
	
	public  List<String> sortingByValue(HashMap<String, Integer> hmap) {
		Map<String, Integer> map = sortByValues(hmap);
		//System.out.println(""+map  );
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.reverse(list);
		List<String> topUsers = list.subList(0, 10);
		//System.out.println("List of top 10 owner user ids : " +topUsers );
		return topUsers;
	}

	public  Map<String, Integer> sortByValues(HashMap<String, Integer> map) {
		List<String> list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap<String, Integer>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

}
