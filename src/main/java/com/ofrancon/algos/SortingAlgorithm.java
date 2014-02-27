package com.ofrancon.algos;

/**
 * An algorithm that puts elements in an array in a certain order
 * See http://en.wikipedia.org/wiki/Sorting_algorithm
 * 
 */
public interface SortingAlgorithm {

	/**
	 * Sorts the passed int array in nondecreasing order
	 * 
	 * @param array
	 *            the array to sort
	 */
	public void sort(int[] array);
}
