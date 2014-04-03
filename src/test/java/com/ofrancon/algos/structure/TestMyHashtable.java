package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMyHashtable {

	@Test
	public void testSingleElement() {
		MyHashtable<String, String> h = new MyHashtable<String, String>(10);
		// Insert a value
		h.insert("A", "A value");
		assertEquals("Not the expected size", 1, h.size());
		// Retrieve it
		String value = h.search("A");
		assertEquals("Not the expected value", "A value", value);
		// Replace it
		h.insert("A", "A new value");
		assertEquals("Not the expected size", 1, h.size());
		value = h.search("A");
		assertEquals("Not the expected value", "A new value", value);
		// Delete it
		h.delete("A");
		assertEquals("Not the expected size", 0, h.size());
		// Search it now that it's missing
		value = h.search("A");
		assertEquals("Should not be found", null, value);
	}

	@Test
	public void testCollision() {
		MyHashtable<String, String> h = new MyHashtable<String, String>(10);
		// Insert a value
		h.insert("a", "a value");
		assertEquals("Not the expected size", 1, h.size());
		System.out.println(h);
		h.insert("C", "C value");
		assertEquals("Not the expected size", 2, h.size());
		System.out.println(h);
		// Remove one of the 2
		h.delete("a");
		assertEquals("Not the expected size", 1, h.size());
		String value = h.search("a");
		assertEquals("Should not be found", null, value);
		value = h.search("C");
		assertEquals("Not the expected value", "C value", value);
	}
}
