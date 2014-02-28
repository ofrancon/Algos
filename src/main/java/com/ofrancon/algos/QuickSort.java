package com.ofrancon.algos;

/**
 * QuickSort
 * The algorithm design manual, S. Skiena, p. 124
 * Introduction to algorithms, T. Cormen et al., p. 171
 * Expected running time O(n log n), worst case O(n2)
 */
public class QuickSort implements SortingAlgorithm {

	private static final String NAME = "QuickSort";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void sort(int[] array) {
		// Nothing to sort if the array contains less than 2 elements
		if (array == null || array.length < 2) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pivot = partition(array, low, high);
			quickSort(array, low, pivot - 1);
			quickSort(array, pivot + 1, high);
		}
	}

	// Rearranges the subarray in place.
	// First partition contains values no greater than the pivot
	// Second partition contains values greater than the pivot
	private int partition(int[] array, int low, int high) {
		// Arbitrarily take the last element of the array as the pivot
		int pivot = array[high];
		int firstHigh = low;
		// Scan the array up to the pivot
		for (int i = low; i < high; i++) {
			// If the element is less that the pivot, swap it to the first high
			// and move first high next
			if (array[i] < pivot) {
				swap(array, firstHigh, i);
				firstHigh++;
			}
		}
		// Move the pivot to its place, as well as the highest element
		swap(array, firstHigh, high);
		return firstHigh;
	}

	private void swap(int[] array, int i, int j) {
		if (i != j) { // If i = j no need to swap
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}
}
