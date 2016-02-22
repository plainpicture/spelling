
package com.flakks.spelling;

import java.util.Map;
import java.util.HashMap;

public class TrieNode {
	public String word;
	public int frequency;
	public Map<Character, TrieNode> children;
	public int minLength;
	public int maxLength;
	public int maxFrequency;
	
	public TrieNode() {
		word = null;
		frequency = -1;
		maxFrequency = -1;
		minLength = -1;
		maxLength = -1;
		children = new HashMap<Character, TrieNode>();
	}
	
	public void insert(String word, int frequency) {
		TrieNode node = this;
		
		for(int i = 0; i <= word.length(); i++) {
			if(node.minLength == -1)
				node.minLength = word.length();
			else
				node.minLength = Math.min(node.minLength, word.length());

			if(node.maxLength == -1)
				node.maxLength = word.length();
			else
				node.maxLength = Math.max(node.maxLength,  word.length());
			
			node.maxFrequency = Math.max(node.maxFrequency, frequency);

			if(i < word.length()) {
				char c = word.charAt(i);
				TrieNode newNode = node.children.get(c);
				
				if(newNode == null) {
					newNode = new TrieNode();
					
					node.children.put(c, newNode);
				}
				
				node = newNode;
			}
		}
		
		node.word = word;
		node.frequency = frequency;
	}
}