
package com.flakks.spelling;

public class SuggestionLookup extends CorrectionLookup {
	private TrieNode trieNode;
	private Dictionary dictionary;
	
	public SuggestionLookup(String locale) {
		super();
		
		trieNode = App.trieNodes.get(locale);
		dictionary = App.dictionaries.get(locale);
	}
	
	public Correction correct(String lookupString, int maxEdits) {
		Integer frequency = dictionary.get(lookupString);
		
		if(frequency != null)
			return new Correction(lookupString, 0, frequency);
		
		if(lookupString.length() < 4)
			return null;
		
		return new Automaton(lookupString, lookupString.length() > 5 ? 2 : 1).correctPrefix(trieNode);
	}
}