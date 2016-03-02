
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellingSuggestor {
	private String locale;
	
	public SpellingSuggestor(String locale) {
		this.locale = locale;
	}
	
	public List<Suggestion> suggest(String query) {
		List<Suggestion> suggestions = new ArrayList<Suggestion>();
		TrieNode node = App.trieNodes.get(locale);
			
		String suggestionQuery = query;
		SuggestionLookup suggestionLookup = new SuggestionLookup(locale);
		suggestionQuery = new QueryMapper(suggestionLookup).map(suggestionQuery);
			
		for(int i = 0; i < suggestionQuery.length(); i++) {
			node = node.children.get(suggestionQuery.charAt(i));
				
			if(node == null)
				break;
		}

		if(node != null) {
			for(Suggestion suggestion : node.suggestions)
				suggestions.add(new Suggestion(suggestion.getToken(), suggestionLookup.getSumDistance(), suggestion.getFrequency()));
	
			Collections.sort(suggestions);
		}
		
		return suggestions;
	}
}
