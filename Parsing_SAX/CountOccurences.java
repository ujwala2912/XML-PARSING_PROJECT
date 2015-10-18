package Parsing_SAX;

import java.util.ArrayList;
import java.util.HashMap;

public class CountOccurences {
	
	public  HashMap<String, Integer> countOccurrences(ArrayList<String> number ) {

		int c = 0;
		HashMap<String, Integer> stringsCount = new HashMap<>();
		for (String s : number) {
			if (!(stringsCount.keySet().contains(s))) {
				c = 1;
				stringsCount.put(s, c);
			} else {
				c = stringsCount.get(s) + 1;
				stringsCount.put(s, c);
			}
		}
		return stringsCount;

}
}
