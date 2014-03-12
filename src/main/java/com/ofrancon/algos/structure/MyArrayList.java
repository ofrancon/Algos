package com.ofrancon.algos.structure;

public class MyArrayList<E> {

	private static final int INITIAL_CAPACITY = 1;

	private Object[] data;
	private int size;

	public MyArrayList() {
		data = new Object[INITIAL_CAPACITY];
		size = 0;
	}

	public void add(int index, E element) {
		checkIndex(index);
		// Grow the capacity if needed
		if (size == data.length) {
			increaseCapacity();
		}
		// Move the data. From the last element to the index
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		// Add the new element
		data[index] = element;
		size++;
	}

	private void checkIndex(int index) {
		// Check the index is valid
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index is " + index + " but size is " + size);
		}
	}

	private void increaseCapacity() {
		// Double the size
		Object[] newData = new Object[size * 2];
		// Copy the data
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		// Assign the new data
		data = newData;
	}

	public int getSize() {
		return size;
	}

	public Object[] getData() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		checkIndex(index);
		return (E) data[index];
	}

	public void remove(int index) {
		checkIndex(index);
		// Move the data till the last element
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		// The last element is null
		data[size - 1] = null;
		size--;
	}
}
