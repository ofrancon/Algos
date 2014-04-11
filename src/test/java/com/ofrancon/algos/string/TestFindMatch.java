package com.ofrancon.algos.string;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ofrancon.algos.string.FindMatch;

public class TestFindMatch {

	private final FindMatch findMatch = new FindMatch();

	// Test null strings
	@Test
	public void testNull() {
		int index = findMatch.findMatch((String) null, (String) null);
		assertTrue(index == -1);
		index = findMatch.findMatch((char[]) null, (char[]) null);
		assertTrue(index == -1);
	}

	@Test
	public void testNullString() {
		String subString = "substring";
		int index = findMatch.findMatch(null, subString);
		assertTrue(index == -1);
		index = findMatch.findMatch(null, subString.toCharArray());
		assertTrue(index == -1);
	}

	@Test
	public void testNullSubString() {
		String string = "a string";
		int index = findMatch.findMatch(string, null);
		assertTrue(index == -1);
		index = findMatch.findMatch(string.toCharArray(), null);
		assertTrue(index == -1);
	}

	// Test empty strings
	@Test
	public void testEmpty() {
		int index = findMatch.findMatch("", "");
		assertTrue(index == -1);
		index = findMatch.findMatch(new char[0], new char[0]);
		assertTrue(index == -1);
	}

	@Test
	public void testEmptyString() {
		String s = "";
		String ss = "a substring";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == -1);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == -1);
	}

	@Test
	public void testEmptySubString() {
		String s = " a string";
		String ss = "";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == -1);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == -1);
	}

	// Other tests
	@Test
	public void testTooLongSubstring() {
		String s = "a string";
		String ss = "a substring";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == -1);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == -1);
	}

	@Test
	public void testFound() {
		String s = "a string that contains a substring somewhere in it";
		String ss = "a substring";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == 23);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == 23);
	}

	@Test
	public void testFoundFirst() {
		String s = "a substring contained in a string";
		String ss = "a substring";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == 0);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == 0);
	}

	@Test
	public void testFoundLast() {
		String s = "a string that contains a substring";
		String ss = "a substring";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == 23);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == 23);
	}

	@Test
	public void testNotFound() {
		String s = "a string that doesn't contain a subSSS";
		String ss = "a substring";
		int index = findMatch.findMatch(s, ss);
		assertTrue(index == -1);
		index = findMatch.findMatch(s.toCharArray(), ss.toCharArray());
		assertTrue(index == -1);
	}
}
