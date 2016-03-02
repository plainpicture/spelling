
package com.flakks.spelling;

public class Correction implements Comparable<Correction> {
	private String token;
	private int distance;
	private int frequency;
	private boolean phonetic;
	
	public String getToken() {
		return token;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public Correction(String token, String locale, String original, int distance, int frequency) {
		this.token = token;
		this.distance = distance;
		this.frequency = frequency;
		
		String tokenPhonetic = Phonetic.encode(token, locale);
		String originalPhonetic = Phonetic.encode(original, locale);

		phonetic = tokenPhonetic != null && originalPhonetic != null && tokenPhonetic.equals(originalPhonetic);
	}
	
	public int compareTo(Correction correction) {
		if(distance < correction.distance)
			return -1;
		
		if(distance > correction.distance)
			return 1;
		
		if(phonetic && !correction.phonetic)
			return -1;
		
		if(!phonetic && correction.phonetic)
			return 1;
		
		if(frequency < correction.frequency)
			return 1;
		
		if(frequency > correction.frequency)
			return -1;
		
		return 0;
	}
}