
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RequestHandlerTest extends TestCase {
    public RequestHandlerTest(String testName) {
        super(testName);
    }
  
    public static Test suite() {
        return new TestSuite(RequestHandlerTest.class);
    }
    
    public void testProcessCorrect() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tfirst word\t1");
    	lines.add("en\tsecond entry\t2");
    	
    	App.dictionaries = App.createDictionaries(lines);
    	App.trieNodes = App.createTrieNodes(App.dictionaries);
    	
    	RequestHandler requestHandler = new RequestHandler("{'operation':'correct','query':'secnd enty','locale':'en'}");

    	JSONObject jsonObject = new JSONObject(requestHandler.process());
    	
    	assertEquals(2, jsonObject.getInt("distance"));
    	assertEquals("second entry", jsonObject.getString("query"));
    	assertNotNull(jsonObject.getInt("took"));
    }
    
    public void testProcessSuggest() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tfirst\t1");
    	lines.add("en\tsecond\t2");
    	lines.add("en\tthird\t3");
    	lines.add("en\tentries\t4");
    	lines.add("en\tentry\t5");
    	lines.add("en\tmultiple words\t6");
    	lines.add("en\tmultiple\t7");
    	
    	App.dictionaries = App.createDictionaries(lines);
    	App.trieNodes = App.createTrieNodes(App.dictionaries);
    	
    	RequestHandler requestHandler = new RequestHandler("{'operation':'suggest','query':'first ent','locale':'en'}");
    	
    	JSONObject jsonObject = new JSONObject(requestHandler.process());
    	
    	List<String> expected = new ArrayList<String>();
    	expected.add("first entry");
    	expected.add("first entries");
    	
    	List<String> actual = new ArrayList<String>();
    	
    	JSONArray suggestions = jsonObject.getJSONArray("suggestions");
    	
    	for(int i = 0; i < suggestions.length(); i++)
    		actual.add(((JSONObject)suggestions.get(i)).getString("query"));
    	
    	assertEquals(expected, actual);
    	assertNotNull(jsonObject.getInt("took"));
    }
}
