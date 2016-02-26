
package com.flakks.spelling;

public class Correction implements Comparable<Correction> {
	public String token;
	public int distance;
	public int frequency;
	
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