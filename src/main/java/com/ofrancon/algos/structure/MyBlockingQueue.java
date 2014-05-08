package com.ofrancon.algos.structure;

import java.util.Arrays;

public class MyBlockingQueue<E> {

	/** The default size for a queue */
	private static final int DEFAULT_MAX_SIZE = 5;

	/** The queued elements */
	private final Object[] data;

	/** The head of the queue */
	private int head;
	/** The tail of the queue */
	private int tail;
	/** The size of the queue */
	private int currentSize;
	/** The state of the queue */
	private boolean isShutdown;

	/**
	 * Creates a queue with the default size
	 */
	public MyBlockingQueue() {
		this(DEFAULT_MAX_SIZE);
	}

	/**
	 * Creates a queue with the passed maximum size
	 * 
	 * @param maxSize the maximum size of the queue
	 * @throws IllegalArgumentException if {@code maxSize < 1}
	 */
	public MyBlockingQueue(int maxSize) {
		if (maxSize <= 0)
			throw new IllegalArgumentException();
		data = new Object[maxSize];
		head = 0;
		tail = 0;
		currentSize = 0;
		isShutdown = false;
	}

	/**
	 * Adds the passed element to the tail of the queue. If the queue is full,
	 * waits for a space to become available.
	 * 
	 * @param element the element to enqueue
	 * @throws QueueShutdownException if the queue has been shutdown
	 */
	public void enqueue(E element) throws QueueShutdownException {
		if (!isShutdown()) {
			if (!isFull()) {
				data[tail] = element;
				currentSize++;
				tail = increment(tail);
			} else {
				// TODO wait
			}
		} else {
			throw new QueueShutdownException("Cannot enqueue new elements: the queue has been shutdown");
		}
	}

	/**
	 * Removes the head element of the queue. Waits for an element to be
	 * available if the queue is empty.
	 * 
	 * @return the oldest element in the queue
	 * @throws QueueShutdownException if the queue is empty and has been
	 *             shutdown
	 */
	@SuppressWarnings("unchecked")
	public E dequeue() throws QueueShutdownException {
		if (!isEmpty()) {
			E element = (E) data[head];
			data[head] = null; // So GC can occur if needed
			currentSize--;
			head = increment(head);
			return element;
		} else {
			if (isShutdown()) {
				throw new QueueShutdownException("Cannot dequeue anymore: the queue has been shutdown");
			} else {
				// TODO wait
				return null;
			}
		}
	}

	/**
	 * Shuts down the queue.
	 */
	public void shutdown() {
		isShutdown = true;
	}

	private int increment(int x) {
		x++;
		if (x == data.length) {
			x = 0; // Wrap around
		}
		return x;
	}

	/**
	 * Returns a String representation of this queue.
	 * For debug purposes only.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tail=" + tail);
		sb.append(", Head=" + head);
		sb.append(", size=" + currentSize);
		sb.append(", isFull=" + isFull());
		sb.append(", isEmpty=" + isEmpty());
		sb.append(", isShutdown=" + isShutdown());
		sb.append(", data=" + Arrays.toString(data));

		return sb.toString();
	}

	/**
	 * Returns true if this queue is empty.
	 * 
	 * @return true if this queue is empty
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Returns true if this queue is full.
	 * 
	 * @return true if this queue is full
	 */
	public boolean isFull() {
		return currentSize == data.length;
	}

	/**
	 * Returns true if this queue has been shutdown.
	 * 
	 * @return true if this queue has been shutdown
	 */
	public boolean isShutdown() {
		return isShutdown;
	}
}
