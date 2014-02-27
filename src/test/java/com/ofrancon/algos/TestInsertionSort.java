package com.ofrancon.algos;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class TestInsertionSort extends TestSortingAlgorithm {

	public TestInsertionSort() {
		super(new InsertionSort());
	}

	@Test
	public void testSortDecreasing() {
		int n = 100;
		int[] input = new int[n];
		Random random = new Random(42l);
		for (int i = 0; i < n; i++) {
			input[i] = random.nextInt(500);
		}

		new InsertionSort().sortDecreasing(input);
		for (int i = 1; i < n; i++) {
			assertTrue(input[i] <= input[i - 1]);
		}
	}
}
