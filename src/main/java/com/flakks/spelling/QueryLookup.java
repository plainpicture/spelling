
package com.flakks.spelling;

import java.util.List;

public interface QueryLookup {
	public abstract QueryMatch lookup(List<String> tokens, int offset);
}