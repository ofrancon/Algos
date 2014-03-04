package com.ofrancon.algos;


/**
 * HeapSort
 * The algorithm design manual, S. Skiena, p. 108
 * Introduction to algorithms, T. Cormen et al., p. 151
 * O(n log n)
 */
public class HeapSort implements SortingAlgorithm {

	private static final String NAME = "HeapSort";

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
		heapSort(array);
	}

	private void heapSort(int[] array) {
		// Build a max-heap
		buildMaxHeap(array);
		// System.out.println("Max-heap: " + Arrays.toString(array));
		int lastIndex = array.length - 1;
		// No need to check for index=0, an array of 1 element is already sorted
		for (int i = lastIndex; i > 0; i--) {
			// The root of the heap is the biggest element. Put it at the end
			// of the array.
			swap(array, 0, i);
			// The last element is sorted. Reduce the size of the array.
			lastIndex--;
			// The swap ruined the heap property so restore it.
			maxHeapify(array, 0, lastIndex);
		}
	}

	private void buildMaxHeap(int[] array) {
		int lastIndex = array.length - 1;
		int firstParent = (array.length - 2) / 2;
		// Loop through the parent nodes
		for (int i = firstParent; i >= 0; i--) {
			maxHeapify(array, i, lastIndex);
		}
	}

	// The parent may be smaller than its child, thus violating the max-heap
	// property. So 'float down' it's value in the max-heap.
	private void maxHeapify(int[] array, int parent, int lastIndex) {
		int leftChild = 2 * parent + 1;
		int rightChild = leftChild + 1;
		int largest;
		// Is the left element bigger than its parent?
		if (leftChild <= lastIndex && array[leftChild] > array[parent]) {
			largest = leftChild;
		} else {
			largest = parent;
		}
		// Is the right element bigger than the largest we've found so far?
		if (rightChild <= lastIndex && array[rightChild] > array[largest]) {
			largest = rightChild;
		}
		// If yes, swap the parent with the largest child. And continue down
		// the heap.
		if (largest != parent) {
			swap(array, parent, largest);
			maxHeapify(array, largest, lastIndex);
		}
	}

	void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
