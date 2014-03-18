package com.ofrancon.algos.structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestMyQueue {

	@Test
	public void testQueue() {
		MyQueue<String> queue = new MyQueue<String>(3);
		assertTrue("Queue should be empty", queue.isEmpty());
		queue.enqueue("A");
		System.out.println("Enqueue A: " + queue);
		queue.enqueue("B");
		System.out.println("Enqueue B: " + queue);
		queue.enqueue("C");
		System.out.println("Enqueue C: " + queue);
		assertTrue("Queue should be full", queue.isFull());
		String expected = "A";
		String actual = queue.dequeue();
		System.out.println("Dequeue A: " + queue);
		assertEquals("Not the expected element", expected, actual);
		assertTrue("Queue should NOT be full", !queue.isFull());
		queue.enqueue("D");
		System.out.println("Enqueue D: " + queue);
		assertTrue("Queue should be full", queue.isFull());
		expected = "B";
		actual = queue.dequeue();
		System.out.println("Dequeue B: " + queue);
		assertEquals("Not the expected element", expected, actual);
		expected = "C";
		actual = queue.dequeue();
		System.out.println("Dequeue C: " + queue);
		assertEquals("Not the expected element", expected, actual);
		expected = "D";
		actual = queue.dequeue();
		System.out.println("Dequeue D: " + queue);
		assertEquals("Not the expected element", expected, actual);
		assertTrue("Queue should be empty", queue.isEmpty());
	}

	@Test
	public void testEmptyQueue() {
		System.out.println("----- Test empty queue -----");
		MyQueue<String> queue = new MyQueue<String>(3);
		assertTrue("Queue should be empty", queue.isEmpty());
		System.out.println(queue);
		try {
			String actual = queue.dequeue();
			fail("A RuntimeException should have been thrown");
		} catch (Exception e) {
			assertTrue("The exception should complain bout the queue being empty", e.getMessage().contains("is empty"));
		}
		System.out.println(queue);
		queue.enqueue("A");
		System.out.println(queue);
		System.out.println("----- - -----");
	}
}
