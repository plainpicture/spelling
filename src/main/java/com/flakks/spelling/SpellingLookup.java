
package com.flakks.spelling;

import java.util.List;

public class SpellingLookup implements QueryLookup {
	private Dictionary dictionary;
	private TrieNode trieNode;
	public int sumDistance;
	
	public SpellingLookup(String locale) {
		dictionary = App.dictionaries.get(locale);
		trieNode = App.trieNodes.get(locale);
		sumDistance = 0;
	}
	
	public String lookup(String lookupString, boolean last) {
		if(dictionary.containsKey(lookupString))
			return lookupString;
		
		int length = lookupString.length();
		int maxEdits = lookupString.length() > 4 ? 2 : 1;
		
		Correction correction = new Automaton(lookupString, maxEdits).correct(trieNode);

		if(correction == null) {
			if(last)
				sumDistance += maxEdits + 1;
				
			return null;
		}
		
		sumDistance += correction.distance;
		
		if(length < 4)
			return lookupString;
		else
			return correction.token;
	}
	
	public QueryMatch lookup(List<String> lookupStrings) {
		String bestString = null;
		int bestIndex = -1;
		int minDistance = 3;
		int size = lookupStrings.size();
		
		for(int i = 0; i < size; i++) {
			String lookupString = lookupStrings.get(i);
		
			if(dictionary.containsKey(lookupString))
				return new QueryMatch(lookupString, i);

			int maxEdits = lookupString.length() > 4 ? 2 : 1;
			
			Correction correction = new Automaton(lookupString, maxEdits).correct(trieNode);

			if(correction == null)
				continue;
			
			if(correction.distance < minDistance) {
				minDistance = correction.distance;
				bestString = correction.token;
				bestIndex = i;
			}
		}
		
		sumDistance += minDistance;
		
		if(bestString == null || bestString.length() < 3)
			return new QueryMatch(lookupStrings.get(lookupStrings.size() - 1), bestIndex);
		else
			return new QueryMatch(bestString, bestIndex);
	}
}