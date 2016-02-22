
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    public AppTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testCreateDictionaries() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("de\tword 1\t1");
    	lines.add("en\tword 2\t2");
    	
    	Map<String, Dictionary> dictionaries = App.createDictionaries(lines);
 
    	assertNotNull(dictionaries.get("de"));
    	assertNotNull(dictionaries.get("en"));
    	
    	assertEquals((int)dictionaries.get("de").get("word 1"), 1);
    	assertEquals((int)dictionaries.get("en").get("word 2"), 2);
    }
    
    public void testCreateTrieNodes() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tword 1\t1");
    	lines.add("de\tword 2\t2");
    	
    	Map<String, TrieNode> trieNodes = App.createTrieNodes(App.createDictionaries(lines));
    	
    	assertNotNull(trieNodes.get("de"));
    	assertNotNull(trieNodes.get("en"));
    	
    	TrieNode node1 = trieNodes.get("en").children.get('w').children.get('o').children.get('r').children.get('d').children.get(' ').children.get('1');
    	TrieNode node2 = trieNodes.get("de").children.get('w').children.get('o').children.get('r').children.get('d').children.get(' ').children.get('2');
    	
    	assertEquals(node1.frequency, 1);
    	assertEquals(node2.frequency, 2);
    }
}
