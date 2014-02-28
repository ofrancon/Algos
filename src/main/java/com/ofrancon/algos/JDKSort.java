package com.ofrancon.algos;

import java.util.Arrays;

public class JDKSort implements SortingAlgorithm {

	private static final String NAME = "JDKSort";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void sort(int[] array) {
		if (array != null) {
			Arrays.sort(array);
		}
	}
}
