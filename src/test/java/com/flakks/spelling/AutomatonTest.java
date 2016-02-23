
package com.flakks.spelling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AutomatonTest extends TestCase {
    public AutomatonTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(AutomatonTest.class);
    }
    
    public void testAutomaton() {
    	Automaton automaton = new Automaton("query", 2);
    	
    	assertEquals("query", automaton.string);
    	assertEquals(2, automaton.maxEdits);
    	assertEquals(-1, automaton.minFrequency);
    }
    
    public void testCorrectDistance() {
    	TrieNode root = new TrieNode();
    	root.insert("first", 1);
    	root.insert("second", 1);
    	
    	assertEquals("first", new Automaton("fist", 2).correct(root).token);
    	assertEquals("second", new Automaton("secnd", 2).correct(root).token);
    }
    
    public void testCorrectFrequency() {
    	TrieNode root = new TrieNode();
    	root.insert("word 1", 1);
    	root.insert("word 2", 2);
    	
    	assertEquals("word 2", new Automaton("word", 2).correct(root).token);
    }
}