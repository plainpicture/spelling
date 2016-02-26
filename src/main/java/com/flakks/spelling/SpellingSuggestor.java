
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpellingSuggestor {
	private String locale;
	
	public SpellingSuggestor(String locale) {
		this.locale = locale;
	}
	
	public List<Suggestion> suggest(String query) {
		List<String> tokens = Arrays.asList(query.split("\\s+"));
		List<Suggestion> suggestions = new ArrayList<Suggestion>();

		for(int k = 1; k <= Math.min(3, tokens.size()); k++) {
			TrieNode node = App.trieNodes.get(locale);
			
			String suggestionQuery = StringHelper.sliceJoin(" ", tokens, tokens.size() - k, tokens.size());
			SuggestionLookup suggestionLookup = new SuggestionLookup(locale);
			suggestionQuery = new QueryMapper(suggestionLookup).map(suggestionQuery);
			
			for(int i = 0; i < suggestionQuery.length(); i++) {
				node = node.children.get(suggestionQuery.charAt(i));
				
				if(node == null)
					break;
			}

			if(node != null) {
				SpellingLookup spellingLookup = new SpellingLookup(locale);
				String prefix = StringHelper.sliceJoin(" ", tokens, 0, tokens.size() - k);
				
				if(tokens.size() - k > 0)
					prefix = new QueryMapper(spellingLookup).map(prefix);
				
				for(Suggestion suggestion : node.suggestions)
					suggestions.add(new Suggestion((prefix + " " + suggestion.token).trim(), spellingLookup.sumDistance + suggestionLookup.sumDistance, suggestion.frequency));
			}
		}

		Collections.sort(suggestions);
		
		suggestions = suggestions.subList(0, Math.min(10, suggestions.size()));
		
		return suggestions;
	}
}
