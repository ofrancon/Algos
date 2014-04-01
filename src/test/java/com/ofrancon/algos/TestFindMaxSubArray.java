package com.ofrancon.algos;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ofrancon.algos.FindMaxSubArray.MaxSubArray;

public class TestFindMaxSubArray {

	static final int[] array = { 100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97 };
	static final int[] counterEx = { 10, 11, 7, 10, 6 };

	@Test
	public void testFindMaxSubArrayBruteForce() {
		System.out.println("----- Brute force -----");
		System.out.println("Input=" + Arrays.toString(array));
		FindMaxSubArray fmsa = new FindMaxSubArray();
		MaxSubArray results = fmsa.findMaxSubArrayBruteForce(array);
		check(results);
		System.out.println("Input=" + Arrays.toString(counterEx));
		results = fmsa.findMaxSubArrayBruteForce(counterEx);
		checkCounterEx(results);
	}

	@Test
	public void testFindMaxSubArrayDivideAndConquer() {
		System.out.println("----- Divide and conquer -----");
		System.out.println("Input=" + Arrays.toString(array));
		FindMaxSubArray fmsa = new FindMaxSubArray();
		MaxSubArray results = fmsa.findMaxSubArrayDivideAndConquer(array);
		check(results);
		System.out.println("Input=" + Arrays.toString(counterEx));
		results = fmsa.findMaxSubArrayDivideAndConquer(counterEx);
		checkCounterEx(results);
	}

	@Test
	public void testFindMaxSubArrayLinear() {
		System.out.println("----- Linear -----");
		System.out.println("Input=" + Arrays.toString(array));
		FindMaxSubArray fmsa = new FindMaxSubArray();
		MaxSubArray results = fmsa.findMaxSubArrayLinear(array);
		check(results);
		System.out.println("Input=" + Arrays.toString(counterEx));
		results = fmsa.findMaxSubArrayLinear(counterEx);
		checkCounterEx(results);
	}

	private void check(MaxSubArray results) {
		System.out.println(results);
		assertEquals("Not the right buy index", 8, results.getStartIndex());
		assertEquals("Not the right sell index", 11, results.getEndIndex());
		assertEquals("Not the right profit", 43, results.getMaxSum());
	}

	private void checkCounterEx(MaxSubArray results) {
		System.out.println(results);
		assertEquals("Not the right buy index", 3, results.getStartIndex());
		assertEquals("Not the right sell index", 3, results.getEndIndex());
		assertEquals("Not the right profit", 3, results.getMaxSum());
	}
}
