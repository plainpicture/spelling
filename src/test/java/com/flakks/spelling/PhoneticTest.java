
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PhoneticTest extends TestCase {
    public PhoneticTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(PhoneticTest.class);
    }
    
    public void testEncode() {
    	assertEquals("6686", Phonetic.encode("menschen", "de"));
    	assertEquals("vim1", Phonetic.encode("villemain", "fr"));
    	assertEquals("PPL", Phonetic.encode("people", "en"));
    }
}
    