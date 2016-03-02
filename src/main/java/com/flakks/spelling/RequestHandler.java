
package com.flakks.spelling;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestHandler {
	private String request;
	
	public RequestHandler(String request) {
		this.request = request;
	}
	
	public String process() {
		JSONObject jsonRequest = new JSONObject(request);
		
		long time = System.currentTimeMillis();
		String locale = jsonRequest.getString("locale");
		String query = jsonRequest.getString("query").toLowerCase();
		
		if(jsonRequest.getString("operation").equals("correct")) {
			SpellingLookup spellingLookup = new SpellingLookup(locale);
			QueryMapper queryMapper = new QueryMapper(spellingLookup);
			
			String mappedQuery = queryMapper.map(query);
			
			time = System.currentTimeMillis() - time;
			
			return new JSONObject().put("query", mappedQuery).put("took", time).put("distance", spellingLookup.getSumDistance()).toString();
		} else if(jsonRequest.getString("operation").equals("suggest")) {
			List<Suggestion> suggestions = new SpellingSuggestor(locale).suggest(query);
					
			JSONObject response = new JSONObject();
			JSONArray jsonSuggestions = new JSONArray();

			for(Suggestion suggestion : suggestions)
				jsonSuggestions.put(new JSONObject().put("query", suggestion.getToken()).put("frequency", suggestion.getFrequency()));
	
			time = System.currentTimeMillis() - time;
					
			return response.put("suggestions", jsonSuggestions).put("took", time).toString();
		}

		return "{}";
	}
}