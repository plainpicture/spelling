
package com.flakks.spelling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CorrectionLookup implements QueryLookup {
	private Map<String, Correction> cache;
	public int sumDistance;
	
	public CorrectionLookup() {
		sumDistance = 0;
		
		cache = new HashMap<String, Correction>();
	}
	
	public abstract Correction correct(String lookupString, int maxEdits);
	
	public QueryMatch lookup(List<String> tokens, int offset) {
		int maxOffset = Math.min(offset + 3, tokens.size());
		int maxK = maxOffset - offset;
		String resultString = null;
		int resultOffset = -1;
		int distance = -1;
		int partialDistance = -1;
		int frequency = 0;
		
		for(int k = 1; k <= maxK; k++) {
			String currentResult = null;
			int currentDistance = 0;
			int currentPartialDistance = 0;
			int currentFrequency = 0;
			
			for(int i = offset; i < maxOffset; i += k) {
				String lookupString = StringHelper.sliceJoin(" ", tokens, i, Math.min(Math.min(i + k, tokens.size()), maxOffset));
				
				Correction correction = null;
				
				if(lookupString.length() > 3) {
					correction = cache.get(lookupString);
					
					if(correction == null) {
						correction = correct(lookupString, lookupString.length() > 4 ? 2 : 1);
						
						cache.put(lookupString, correction);
					}
				} else {
					correction = null;
				}

				if(correction == null) {					
					if(i == offset)
						currentResult = lookupString;
					
					currentDistance += lookupString.length() / 2 + 1;
					
					if(i == offset)
						currentPartialDistance = lookupString.length() / 2 + 1;
				} else {					
					if(i == offset)
						currentResult = correction.token;
					
					currentDistance += correction.distance;
					currentFrequency += correction.frequency;
					
					if(i == offset)
						currentPartialDistance = correction.distance;
				}
			}
			
			if(distance == -1 || currentDistance < distance || (currentDistance == distance && currentFrequency > frequency)) {
				distance = currentDistance;
				partialDistance = currentPartialDistance;
				resultString = currentResult;
				resultOffset = offset + k;
			}
		}
		
		sumDistance += partialDistance;
		
		return new QueryMatch(resultString, resultOffset);
	}
}