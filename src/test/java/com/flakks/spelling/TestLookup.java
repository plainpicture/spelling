
package com.flakks.spelling;

import java.util.List;

public class TestLookup implements QueryLookup {
	private int count;
	
	public TestLookup() {
		count = 0;
	}

	public QueryMatch lookup(List<String> tokens, int offset) {
		count += 1;

		return new QueryMatch("test" + count, offset + 1);
	}
}
