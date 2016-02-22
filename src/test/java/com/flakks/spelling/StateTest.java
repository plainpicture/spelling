
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StateTest extends TestCase {
    public StateTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(StateTest.class);
    }
    
    public void testState() {
    	List<Integer> indices = new ArrayList<Integer>();
    	indices.add(1);
    	indices.add(2);
    	
    	List<Integer> values = new ArrayList<Integer>();
    	values.add(1);
    	values.add(2);
    	
    	State state = new State(indices, values);
    	
    	assertEquals(indices, state.indices);
    	assertEquals(values, state.values);
    }
}
