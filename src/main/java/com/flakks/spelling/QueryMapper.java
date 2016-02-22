
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

abstract public class QueryMapper {
	public int maxWords;
	
	public QueryMapper() {
		this.maxWords = 3;
	}
	
	public QueryMapper(int maxWords) {
		this.maxWords = maxWords;
	}
	
	protected abstract String lookup(String str);
	
	public String map(String query) {
		StringBuffer result = new StringBuffer();
		List<String> tokens = tokenize(query);
		
		for(int i = 0; i < tokens.size(); i++) {
			for(int u = Math.min(i + maxWords - 1, tokens.size() - 1); u >= i; u--) {
				String request = String.join(" ", tokens.subList(i, u + 1)).trim();
				
				if(request.length() > 0) {
					String response = lookup(request);
						
					if(response != null && response.trim().length() > 0) {
						i = u;
						
						result.append(" ").append(response);
					} else if(i == u) {
						result.append(" ").append(request);
					}
				}
			}
		}
		
		return result.toString().trim();
	}
	
	private List<String> tokenize(String query) {
		String[] items = wrapOperators(query).split("\\s+");
		List<String> result = new ArrayList<String>();
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] == "\"") {
				StringBuffer phrase = new StringBuffer();
				
				int u;
				
				for(u = i + 1; u < items.length && items[u] != "\""; u++)
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
