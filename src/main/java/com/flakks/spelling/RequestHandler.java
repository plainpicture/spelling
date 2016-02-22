
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
	
	/*
	public void run() {
		try {
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				JSONObject request = new JSONObject(bufferedReader.readLine());

				long time = System.currentTimeMillis();
				
				SpellingQueryMapper mapper = new SpellingQueryMapper(request.getString("locale"));
				
				String result = mapper.map(request.getString("query"));
				
				time = System.currentTimeMillis() - time;
				
				OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
				writer.write(new JSONObject().put("query", result).put("took", time).put("distance",  mapper.sumDistance).toString());
				writer.write("\n");
				writer.flush();
			} finally {
				socket.close();
			}
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	*/
}