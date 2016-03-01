
package com.flakks.spelling;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.HashMap;

public class TrieNode {
	public String word;
	public Character c;
	public String prefix;
	public int frequency;
	public int sumFrequency;
	public int maxFrequency;
	public int minLength;
	public int maxLength;
	public Map<Character, TrieNode> children;
	public PriorityQueue<Suggestion> suggestions;
	
	public TrieNode() {
		word = null;
		c = null;
		prefix = null;
		frequency = -1;
		sumFrequency = 0;
		maxFrequency = 0;
		minLength = -1;
		maxLength = -1;
		children = new HashMap<Character, TrieNode>();
		suggestions = new PriorityQueue<Suggestion>(11, Collections.reverseOrder());
	}
	
	public void insert(String word, int frequency) {
		TrieNode node = this;
		Suggestion suggestion = new Suggestion(word, 0, frequency);
		StringBuffer prefix = new StringBuffer();
		
		for(int i = 0; i <= word.length(); i++) {
			if(node.minLength == -1)
				node.minLength = word.length();
			else
				node.minLength = Math.min(node.minLength, word.length());

			node.maxLength = Math.max(node.maxLength,  word.length());
			node.maxFrequency = Math.max(node.maxFrequency, frequency);
			
			node.suggestions.add(suggestion);
			
			if(node.suggestions.size() > 10)
				node.suggestions.poll();
			
			node.sumFrequency += frequency;
			node.prefix = prefix.toString();

			if(i < word.length()) {
				prefix.append(word.charAt(i));
				
				char c = word.charAt(i);
				TrieNode newNode = node.children.get(c);
				
				if(newNode == null) {
					newNode = new TrieNode();
					newNode.c = c;
					
					node.children.put(c, newNode);
				}
				
				node = newNode;
			}
		}
		
		node.word = word;
		node.frequency = frequency;
	}
}