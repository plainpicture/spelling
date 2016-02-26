
package com.flakks.spelling;

import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TrieNodeTest extends TestCase {
    public TrieNodeTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(TrieNodeTest.class);
    }
    
    public void testTrieNode() {		
    	TrieNode trieNode = new TrieNode();
    	
    	assertEquals(null, trieNode.word);
    	assertEquals(-1, trieNode.frequency);
    	assertEquals(0, trieNode.sumFrequency);
    	assertEquals(new HashMap<Character, TrieNode>(), trieNode.children);
    }
    
    public void testInsert() {
    	TrieNode root = new TrieNode();
    	root.insert("first", 1);
    	root.insert("second", 2);
    	
    	assertEquals(null, root.word);
    	assertEquals(-1, root.frequency);
    	assertEquals(3, root.sumFrequency);
    	
    	TrieNode f = root.children.get('f');
    	
    	assertEquals(null, f.word);
    	assertEquals(-1, f.frequency);
    	assertEquals(1, f.sumFrequency);
    	
    	TrieNode first = root.children.get('f').children.get('i').children.get('r').children.get('s').children.get('t');
    	
    	assertEquals("first", first.word);
    	assertEquals(1, first.frequency);
    	assertEquals(1, first.sumFrequency);
    }
}
