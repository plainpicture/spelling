
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SuggestionTest extends TestCase {
    public SuggestionTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(SuggestionTest.class);
    }
    
    public void testSuggestion() {
    	Suggestion suggestion = new Suggestion("token", 1, 2);
    	
    	assertEquals("token", suggestion.token);
    	assertEquals(1, suggestion.distance);
    	assertEquals(2, suggestion.frequency);
    }
    
    public void testCompareTo() {
    	assertEquals(-1, new Suggestion("token 1", 1, 2).compareTo(new Suggestion("token 2", 2, 3)));
    	assertEquals(0, new Suggestion("token 1", 1, 2).compareTo(new Suggestion("token 2", 1, 2)));
    	assertEquals(1, new Suggestion("token 1", 2, 3).compareTo(new Suggestion("token 2", 1, 2)));
    }
}
