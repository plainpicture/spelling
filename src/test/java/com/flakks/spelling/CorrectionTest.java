
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CorrectionTest extends TestCase {
    public CorrectionTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(CorrectionTest.class);
    }
    
    public void testSuggestion() {
    	Correction correction = new Correction("token", 1, 2);
    	
    	assertEquals("token", correction.token);
    	assertEquals(1, correction.distance);
    	assertEquals(2, correction.frequency);
    }
    
    public void testCompareTo() {
    	assertEquals(-1, new Correction("token 1", 1, 2).compareTo(new Correction("token 2", 2, 3)));
    	assertEquals(0, new Correction("token 1", 1, 2).compareTo(new Correction("token 2", 1, 2)));
    	assertEquals(1, new Correction("token 1", 2, 3).compareTo(new Correction("token 2", 1, 2)));
    }
}
