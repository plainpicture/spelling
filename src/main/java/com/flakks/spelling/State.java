
package com.flakks.spelling;

import java.util.List;

public class State {
	public List<Integer> indices;
	public List<Integer> values;

	public State(List<Integer> indices, List<Integer> values) {
		this.indices = indices;
		this.values = values;
	}
}