
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;

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
    
    public void testProcess() {
    	List<String> lines = new ArrayList<String>();
    	lines.add("en\tfirst word\t1");
    	lines.add("en\tsecond entry\t2");
    	
    	App.dictionaries = App.createDictionaries(lines);
    	App.trieNodes = App.createTrieNodes(App.dictionaries);
    	
    	RequestHandler requestHandler = new RequestHandler("{'query':'secnd enty','locale':'en'}");
    	
    	JSONObject jsonObject = new JSONObject(requestHandler.process());
    	
    	assertEquals(2, jsonObject.getInt("distance"));
    	assertEquals("second entry", jsonObject.getString("query"));
    	assertNotNull(jsonObject.getInt("took"));
    }
}
