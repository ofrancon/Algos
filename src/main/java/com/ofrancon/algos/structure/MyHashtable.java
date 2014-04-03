package com.ofrancon.algos.structure;

public class MyHashtable<K, V> {

	private final MyLinkedList<MyEntry<K, V>>[] array;
	private final int size;

	@SuppressWarnings("unchecked")
	public MyHashtable(int size) {
		array = new MyLinkedList[10];
		this.size = size;
	}

	public void insert(K key, V value) {
		int index = key.hashCode() % size;
		MyLinkedList<MyEntry<K, V>> list = array[index];
		if (list == null) {
			list = new MyLinkedList<MyEntry<K, V>>();
			array[index] = list;
		}
		// Search for the key in case it's already here
		MyEntry<K, V> entry = null;
		boolean isNew = true;
		for (int i = 0; i < list.size(); i++) {
			entry = list.get(i);
			if (key.equals(entry.key)) {
				// Update the entry
				entry.value = value;
				isNew = false;
				break;
			}
		}
		// Insert the key if it wasn't here
		if (isNew) {
			entry = new MyEntry<K, V>(key, value);
			list.addLast(entry);
		}
	}

	public V search(K key) {
		int index = key.hashCode() % size;
		MyLinkedList<MyEntry<K, V>> list = array[index];
		for (int i = 0; i < list.size(); i++) {
			MyEntry<K, V> entry = list.get(i);
			if (key.equals(entry.key)) {
				return entry.value;
			}
		}
		return null;
	}

	public void delete(K key) {
		int index = key.hashCode() % size;
		MyLinkedList<MyEntry<K, V>> list = array[index];
		for (int i = 0; i < list.size(); i++) {
			MyEntry<K, V> entry = list.get(i);
			if (key.equals(entry.key)) {
				list.remove(entry);
				break;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("MyHashtable:\n");
		for (int i = 0; i < array.length; i++) {
			sb.append(i).append(": ").append(array[i]).append("\n");
		}
		return sb.toString();
	}

	public int size() {
		int size = 0;
		for (int i = 0; i < array.length; i++) {
			MyLinkedList<MyEntry<K, V>> list = array[i];
			if (list != null) {
				size += list.size();
			}
		}
		return size;
	}

	class MyEntry<K, V> {
		K key;
		V value;

		MyEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("[");
			sb.append(key);
			sb.append("=");
			sb.append(value);
			sb.append("]");
			return sb.toString();
		}
	}
}
