package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TestMyArrayList {

	@Test
	public void testMyArrayList() {
		MyArrayList array = new MyArrayList();
		System.out.println("Initial=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 0, array.getSize());
		assertEquals("Initial capacity does not match", 1, array.getData().length);
		array.add(0, "0");
		System.out.println("Add zero=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 1, array.getSize());
		assertEquals("Initial capacity does not match", 1, array.getData().length);
		// Now capacity should double: 1 * 2 = 2
		array.add(1, "1");
		System.out.println("Add one=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 2, array.getSize());
		assertEquals("Initial capacity does not match", 2, array.getData().length);
		// Now capacity should double: 2 * 2 = 4
		array.add(2, "2");
		System.out.println("Add two=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 3, array.getSize());
		assertEquals("Initial capacity does not match", 4, array.getData().length);
		array.add(3, "3");
		System.out.println("Add three=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 4, array.getSize());
		assertEquals("Initial capacity does not match", 4, array.getData().length);
		// Now capacity should double: 4 * 2 = 8
		array.add(4, "4");
		System.out.println("Add four=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 5, array.getSize());
		assertEquals("Initial capacity does not match", 8, array.getData().length);
		array.add(5, "9");
		System.out.println("Add nine=" + Arrays.toString(array.getData()));
		assertEquals("Size does not match", 6, array.getSize());
		String expected = "[0, 1, 2, 3, 4, 9, null, null]";
		String actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		// Insert 8.
		array.add(5, "8");
		System.out.println("Insert eight=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 8, 9, null]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		// Insert 7.
		array.add(5, "7");
		System.out.println("Insert seven=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 7, 8, 9]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		// Insert 6. We've reach maximum capacity so it should double
		array.add(5, "6");
		System.out.println("Insert six=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 6, 7, 8, 9, null, null, null, null, null, null, null]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		// Insert 5.
		array.add(5, "5");
		System.out.println("Insert five=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, null, null, null, null, null, null]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		// Insert 5 again
		array.add(5, "5");
		System.out.println("Insert five again=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, null, null, null, null, null]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		// Delete the second five
		array.remove(6);
		System.out.println("Remove second five=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, null, null, null, null, null, null]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		array.add(10, "A");
		array.add(11, "B");
		array.add(12, "C");
		array.add(13, "D");
		array.add(14, "E");
		array.add(15, "F");
		System.out.println("Filled=" + Arrays.toString(array.getData()));
		expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
		System.out.println("Remove A=" + Arrays.toString(array.getData()));
		array.remove(10);
		expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, B, C, D, E, F, null]";
		actual = Arrays.toString(array.getData());
		assertEquals("Array does not contain the expected elements", expected, actual);
	}
}
