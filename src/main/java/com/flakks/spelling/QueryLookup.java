
package com.flakks.spelling;

import java.util.List;

public interface QueryLookup {
	public abstract String lookup(String lookupString, boolean last);
	public abstract QueryMatch lookup(List<String> lookupStrings);
}