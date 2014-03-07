package com.ofrancon.algos.sorting;

/**
 * An algorithm that puts elements in an array in a certain order
 * See http://en.wikipedia.org/wiki/Sorting_algorithm
 * 
 */
public interface SortingAlgorithm {

	/**
	 * 
	 * @return a String the name of the algorithm
	 */
	public String getName();

	/**
	 * Sorts the passed int array in nondecreasing order
	 * 
	 * @param array
	 *            the array to sort
	 */
	public void sort(int[] array);

}
