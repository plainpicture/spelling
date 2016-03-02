
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class QueryMatchTest extends TestCase {
    public QueryMatchTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(QueryMatchTest.class);
    }
    
    public void testQueryMatch() {
    	QueryMatch queryMatch = new QueryMatch("match", 1);
    	
    	assertEquals("match", queryMatch.getMatch());
    	assertEquals(1, queryMatch.getOffset());
    }
}