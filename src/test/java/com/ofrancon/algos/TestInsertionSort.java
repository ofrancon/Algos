package com.ofrancon.algos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class TestInsertionSort {

	@Test
	public void testExample() {
		int[] input = { 5, 2, 4, 6, 1, 3 };
		int[] output = { 1, 2, 3, 4, 5, 6 };
		System.out.println("Input: " + Arrays.toString(input));
		InsertionSort.sort(input);
		System.out.println("Output: " + Arrays.toString(input));
		assertTrue(Arrays.equals(output, input));
	}

	@Test
	public void test2IndenticalEntries() {
		int[] input = { 31, 41, 59, 26, 41, 58 };
		int[] output = { 26, 31, 41, 41, 58, 59 };
		System.out.println("Input: " + Arrays.toString(input));
		InsertionSort.sort(input);
		System.out.println("Output: " + Arrays.toString(input));
		assertTrue(Arrays.equals(output, input));
	}

	@Test
	public void testNull() {
		int[] input = null;
		InsertionSort.sort(input);
		assertTrue(input == null);
	}

	@Test
	public void testNoElement() {
		int[] input = {};
		int[] output = {};
		InsertionSort.sort(input);
		assertTrue(Arrays.equals(input, output));
	}

	@Test
	public void testOneElement() {
		int[] input = { 42 };
		int[] output = { 42 };
		InsertionSort.sort(input);
		assertTrue(Arrays.equals(input, output));
	}

	@Test
	public void test1000() {
		sort(1000);
	}

	@Test
	public void test10000() {
		sort(10000);
	}

	@Test
	public void test100000() {
		sort(100000);
	}

	private void sort(int n) {
		int[] input = new int[n];
		Random random = new Random(42l);
		for (int i = 0; i < n; i++) {
			input[i] = random.nextInt();
		}
		assertEquals("Array does not contain " + n + " elements", input.length, n);

		long before = System.currentTimeMillis();
		InsertionSort.sort(input);
		long after = System.currentTimeMillis();
		for (int i = 1; i < n; i++) {
			assertTrue(input[i] >= input[i - 1]);
		}
		long time = after - before;
		System.out.println("Time to sort " + n + " elements: " + time + " ms.");
	}

	// TODO: Test best case: the array is already sorted

	// TODO: Test worst case: the array in reverse sorted.

	@Test
	public void testSortDecreasing() {
		int n = 100;
		int[] input = new int[n];
		Random random = new Random(42l);
		for (int i = 0; i < n; i++) {
			input[i] = random.nextInt(500);
		}
		assertEquals("Array does not contain " + n + " elements", input.length, n);

		long before = System.currentTimeMillis();
		InsertionSort.sortDecreasing(input);
		long after = System.currentTimeMillis();
		for (int i = 1; i < n; i++) {
			assertTrue(input[i] <= input[i - 1]);
		}
		long time = after - before;
		System.out.println("Output: " + Arrays.toString(input));
		System.out.println("Time to sort " + n + " elements: " + time + " ms.");
	}
}
