package com.ofrancon.algos.structure;

import java.util.Arrays;

public class MyQueue<E> {

	private static final int DEFAULT_MAX_SIZE = 5;

	private int head;
	private int tail;
	Object[] data;

	public MyQueue() {
		this(DEFAULT_MAX_SIZE);
	}

	public MyQueue(int maxSize) {
		data = new Object[maxSize + 1];
		head = 0;
		tail = 0;
	}

	public void enqueue(E element) {
		if (!isFull()) {
			data[tail] = element;
			tail++;
			if (tail == data.length) {
				tail = 0; // Wrap around
			}
		} else {
			throw new RuntimeException("The queue is full!");
		}
		toString();
	}

	@SuppressWarnings("unchecked")
	public E dequeue() {
		E element = (E) data[head];
		data[head] = null; // So GC can occur if needed
		head++;
		if (head == data.length) {
			head = 0; // Wrap around
		}
		toString();
		return element;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tail=" + tail);
		sb.append(", Head=" + head);
		sb.append(", isFull=" + isFull());
		sb.append(", isEmpty=" + isEmpty());
		sb.append(", Data=" + Arrays.toString(data));

		return sb.toString();
	}

	public boolean isEmpty() {
		return head == tail;
	}

	public boolean isFull() {
		if ((head == tail + 1) || (head == 0 && tail == data.length - 1)) {
			return true;
		} else {
			return false;
		}
	}
}
