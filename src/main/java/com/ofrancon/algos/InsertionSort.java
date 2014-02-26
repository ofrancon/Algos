package com.ofrancon.algos;

/**
 * Insertion sort
 * Introduction to Algorithms, T. Cormen et al., p. 18
 * O(n2)
 */
public class InsertionSort implements SortAlgorithm {

	@Override
	public void sort(int[] array) {
		if (array == null || array.length < 2) {
			return;
		}
		// Part of the array before i is sorted
		// Part of the array after i has to be sorted
		// Start at index 1 as we can consider index 0 is an array of 1 element
		// already sorted
		for (int i = 1; i < array.length; i++) {
			int key = array[i];

			// Loop back on the first part of the array
			// and move each element greater than the key by 1 index
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			// We've found the position of the key
			array[j + 1] = key;
		}
	}

	// Exercise 2.1-2 p. 22
	public void sortDecreasing(int[] array) {
		if (array == null || array.length < 2) {
			return;
		}
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			// Decreasing: move the current element to the right
			// if it's LESS than the key
			while (j >= 0 && array[j] < key) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
	}
}
