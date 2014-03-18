package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMyLinkedList {

	@Test
	public void testMyLinkedList() {
		MyLinkedList<String> list = new MyLinkedList<String>();
		int actualSize = list.getSize();
		System.out.println("List is: " + list);
		int expectedSize = 0;
		assertEquals("Not the expected size", expectedSize, actualSize);
		assertTrue("List should be empty", list.isEmpty());
		list.addFirst("C");
		System.out.println("List is: " + list);
		actualSize = list.getSize();
		expectedSize = 1;
		assertEquals("Not the expected size", expectedSize, actualSize);
		assertTrue("Lost should not be empty", !list.isEmpty());
		list.addFirst("B");
		System.out.println("List is: " + list);
		list.addFirst("A");
		System.out.println("List is: " + list);
		actualSize = list.getSize();
		expectedSize = 3;
		assertEquals("Not the expected size", expectedSize, actualSize);
		String actual = list.removeFirst();
		System.out.println("List is: " + list);
		String expected = "A";
		assertEquals("Not the expected first element", expected, actual);
		actual = list.removeFirst();
		actual = list.removeFirst();
		expected = "C";
		assertEquals("Not the expected first element", expected, actual);
		assertTrue("List should be empty", list.isEmpty());
	}

	@Test
	public void testContains() {
		System.out.println("----- Test contains() -----");
		MyLinkedList<String> list = new MyLinkedList<String>();
		boolean actual = list.contains("B");
		assertFalse("List should not contain the element", actual);
		list.addLast("A");
		actual = list.contains("B");
		assertFalse("List should not contain the element", actual);
		list.addLast("B");
		actual = list.contains("B");
		assertTrue("List should contain the element", actual);
		list.removeFirst();
		actual = list.contains("B");
		assertTrue("List should contain the element", actual);
		list.removeLast();
		System.out.println("List is: " + list);
		actual = list.contains("B");
		assertFalse("List should not contain the element", actual);
		System.out.println("----- - -----");
	}

}
