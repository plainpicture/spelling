
package com.flakks.spelling;

public class SuggestionLookup extends CorrectionLookup {
	private TrieNode trieNode;
	
	public SuggestionLookup(String locale) {
		super();
		
		trieNode = App.trieNodes.get(locale);
	}
	
	public Correction correct(String lookupString, int maxEdits) {
		return new Automaton(lookupString, lookupString.length() > 4 ? 2 : 1).correctPrefix(trieNode);
	}
}