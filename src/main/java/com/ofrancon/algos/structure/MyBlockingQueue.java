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
	 * @throws InterruptedException
	 */
	public synchronized void enqueue(E element) throws QueueShutdownException, InterruptedException {
		if (!isShutdown()) {
			while (isFull()) {
				wait();
			}
			data[tail] = element;
			currentSize++;
			tail = increment(tail);
			notifyAll();
		} else {
			throw new QueueShutdownException("Cannot enqueue new elements: the queue has been shutdown");
		}
	}

	/**
	 * Removes the head element of the queue. Waits for an element to be
	 * available if the queue is empty and not shutdown.
	 * 
	 * @return the oldest element in the queue
	 * @throws QueueShutdownException if the queue is empty and has been
	 *             shutdown
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public synchronized E dequeue() throws QueueShutdownException, InterruptedException {
		while (isEmpty()) {
			if (isShutdown()) {
				throw new QueueShutdownException("Cannot dequeue anymore: the queue is empty and has been shutdown");
			} else {
				wait();
			}
		}
		E element = (E) data[head];
		data[head] = null; // So GC can occur if needed
		currentSize--;
		head = increment(head);
		notifyAll();
		return element;
	}

	/**
	 * Shuts down the queue.
	 */
	public synchronized void shutdown() {
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
	 * 
	 * @return a string representation of this queue
	 */
	@Override
	public synchronized String toString() {
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
	public synchronized boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Returns true if this queue is full.
	 * 
	 * @return true if this queue is full
	 */
	public synchronized boolean isFull() {
		return currentSize == data.length;
	}

	/**
	 * Returns true if this queue has been shutdown.
	 * 
	 * @return true if this queue has been shutdown
	 */
	public synchronized boolean isShutdown() {
		return isShutdown;
	}
}
