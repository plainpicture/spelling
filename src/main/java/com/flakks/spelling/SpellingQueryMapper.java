
package com.flakks.spelling;

public class SpellingQueryMapper extends QueryMapper {
	private Dictionary dictionary;
	private TrieNode trieNode;
	public int sumDistance;
	
	public SpellingQueryMapper(String locale) {
		dictionary = App.dictionaries.get(locale);
		trieNode = App.trieNodes.get(locale);
		sumDistance = 0;
	}

	public String map(String query) {
		sumDistance = 0;
		
		return super.map(query);
	}
	
	protected String lookup(String str) {
		if(str.length() < 4)
			return str;
			
		if(dictionary.containsKey(str))
			return str;
		
		Suggestion suggestion = new Automaton(str, str.length() > 4 ? 2 : 1).suggest(trieNode);
			
		if(suggestion == null)
			return null;
		
		sumDistance += suggestion.distance;
		
		return suggestion.token;
	}
}