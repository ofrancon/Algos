package com.ofrancon.algos.structure;

public class MyLinkedList<E> {

	private Node<E> first;
	private Node<E> last;
	private int size;

	public MyLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	public void addFirst(E element) {
		Node<E> newNode = new Node<E>(null, element, first);
		if (getSize() == 0) {
			// This is the first node to be inserted
			first = newNode;
			last = newNode;
		} else {
			// Else update it
			first.previous = newNode;
			first = newNode;
		}
		size++;
	}

	public void addLast(E element) {
		Node<E> newNode = new Node<E>(last, element, null);
		if (getSize() == 0) {
			// This is the first node to be inserted
			first = newNode;
			last = newNode;
		} else {
			// Else update it
			last.next = newNode;
			last = newNode;
		}
		size++;
	}

	public E removeFirst() {
		E e = first.element;
		// If there's only 1 element left in the list, clear it
		if (getSize() == 1) {
			first = null;
			last = null;
		} else {
			first = first.next;
		}
		size--;
		if (first != null) {
			first.previous = null;
		}
		return e;
	}

	public E removeLast() {
		E e = last.element;
		// If there's only 1 element left in the list, clear it
		if (getSize() == 1) {
			first = null;
			last = null;
		} else {
			last = last.previous;
		}
		size--;
		if (last != null) {
			last.next = null;
		}
		return e;
	}

	public boolean contains(E element) {
		Node<E> currentNode = first;
		while (currentNode != null) {
			if (currentNode.element == element) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("first=").append(first);
		sb.append(", last=").append(last);
		sb.append(", size=").append(getSize()).append(", ");
		if (!isEmpty()) {
			sb.append("[");
			Node<E> currentNode = first;
			while (currentNode != null) {
				if (currentNode != first) {
					sb.append(", ");
				}
				sb.append(currentNode.element);
				currentNode = currentNode.next;
			}
			sb.append("]");
		} else {
			sb.append("(empty)");
		}

		return sb.toString();
	}

	private static class Node<E> {
		E element;
		Node<E> previous;
		Node<E> next;

		Node(Node<E> previous, E element, Node<E> next) {
			this.element = element;
			this.previous = previous;
			this.next = next;
		}

		@Override
		public String toString() {
			return element.toString();
		}
	}
}
