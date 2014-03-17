package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMyQueue {

	@Test
	public void testQueue() {
		MyQueue<String> queue = new MyQueue<String>(3);
		assertTrue("Queue should be empty", queue.isEmpty());
		queue.enqueue("A");
		System.out.println(queue);
		queue.enqueue("B");
		System.out.println(queue);
		queue.enqueue("C");
		System.out.println(queue);
		assertTrue("Queue should be full", queue.isFull());
		String expected = "A";
		String actual = queue.dequeue();
		System.out.println(queue);
		assertEquals("Not the expected element", expected, actual);
		assertTrue("Queue should NOT be full", !queue.isFull());
		queue.enqueue("D");
		System.out.println(queue);
		assertTrue("Queue should be full", queue.isFull());
		expected = "B";
		actual = queue.dequeue();
		assertEquals("Not the expected element", expected, actual);
		expected = "C";
		actual = queue.dequeue();
		System.out.println(queue);
		assertEquals("Not the expected element", expected, actual);
		expected = "D";
		actual = queue.dequeue();
		System.out.println(queue);
		assertEquals("Not the expected element", expected, actual);
		assertTrue("Queue should be empty", queue.isEmpty());
	}

}
