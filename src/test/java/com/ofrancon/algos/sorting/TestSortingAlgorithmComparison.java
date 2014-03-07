package com.ofrancon.algos.sorting;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import org.junit.Test;

import com.ofrancon.algos.sorting.HeapSort;
import com.ofrancon.algos.sorting.JDKSort;
import com.ofrancon.algos.sorting.MergeSort;
import com.ofrancon.algos.sorting.QuickSort;
import com.ofrancon.algos.sorting.RandomizedQuickSort;
import com.ofrancon.algos.sorting.SortingAlgorithm;

public class TestSortingAlgorithmComparison {

	@Test
	public void compare() {
		int size = 1000000;
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		String sizeS = nf.format(size);
		System.out.println("Sorting an array of " + sizeS + " random int:");

		testAlgo(size, new JDKSort());
		testAlgo(size, new MergeSort());
		testAlgo(size, new QuickSort());
		testAlgo(size, new RandomizedQuickSort());
		testAlgo(size, new HeapSort());
	}

	private void testAlgo(int size, SortingAlgorithm algo) {
		int[] array = createRandomIntArray(size);
		long before = System.nanoTime();
		algo.sort(array);
		long after = System.nanoTime();
		double time = (after - before) / 1000000d;
		System.out.println(algo.getName() + ": " + time + " ms.");
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
}
