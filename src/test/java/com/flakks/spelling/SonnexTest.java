
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SonnexTest extends TestCase {
    public SonnexTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(SonnexTest.class);
    }
    
    public void testEncode() {
    	Sonnex sonnex = new Sonnex();
    	
    	assertEquals("im1", sonnex.encode("illemain"));
    	assertEquals("vii", sonnex.encode("viille"));
    	assertEquals("frateE", sonnex.encode("fratteué"));
    }
}
