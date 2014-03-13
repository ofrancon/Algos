package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestMyStack {

	@Test
	public void testMyStack() {
		MyStack<String> stack = new MyStack<String>();
		assertTrue("Stack should be empty", stack.isEmpty());
		stack.push("A");
		assertTrue("Stack should not be empty", !stack.isEmpty());
		int actual = stack.search("A");
		int expected = 0;
		assertEquals("Didn't find the expected index!", expected, actual);
		actual = stack.search("C");
		expected = -1;
		assertEquals("Didn't find the expected index!", expected, actual);
		String expectedElement = "A";
		String element = stack.pop();
		assertEquals("Not the expected element", expectedElement, element);
		assertTrue("Stack should be empty", stack.isEmpty());
		stack.push("A");
		stack.push("B");
		stack.push("C");
		expectedElement = "C";
		element = stack.pop();
		assertEquals("Not the expected element", expectedElement, element);
		expectedElement = "B";
		element = stack.pop();
		assertEquals("Not the expected element", expectedElement, element);
	}

	@Test
	public void testEmptyStack() {
		MyStack<String> stack = new MyStack<String>(2);
		assertTrue("Stack should be empty", stack.isEmpty());
		try {
			stack.peek();
			fail("Peek() on an empty stack should throw an underflow exception");
		} catch (Exception e) {
			assertTrue("Exception should specify stack is empty", e.getMessage().contains("empty"));
		}
		try {
			stack.pop();
			fail("Pop() on an empty stack should throw an underflow exception");
		} catch (Exception e) {
			assertTrue("Exception should specify an underflow occurred", e.getMessage().contains("underflow"));
		}
		int actual = stack.search("C");
		int expected = -1;
		assertEquals("Didn't find the expected index!", expected, actual);
	}

	@Test
	public void testFullStack() {
		MyStack<String> stack = new MyStack<String>(2);
		stack.push("A");
		stack.push("B");
		assertTrue("Stack should not be empty", !stack.isEmpty());
		// Stack is full now
		try {
			stack.push("C");
			fail("Push() on a full stack should throw an overflow exception");
		} catch (Exception e) {
			assertTrue("Exception should specify an overflow occurred", e.getMessage().contains("overflow"));
		}
	}

	@Test
	public void testStackSearch() {
		MyStack<String> stack = new MyStack<String>();
		stack.push("A"); // 0
		stack.push("B"); // 1
		stack.push("C"); // 2
		int actual = stack.search("C");
		int expected = 2;
		assertEquals("Didn't find the expected index!", expected, actual);
		actual = stack.search("A");
		expected = 0;
		assertEquals("Didn't find the expected index!", expected, actual);
		actual = stack.search("unknown element");
		expected = -1;
		assertEquals("Didn't find the expected index!", expected, actual);
	}
}
