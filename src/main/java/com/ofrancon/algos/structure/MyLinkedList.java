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
		if (size() == 0) {
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
		if (size() == 0) {
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
		if (size() == 1) {
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
		if (size() == 1) {
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

	public boolean remove(E element) {
		Node<E> currentNode = first;
		while (currentNode != null) {
			if (currentNode.element.equals(element)) {
				if (currentNode == first) {
					removeFirst();
				} else if (currentNode == last) {
					removeLast();
				} else {
					currentNode.previous.next = currentNode.next;
					currentNode.next.previous = currentNode.previous;
					size--;
				}
				break; // Done.
			}
			currentNode = currentNode.next;
		}
		return false;
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

	public int size() {
		return size;
	}

	public E getFirst() {
		return first == null ? null : first.element;
	}

	public E getLast() {
		return last == null ? null : last.element;
	}

	public E get(int index) {
		Node<E> currentNode = first;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				return currentNode.element;
			} else {
				currentNode = currentNode.next;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("first=").append(first);
		sb.append(", last=").append(last);
		sb.append(", size=").append(size()).append(", ");
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
