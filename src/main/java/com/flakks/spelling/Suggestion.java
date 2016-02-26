
package com.flakks.spelling;

public class Suggestion implements Comparable<Suggestion> {
	public String token;
	public int frequency;
	public int distance;
	
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
}