
package com.flakks.spelling;

public class Suggestion implements Comparable<Suggestion> {
	private String token;
	private int frequency;
	private int distance;
	
	public Suggestion(String token, int distance, int frequency) {
		this.token = token;
		this.distance = distance;
		this.frequency = frequency;
	}
	
	public int compareTo(Suggestion suggestion) {
		int distanceCompare = Integer.compare(distance, suggestion.distance);
		
		if(distanceCompare != 0)
			return distanceCompare;
		
		return Integer.compare(frequency * -1, suggestion.frequency * -1);
	}
	
	public String getToken() {
		return token;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public int getDistance() {
		return distance;
	}
}