package com.ofrancon.algos;

/**
 * MergeSort
 * The algorithm design manual, S. Skiena, p. 120
 * Introduction to algorithms, T. Cormen et al., p. 34
 * O(n log n)
 */
public class MergeSort implements SortingAlgorithm {

	@Override
	public void sort(int[] array) {
		// Nothing to sort if the array contains less than 2 elements
		if (array == null || array.length < 2) {
			return;
		}
		mergeSort(array, 0, array.length - 1);
	}

	private void mergeSort(int[] array, int low, int high) {
		if (low < high) {
			int middle = (low + high) / 2;
			mergeSort(array, low, middle);
			mergeSort(array, middle + 1, high);
			merge(array, low, middle, high);
		}
	}

	private void merge(int[] array, int low, int middle, int high) {
		// Using arrays but could use queues
		int leftSize = middle - low + 1;
		int rightSize = high - middle;
		int[] left = new int[leftSize + 1]; // +1 is for the sentinel
		int[] right = new int[rightSize + 1]; // +1 is for the sentinel
		// Copy left side
		for (int i = 0; i < leftSize; i++) {
			left[i] = array[low + i];
		}
		// Copy the right side
		for (int i = 0; i < rightSize; i++) {
			right[i] = array[middle + 1 + i];
		}
		// Add the sentinels to the last element of the array
		left[leftSize] = Integer.MAX_VALUE;
		right[rightSize] = Integer.MAX_VALUE;
		// Merge
		int leftCounter = 0;
		int rightCounter = 0;
		for (int i = low; i <= high; i++) {
			if (left[leftCounter] <= right[rightCounter]) {
				array[i] = left[leftCounter];
				leftCounter++;
			} else {
				array[i] = right[rightCounter];
				rightCounter++;
			}
		}
	}
}
