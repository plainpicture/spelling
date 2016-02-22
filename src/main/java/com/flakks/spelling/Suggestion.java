
package com.flakks.spelling;

public class Suggestion implements Comparable<Suggestion> {
	public String token;
	public int distance;
	public int frequency;
	
	public Suggestion(String token, int distance, int frequency) {
		this.token = token;
		this.distance = distance;
		this.frequency = frequency;
	}
	
	public int compareTo(Suggestion suggestion) {		
		if(distance < suggestion.distance)
			return -1;
		
		if(distance > suggestion.distance)
			return 1;

		if(frequency < suggestion.frequency)
			return 1;
		else if(frequency > suggestion.frequency)
			return -1;
		
		return 0;
	}
}