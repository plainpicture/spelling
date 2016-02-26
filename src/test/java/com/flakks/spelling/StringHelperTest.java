
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StringHelperTest extends TestCase {
    public StringHelperTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(StringHelperTest.class);
    }
    
    public void testSliceJoin() {
    	List<String> list = new ArrayList<String>();
    	list.add("word1");
    	list.add("word2");
    	list.add("word3");
    	list.add("word4");

    	assertEquals("word2", StringHelper.sliceJoin(" ", list, 1, 2));
    	assertEquals("word2 word3", StringHelper.sliceJoin(" ", list, 1, 3));
    }
}
