package com.ofrancon.algos;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class TestMergeSort {

	// Cormen - Exercise 2.3-1
	@Test
	public void testExample() {
		int[] array = { 3, 41, 52, 26, 38, 57, 9, 49 };
		System.out.println("Input: " + Arrays.toString(array));
		MergeSort.sort(array);
		System.out.println("Output: " + Arrays.toString(array));
		for (int i = 1; i < array.length; i++) {
			assertTrue(array[i] >= array[i - 1]);
		}
	}

	// TODO: test array with odd number of elements
}
