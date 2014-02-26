package com.ofrancon.algos;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public abstract class TestSortAlgorithm {

	private final SortAlgorithm _algo;

	TestSortAlgorithm(SortAlgorithm algo) {
		_algo = algo;
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
	public void testOneElement() {
		int[] input = { 42 };
		testSort(input);
	}

	@Test
	public void testNull() {
		int[] input = null;
		_algo.sort(input);
		assertTrue(input == null);
	}

	@Test
	public void testNoElement() {
		int[] input = {};
		int[] output = {};
		_algo.sort(input);
		assertTrue(Arrays.equals(input, output));
	}

	@Test
	public void test10() {
		int[] input = createRandomIntArray(10);
		testSort(input, true);
	}

	@Test
	public void test100() {
		int[] input = createRandomIntArray(100);
		testSort(input, true);
	}

	@Test
	public void test1000() {
		int[] input = createRandomIntArray(1000);
		testSort(input, true);
	}

	@Test
	public void test10000() {
		int[] input = createRandomIntArray(10000);
		testSort(input, true);
	}

	@Test
	public void test100000() {
		int[] input = createRandomIntArray(100000);
		testSort(input, true);
	}

	@Test
	public void test10000AlreadySorted() {
		int[] input = createSortedIntArray(10000);
		testSort(input, true, "Time to sort " + input.length + " already sorted elements: ");
	}

	@Test
	public void test10000ReverseSorted() {
		int[] input = createReverseSortedIntArray(10000);
		testSort(input, true, "Time to sort " + input.length + " reverse sorted elements: ");
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
			before = System.currentTimeMillis();
		}
		_algo.sort(input);
		if (isLogTime) {
			after = System.currentTimeMillis();
			long time = after - before;
			System.out.println(message + time + " ms.");
		}
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

}
