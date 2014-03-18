package com.ofrancon.algos.structure;

import java.util.Arrays;

public class MyQueue<E> {

	private static final int DEFAULT_MAX_SIZE = 5;

	private int head;
	private int tail;
	private int currentSize;
	Object[] data;

	public MyQueue() {
		this(DEFAULT_MAX_SIZE);
	}

	public MyQueue(int maxSize) {
		data = new Object[maxSize];
		head = 0;
		tail = 0;
		currentSize = 0;
	}

	public void enqueue(E element) {
		if (!isFull()) {
			data[tail] = element;
			currentSize++;
			tail = increment(tail);
		} else {
			throw new RuntimeException("The queue is full!");
		}
	}

	@SuppressWarnings("unchecked")
	public E dequeue() {
		if (!isEmpty()) {
			E element = (E) data[head];
			data[head] = null; // So GC can occur if needed
			currentSize--;
			head = increment(head);
			return element;
		} else {
			throw new RuntimeException("The queue is empty!");
		}
	}

	private int increment(int x) {
		x++;
		if (x == data.length) {
			x = 0; // Wrap around
		}
		return x;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tail=" + tail);
		sb.append(", Head=" + head);
		sb.append(", size=" + currentSize);
		sb.append(", isFull=" + isFull());
		sb.append(", isEmpty=" + isEmpty());
		sb.append(", data=" + Arrays.toString(data));

		return sb.toString();
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean isFull() {
		return currentSize == data.length;
	}
}
