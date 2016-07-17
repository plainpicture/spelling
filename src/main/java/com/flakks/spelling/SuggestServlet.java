
package com.flakks.spelling;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class SuggestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long time = System.currentTimeMillis();
		String locale = request.getParameter("locale");
		String query = request.getParameter("query").toLowerCase();
		
		List<Suggestion> suggestions = new SpellingSuggestor(locale).suggest(query);
		JSONArray jsonSuggestions = new JSONArray();

		for(Suggestion suggestion : suggestions)
			jsonSuggestions.put(new JSONObject().put("query", suggestion.getToken()).put("frequency", suggestion.getFrequency()));
	
		time = System.currentTimeMillis() - time;
					
		JSONObject json = new JSONObject();
		json.put("suggestions", jsonSuggestions);
		json.put("took", time);

    	response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(json.toString());
    }
}
