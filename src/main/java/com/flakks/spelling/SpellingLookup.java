
package com.flakks.spelling;

public class SpellingLookup extends CorrectionLookup {
	private TrieNode trieNode;
	private Dictionary dictionary;
	
	public SpellingLookup(String locale) {
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
		
		return new Automaton(lookupString, lookupString.length() > 4 ? 2 : 1).correct(trieNode);
	}
}