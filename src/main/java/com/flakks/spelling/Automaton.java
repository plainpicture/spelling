
package com.flakks.spelling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Automaton {
	public String string;
	public int maxEdits;
	public int minFrequency;
	
	public Automaton(String string, int maxEdits) {
		this.string = string;
		this.maxEdits = maxEdits;
		this.minFrequency = -1;
	}
	
	private State start() {
		List<Integer> indices = new ArrayList<Integer>(maxEdits);
		List<Integer> values = new ArrayList<Integer>(maxEdits);
		
		for(int i = 0; i <= maxEdits; i++) {
			indices.add(i);
			values.add(i);
		}
		
		return new State(indices, values);
	}
	
	private State step(State state, char c) {
		List<Integer> indices = state.indices;
		List<Integer> values = state.values;
		
		List<Integer> newIndices = new ArrayList<Integer>();
		List<Integer> newValues = new ArrayList<Integer>();
		
		if(indices.size() > 0 && indices.get(0) == 0 && values.get(0) < maxEdits) {
			newIndices.add(0);
			newValues.add(values.get(0) + 1);
		}
		
		for(int j = 0; j < indices.size(); j++) {
			int i = indices.get(j);
			
			if(i == string.length())
				break;
			
			int cost = string.charAt(i) == c ? 0 : 1;
			int val = values.get(j) + cost;
			
			if(newIndices.size() > 0 && newIndices.get(newIndices.size() - 1) == i)
				val = Math.min(val, newValues.get(newValues.size() - 1) + 1);
			
			if(j + 1 < indices.size() && indices.get(j + 1) == i + 1)
				val = Math.min(val, values.get(j + 1) + 1);
			
			if(val <= maxEdits) {
				newIndices.add(i + 1);
				newValues.add(val);
			}
		}
		
		return new State(newIndices, newValues);
	}
	
	private boolean isMatch(State state) {
		return state.indices.size() > 0 && state.indices.get(state.indices.size() - 1) == string.length();
	}
	
	private boolean canMatch(State state, TrieNode node) {
		return state.indices.size() > 0 &&
			((node.minLength - string.length() < maxEdits && string.length() - node.maxLength < maxEdits) ||
			(node.minLength - string.length() <= maxEdits && string.length() - node.maxLength <= maxEdits && minFrequency < node.maxFrequency));
	}

	public Correction correct(TrieNode node) {
		return correctRecursive(node, start(), null);
	}
	
	private Correction correctRecursive(TrieNode node, State state, Correction correction) {
		for(Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
			Character c = entry.getKey();
			TrieNode newNode = entry.getValue();
			
			State newState = step(state, c);
			
			if(newNode.word != null && isMatch(newState)) {
				Correction newSuggestion = new Correction(newNode.word, newState.values.get(newState.values.size() - 1), newNode.frequency);
				
				if(correction == null) {
					maxEdits = newSuggestion.distance;
					minFrequency = newSuggestion.frequency;
					
					correction = newSuggestion;
				} else if(newSuggestion.compareTo(correction) == -1) {
					maxEdits = Math.min(maxEdits, newSuggestion.distance);
					minFrequency = Math.max(minFrequency, newSuggestion.frequency);
					
					correction = newSuggestion;
				}
			}
			
			if(canMatch(newState, newNode))
				correction = correctRecursive(newNode, newState, correction);
		}

		return correction;
	}
}
