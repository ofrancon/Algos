package com.ofrancon.algos;

/**
 * SelectionSort
 * The algorithm design manual, S. Skiena, p. 42
 * O(n^2)
 */
public class SelectionSort implements SortingAlgorithm {

	private static final String NAME = "SelectionSort";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void sort(int[] array) {
		if (array == null || array.length < 2) {
			return;
		}

		for (int i = 0; i < array.length; i++) {
			int min = i;
			// Find the smallest element in the unsorted part of the array
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			swap(array, i, min);
		}
	}

	void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
