package com.ofrancon.algos.structure;

public class MyStack<E> {

	private static final int DEFAULT_MAX_SIZE = 5;

	int maxSize;
	int top;
	Object[] data;

	public MyStack() {
		this(DEFAULT_MAX_SIZE);
	}

	public MyStack(int maxSize) {
		this.maxSize = maxSize;
		this.data = new Object[maxSize];
		this.top = -1;
	}

	// Insert
	public void push(E element) {
		top++;
		if (top == maxSize) {
			throw new RuntimeException("Stack overflow: maximum size was " + maxSize);
		}
		data[top] = element;
	}

	// Delete
	@SuppressWarnings("unchecked")
	public E pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack underflow: stack is empty");
		}
		E result = (E) data[top];
		data[top] = null; // so result can be GCed if needed
		top--;
		return result;
	}

	@SuppressWarnings("unchecked")
	public E peek() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return (E) data[top];

	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int search(E element) {
		for (int i = 0; i <= top; i++) {
			if (data[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

}
