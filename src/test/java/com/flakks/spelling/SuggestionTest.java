
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
    
    public void testCompareTo() {
    	assertEquals(-1, new Suggestion("suggestion 1", 1, 2).compareTo(new Suggestion("suggestion 2", 2, 3)));
    	assertEquals(0, new Suggestion("suggestion 1", 1, 2).compareTo(new Suggestion("suggestion 1", 1, 2)));
    	assertEquals(1, new Suggestion("suggestion 1", 2, 3).compareTo(new Suggestion("suggestion 1", 1, 2)));
    }
}
