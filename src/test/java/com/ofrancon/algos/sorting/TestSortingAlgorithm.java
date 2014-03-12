package com.ofrancon.algos.sorting;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public abstract class TestSortingAlgorithm {

	private final SortingAlgorithm algo;

	TestSortingAlgorithm(SortingAlgorithm algo) {
		this.algo = algo;
	}

	@Test
	public void testEvenNumberOfElements() {
		int[] input = { 5, 2, 4, 6, 1, 3 };
		testSort(input);
	}

	@Test
	public void testOddNumberOfElements() {
		int[] input = { 5, 2, 4, 6, 1, 3, 7 };
		testSort(input);
	}

	@Test
	public void test2IndenticalElements() {
		int[] input = { 31, 41, 59, 26, 41, 58 };
		testSort(input);
	}

	@Test
	public void testIndenticalElements() {
		int[] input = { 42, 42, 42, 42, 42, 42 };
		testSort(input);
	}

	@Test
	public void testOneElement() {
		int[] input = { 42 };
		testSort(input);
	}

	@Test
	public void testNull() {
		int[] input = null;
		algo.sort(input);
		assertTrue(input == null);
	}

	@Test
	public void testNoElement() {
		int[] input = {};
		int[] output = {};
		algo.sort(input);
		assertTrue(Arrays.equals(input, output));
	}

	@Test
	public void test10K() {
		int[] input = createRandomIntArray(10000);
		testSort(input, true, "Time to sort 10K random elements: ");
	}

	@Test
	public void test100K() {
		int[] input = createRandomIntArray(100000);
		testSort(input, true, "Time to sort 100K random elements: ");
	}

	@Test
	public void test1M() {
		int[] input = createRandomIntArray(1000000);
		testSort(input, true, "Time to sort 1M random elements: ");
	}

	@Test
	public void test10KAlreadySorted() {
		int[] input = createSortedIntArray(10000);
		testSort(input, true, "Time to sort 10K already sorted elements: ");
	}

	@Test
	public void test10KReverseSorted() {
		int[] input = createReverseSortedIntArray(10000);
		testSort(input, true, "Time to sort 10K reverse sorted elements: ");
	}

	@Test
	public void test10KIdentical() {
		int[] input = createIdenticalIntArray(10000);
		testSort(input, true, "Time to sort 10K identical elements: ");
	}

	private void testSort(int[] input) {
		testSort(input, false);
	}

	private void testSort(int[] input, boolean isLogTime) {
		testSort(input, isLogTime, "Time to sort " + input.length + " elements: ");
	}

	private void testSort(int[] input, boolean isLogTime, String message) {
		// System.out.println("Input: " + Arrays.toString(input));
		long before = 0;
		long after = 0;
		if (isLogTime) {
			before = System.nanoTime();
		}
		algo.sort(input);
		if (isLogTime) {
			after = System.nanoTime();
			double time = (after - before) / 1000000d;
			System.out.println(message + time + " ms.");
		}
		// System.out.println("Output: " + Arrays.toString(input));
		check(input);
	}

	private void check(int[] array) {
		for (int i = 1; i < array.length; i++) {
			assertTrue(array[i] >= array[i - 1]);
		}
	}

	private int[] createRandomIntArray(int size) {
		// Create an int array of size n
		int[] array = new int[size];
		// Initialize it with random numbers
		Random random = new Random(42l);
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt();
		}
		return array;
	}

	private int[] createSortedIntArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i + 1;
		}
		return array;
	}

	private int[] createReverseSortedIntArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = size - i;
		}
		return array;
	}

	private int[] createIdenticalIntArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = 42;
		}
		return array;
	}

}
