package com.ofrancon.algos.sorting;

import java.util.Random;

/**
 * QuickSort
 * The algorithm design manual, S. Skiena, p. 124
 * Introduction to algorithms, T. Cormen et al., p. 171
 * Expected running time O(n log n), worst case O(n2)
 */
public class RandomizedQuickSort extends QuickSort {

	private static final String NAME = "RandomizedQuickSort";

	private final Random _random;

	public RandomizedQuickSort() {
		_random = new Random(42l);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	/**
	 * Redefine the partition method to select a random pivot
	 */
	int partition(int[] array, int low, int high) {
		// Generate a random number between 0 and the number of elements
		int i = low + _random.nextInt(high - low);
		swap(array, i, high);
		return super.partition(array, low, high);
	}
}
