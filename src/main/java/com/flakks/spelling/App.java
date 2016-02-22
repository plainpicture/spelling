
package com.flakks.spelling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
	public static Map<String, Dictionary> dictionaries;
	public static Map<String, TrieNode> trieNodes;
	
	public static Map<String, Dictionary> createDictionaries(List<String> lines) {
		Map<String, Dictionary> dictionaries = new HashMap<String, Dictionary>();

		for(String line : lines) {
			String[] columns = line.split("\t");
			
			Dictionary dictionary = dictionaries.get(columns[0]);
			
			if(dictionary == null) {
				dictionary = new Dictionary();
				dictionaries.put(columns[0], dictionary);
			}
			
			dictionary.put(columns[1].trim().toLowerCase(), Integer.parseInt(columns[2]));
		}
		
		return dictionaries;
	}
	
	public static Map<String, TrieNode> createTrieNodes(Map<String, Dictionary> dictionaries) {
		Map<String, TrieNode> rootNodes = new HashMap<String, TrieNode>();
		
		for(Map.Entry<String, Dictionary> entry : dictionaries.entrySet()) {
			TrieNode rootNode = rootNodes.get(entry.getKey());
			
			if(rootNode == null) {
				rootNode = new TrieNode();
				rootNodes.put(entry.getKey(), rootNode);
			}
			
			for(Map.Entry<String, Integer> dictionaryEntry : entry.getValue().entrySet()) {
				rootNode.insert(dictionaryEntry.getKey(), dictionaryEntry.getValue());
			}
		}
		
		return rootNodes;
	}
	
	public static void main(String[] args) throws IOException {
		dictionaries = createDictionaries(Files.readAllLines(Paths.get(args[0])));
		trieNodes = createTrieNodes(dictionaries);
		
		new Server().start();
	}
}