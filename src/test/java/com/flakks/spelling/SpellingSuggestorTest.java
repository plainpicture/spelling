
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SpellingSuggestorTest extends TestCase {
    public SpellingSuggestorTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(SpellingSuggestorTest.class);
    }
    
    public void testSuggest() {
        List<String> lines = new ArrayList<String>();
        lines.add("en\tfirst word1\t1");
        lines.add("en\tfirst word2\t2");
        lines.add("en\tfirst word3\t3");
        lines.add("en\tsecond word\t4");
        
        App.dictionaries = App.createDictionaries(lines);
        App.trieNodes = App.createTrieNodes(App.dictionaries);
        
        List<Suggestion> suggestions = new SpellingSuggestor("en").suggest("firrst wor");
        
        assertEquals(3, suggestions.size());
        assertEquals("first word3", suggestions.get(0).getToken());
        assertEquals("first word2", suggestions.get(1).getToken());
        assertEquals("first word1", suggestions.get(2).getToken());
    }
}