package com.flakks.spelling;

public class QueryMatch {
	private String match;
	private int offset;
	
	public QueryMatch(String match, int offset) {
		this.match = match;
		this.offset = offset;
	}
	
	public String getMatch() {
		return match;
	}
	
	public int getOffset() {
		return offset;
	}
}
