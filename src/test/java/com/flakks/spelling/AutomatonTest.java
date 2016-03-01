
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
    	
    	assertEquals("first", new Automaton("fist", 1).correct(root).token);
    	assertEquals(1, new Automaton("fist", 1).correct(root).distance);
    	
    	assertEquals("first", new Automaton("fitst", 1).correct(root).token);
    	assertEquals(1, new Automaton("fist", 1).correct(root).distance);
    	
    	assertEquals("second", new Automaton("secnod", 1).correct(root).token);
    	assertEquals(1, new Automaton("secnod", 1).correct(root).distance);
    	
    	assertEquals("second", new Automaton("secnd", 2).correct(root).token);
    	assertEquals(2, new Automaton("secd", 2).correct(root).distance);
    }
    
    public void testCorrectFrequency() {
    	TrieNode root = new TrieNode();
    	root.insert("word 1", 1);
    	root.insert("word 2", 2);
    	
    	assertEquals("word 2", new Automaton("word", 2).correct(root).token);
    }
    
    public void testCorrectPrefixDistance() {
    	TrieNode root = new TrieNode();
    	root.insert("first", 1);
    	root.insert("second", 1);
    	
    	assertEquals("first", new Automaton("fist", 2).correctPrefix(root).token);
    	assertEquals("second", new Automaton("secnd", 2).correctPrefix(root).token);
    }
    
    public void testCorrectPrefixFrequency() {
    	TrieNode root = new TrieNode();
    	root.insert("wonder", 1);
    	root.insert("wonderful", 2);
    	root.insert("wondering", 3);
    	
    	assertEquals("won", new Automaton("wont", 2).correctPrefix(root).token);
    	assertEquals("wonder", new Automaton("wonderx", 2).correctPrefix(root).token);
    }
}
