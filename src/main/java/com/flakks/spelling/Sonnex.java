
package com.flakks.spelling;

import java.util.HashMap;
import java.util.Map;

class SonnexTrieNode {
	public Map<Character, SonnexTrieNode> children;
	public String output;
	
	public SonnexTrieNode() {
		children = new HashMap<Character, SonnexTrieNode>();
	}
	
	public void insert(String input, String output) {
		SonnexTrieNode node = this;
		
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			SonnexTrieNode newNode = node.children.get(c);
				
			if(newNode == null) {
				newNode = new SonnexTrieNode();
					
				node.children.put(c, newNode);
			}
				
			node = newNode;
		}
		
		node.output = output;
	}
}

public class Sonnex {
	private SonnexTrieNode trie;
	
	public Sonnex() {
		trie = new SonnexTrieNode();
		
		trie.insert("un", "1");
		trie.insert("ein", "1");
		trie.insert("in", "1");
		trie.insert("ain", "1");
		trie.insert("en", "2");
		trie.insert("an", "2");
		trie.insert("on", "3");
		trie.insert("a", "a");
		trie.insert("à", "a");
		trie.insert("â", "a");
		trie.insert("b", "b");
		trie.insert("bb", "b");
		trie.insert("ch", "C");
		trie.insert("ch", "C");
		trie.insert("d", "d");
		trie.insert("dd", "d");
		trie.insert("e", "e");
		trie.insert("eu", "e");
		trie.insert("ê", "E");
		trie.insert("é", "E");
		trie.insert("è", "E");
		trie.insert("ai", "E");
		trie.insert("ei", "E");
		trie.insert("f", "f");
		trie.insert("ff", "f");
		trie.insert("ph", "f");
		trie.insert("gu", "g");
		trie.insert("î", "i");
		trie.insert("i", "i");
		trie.insert("ille", "i");
		trie.insert("j", "j");
		trie.insert("ge", "j");
		trie.insert("k", "k");
		trie.insert("c", "k");
		trie.insert("qu", "k");
		trie.insert("ck", "k");
		trie.insert("l", "l");
		trie.insert("ll", "l");
		trie.insert("ll", "l");
		trie.insert("m", "m");
		trie.insert("mm", "m");
		trie.insert("n", "n");
		trie.insert("nn", "n");
		trie.insert("o", "o");
		trie.insert("ô", "o");
		trie.insert("p", "p");
		trie.insert("pp", "p");
		trie.insert("r", "r");
		trie.insert("rr", "r");
		trie.insert("s", "s");
		trie.insert("ss", "s");
		trie.insert("t", "t");
		trie.insert("tt", "t");
		trie.insert("u", "u");
		trie.insert("ù", "u");
		trie.insert("û", "u");
		trie.insert("v", "v");
		trie.insert("w", "v");
		trie.insert("z", "z");
		trie.insert("s", "z");
		trie.insert("ou", "U");
	}
	
	public String encode(String input) {
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0; i < input.length(); i++) {
			SonnexTrieNode node = trie.children.get(input.charAt(i));
			String output = null;
			
			if(node != null) {
				output = node.output;
				int offset = i;
				
				for(int v = i + 1; v < input.length(); v++) {
					SonnexTrieNode newNode = node.children.get(input.charAt(v));
					
					if(newNode == null)
						break;
					
					if(newNode.output != null) {
						output = newNode.output;
						offset = v;
					}
						
					node = newNode;
				}
				
				if(output != null) {
					buffer.append(output);
					i = offset;
				}
			}
		}
		
		return buffer.toString();
	}
}
