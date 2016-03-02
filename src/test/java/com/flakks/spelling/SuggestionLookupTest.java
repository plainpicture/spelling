
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SuggestionLookupTest extends TestCase {
    public SuggestionLookupTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(SuggestionLookupTest.class);
    }

    public void testLookup() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tfirst word\t1");
    	lines.add("en\tsecond entry\t2");
    	
    	App.dictionaries = App.createDictionaries(lines);
    	App.trieNodes = App.createTrieNodes(App.dictionaries);
    	
    	List<String> tokens = new ArrayList<String>();
    	tokens.add("firstt");
    	tokens.add("wot");
    	
    	QueryMatch queryMatch = new SuggestionLookup("en").lookup(tokens, 0);
    	
    	assertEquals("first wo", queryMatch.getMatch());
    	assertEquals(2, queryMatch.getOffset());
    }
}