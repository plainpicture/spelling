
package com.flakks.spelling;

import org.json.JSONObject;

public class RequestHandler {
	private String request;
	
	public RequestHandler(String request) {
		this.request = request;
	}
	
	public String process() {
		JSONObject jsonRequest = new JSONObject(request);

		long time = System.currentTimeMillis();
		
		SpellingQueryMapper mapper = new SpellingQueryMapper(jsonRequest.getString("locale"));
		
		String result = mapper.map(jsonRequest.getString("query"));
		
		time = System.currentTimeMillis() - time;
		
		return new JSONObject().put("query", result).put("took", time).put("distance",  mapper.sumDistance).toString();
	}
}