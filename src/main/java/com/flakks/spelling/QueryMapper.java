
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

public class QueryMapper {
	public int maxWords;
	private QueryLookup queryLookup;
	
	public QueryMapper(QueryLookup queryLookup) {
		this.queryLookup = queryLookup;
		this.maxWords = 3;
	}
	
	public QueryMapper(int maxWords) {
		this.maxWords = maxWords;
	}
	
	public String map(String query) {
		StringBuffer result = new StringBuffer();
		List<String> tokens = tokenize(query);
		
		for(int i = 0; i < tokens.size(); i++) {
			List<String> requests = new ArrayList<String>();
			
			int max = Math.min(i + maxWords - 1, tokens.size() - 1);
			
			for(int u = max; u >= i; u--)
				requests.add(String.join(" ", tokens.subList(i, u + 1)).trim());
				
			QueryMatch queryMatch = queryLookup.lookup(requests);
						
			if(queryMatch != null && queryMatch.match.trim().length() > 0) {
				i = max - queryMatch.index;
						
				result.append(" ").append(queryMatch.match);
			} else if(i == max - queryMatch.index) {
				result.append(" ").append(queryMatch.match);
			}
		}
		
		return result.toString().trim();
	}
	
	private List<String> tokenize(String query) {
		String[] items = wrapOperators(query).split("\\s+");
		List<String> result = new ArrayList<String>();
		
		for(int i = 0; i < items.length; i++) {
			if(items[i].equals("\"")) {
				StringBuffer phrase = new StringBuffer();
				
				int u;
				
				for(u = i + 1; u < items.length && !items[u].equals("\""); u++)
					phrase.append(" ").append(items[u].trim());
				
				if(u < items.length) {
					result.add(phrase.toString().trim());
					
					i = u;
				}
			} else {
				result.add(items[i].trim());
			}
		}
		
		return result;
	}
	
	private String wrapOperators(String query) {
		return query
			.replaceAll("(\\s|^)\\-", " - ")
			.replaceAll("(\\s|^)\\+", " + ")
			.replaceAll("\\(", " ( ")
			.replaceAll("\\)", " ) ")
			.replaceAll("\\|", " | ")
			.replaceAll("\"", " \" ");
	}
}
