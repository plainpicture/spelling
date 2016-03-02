
package com.flakks.spelling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CorrectionLookup implements QueryLookup {
	private Map<String, Correction> cache;
	private int sumDistance;
	
	public static int[][][] MATRIX = {
			{{0, 1}, {1, 1}, {2, 1}},
			{{0, 1}, {1, 2}},
			{{0, 2}, {2, 1}},
			{{0, 3}}
	};
	
	public CorrectionLookup() {
		sumDistance = 0;
		
		cache = new HashMap<String, Correction>();
	}
	
	public int getSumDistance() {
		return sumDistance;
	}
	
	public abstract Correction correct(String lookupString, int maxEdits);
	
	public QueryMatch lookup(List<String> tokens, int offset) {
		String resultString = null;
		int resultOffset = -1;
		float distance = -1;
		int realDistance = -1;
		int frequency = 0;
		
		for(int u = 0; u < MATRIX.length; u++) {
			String currentResult = null;
			float currentDistance = 0;
			int currentRealDistance = 0;
			int currentFrequency = 0;
			
			for(int v = 0; v < MATRIX[u].length; v++) {
				int from = offset + MATRIX[u][v][0];
				int to = Math.min(from + MATRIX[u][v][1], tokens.size());
				
				if(to <= from)
					continue;
				
				String lookupString = StringHelper.sliceJoin(" ", tokens, from, to);
				int numWords = to - from;
				
				Correction correction = cache.get(lookupString);
					
				if(correction == null) {
					correction = correct(lookupString, lookupString.length() > 4 ? 2 : 1);
						
					cache.put(lookupString, correction);
				}

				if(correction == null) {					
					if(from == offset)
						currentResult = lookupString;
					
					currentDistance += (lookupString.length() / 2 + 1) / (float)numWords;
				} else {
					if(from == offset) {
						currentResult = correction.getToken();
						currentRealDistance = correction.getDistance();
					}
					
					currentDistance += correction.getDistance() / (float)numWords;
					currentFrequency += correction.getFrequency();
				}
			}
			
			if(distance == -1 || currentDistance < distance || (currentDistance == distance && currentFrequency > frequency)) {
				distance = currentDistance;
				realDistance = currentRealDistance;
				resultString = currentResult;
				resultOffset = Math.min(offset + MATRIX[u][0][1], tokens.size());
			}
		}
		
		sumDistance += realDistance;
		
		return new QueryMatch(resultString, resultOffset);
	}
}