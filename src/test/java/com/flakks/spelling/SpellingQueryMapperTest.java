
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SpellingQueryMapperTest extends TestCase {
    public SpellingQueryMapperTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(SpellingQueryMapperTest.class);
    }
    
    public void testMapDefault() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tfirst word\t1");
    	lines.add("en\tsecond entry\t2");
    	
    	App.dictionaries = App.createDictionaries(lines);
    	App.trieNodes = App.createTrieNodes(App.dictionaries);
    	
    	SpellingQueryMapper mapper = new SpellingQueryMapper("en");
    	
    	assertEquals("first word second entry", mapper.map("fist wrd secnd enty"));
    	assertEquals(4, mapper.sumDistance);
    }
    
    public void testMapShort() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tword 1\t1");
    	lines.add("en\tword 2\t2");
    	
    	App.dictionaries = App.createDictionaries(lines);
    	App.trieNodes = App.createTrieNodes(App.dictionaries);
    	
    	SpellingQueryMapper mapper = new SpellingQueryMapper("en");
    	
    	assertEquals("wort", mapper.map("wort"));
    }
}
