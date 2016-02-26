
package com.flakks.spelling;

import java.util.List;

public class StringHelper {
	public static String sliceJoin(String joinString, List<String> list, int from, int to) {
		StringBuffer buffer = new StringBuffer();
		
		for(int i = from; i < to; i++) {
			if(i != from && list.get(i).length() > 0)
				buffer.append(" ");
			
			buffer.append(list.get(i));
		}
		
		return buffer.toString();
	}
	
	public static String join(String joinString, List<String> list) {
		return sliceJoin(joinString, list, 0, list.size());
	}
}