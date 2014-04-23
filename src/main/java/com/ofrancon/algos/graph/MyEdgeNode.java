package com.ofrancon.algos.graph;

public class MyEdgeNode {

	// Adjacency info
	private final int y;
	// Edge weight, if any
	private final int weight;
	// Next edge in the list
	private final MyEdgeNode next;

	public MyEdgeNode(int y, int weight, MyEdgeNode next) {
		this.y = y;
		this.weight = weight;
		this.next = next;
	}

	public int getY() {
		return y;
	}

	public MyEdgeNode getNext() {
		return next;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(y).append(")");
		return sb.toString();
	}
}
