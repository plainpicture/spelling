
package com.flakks.spelling;

public class Correction implements Comparable<Correction> {
	private String token;
	private int distance;
	private int frequency;
	
	public String getToken() {
		return token;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public Correction(String token, int distance, int frequency) {
		this.token = token;
		this.distance = distance;
		this.frequency = frequency;
	}
	
	public int compareTo(Correction correction) {		
		if(distance < correction.distance)
			return -1;
		
		if(distance > correction.distance)
			return 1;
		
		if(frequency < correction.frequency)
			return 1;
		
		if(frequency > correction.frequency)
			return -1;
		
		return 0;
	}
}