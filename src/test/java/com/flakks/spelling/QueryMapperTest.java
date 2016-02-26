
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class QueryMapperTest extends TestCase {
    public QueryMapperTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(QueryMapperTest.class);
    }
    
    public void testMap() {
    	assertEquals("test1 test2", new QueryMapper(new TestLookup()).map("first second"));
    }
    
    public void testMapWithOperators() {
    	assertEquals("test1 test2 \" test3 \" - test4 & test5", new QueryMapper(new TestLookup()).map("first second \"third fourth\" -fifth & sixth"));
    }
}