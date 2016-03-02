
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

public class QueryMapper {
	private QueryLookup queryLookup;
	
	public QueryMapper(QueryLookup queryLookup) {
		this.queryLookup = queryLookup;
	}
	
	public String map(String query) {
		List<String> tokens = tokenize(query);
		List<String> resultTokens = new ArrayList<String>();
		
		for(int i = 0; i < tokens.size(); i++) {
			int nextOperatorIndex = nextOperatorIndex(tokens, i);
			
			if(nextOperatorIndex == i) {
				resultTokens.add(tokens.get(i));
			} else {
				List<String> tokenGroup = tokens.subList(i, nextOperatorIndex);
				
				int u;
				
				for(u = 0; u < tokenGroup.size(); u++) {
					QueryMatch queryMatch = queryLookup.lookup(tokenGroup, u);
					
					resultTokens.add(queryMatch.getMatch());
					
					u = queryMatch.getOffset() - 1;
				}
				
				i += u - 1;
			}
		}
		
		return StringHelper.join(" ", resultTokens);
	}
	
	private int nextOperatorIndex(List<String> tokens, int offset) {
		for(int i = offset; i < tokens.size(); i++) {
			String token = tokens.get(i);
			
			if(token.equals("-") || token.equals("(") || token.equals(")") || token.equals("\"") || token.equals("+") || token.equals("|") || token.equals("&"))
				return i;
		}
		
		return tokens.size();
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
					result.add("\"");
					
					result.add(phrase.toString().trim());
					
					if(items[u].equals("\""))
						result.add("\"");
					
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
			.replaceAll("(\\s|^)\\+", " & ")
			.replaceAll("\\(", " ( ")
			.replaceAll("\\)", " ) ")
			.replaceAll("\\|", " | ")
			.replaceAll("\"", " \" ");
	}
}
