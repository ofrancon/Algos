package com.ofrancon.algos.graph;

import com.ofrancon.algos.structure.MyQueue;

public class MyGraph {

	// Adjacency info
	private final MyEdgeNode[] edges;
	// Outdegree of each vertex
	private final int[] degree;
	// Number of vertices in graph
	private final int nbVertices;
	// Number of edges in graph
	private int nbEdges;
	// Is the graph directed?
	private final boolean isDirected;

	public MyGraph(int nbVertices, boolean isDirected) {
		this.nbVertices = nbVertices;
		this.nbEdges = 0;
		this.isDirected = isDirected;
		edges = new MyEdgeNode[nbVertices + 1]; // 0 is not used
		degree = new int[nbVertices + 1]; // 0 is not used
	}

	public void insertEdge(int x, int y) {
		insertEdge(x, y, isDirected);
	}

	private void insertEdge(int x, int y, boolean isDirected) {
		MyEdgeNode next = edges[x];
		MyEdgeNode node = new MyEdgeNode(y, 0, next);
		edges[x] = node;
		degree[x] = degree[x] + 1; // We now have 1 valid edge for node x
		if (isDirected) {
			// Directed, so only one direction
			nbEdges = nbEdges + 1;
		} else {
			// Also insert the other way round
			insertEdge(y, x, true);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= nbVertices; i++) {
			sb.append(i).append(":");
			MyEdgeNode node = edges[i];
			while (node != null) {
				sb.append(" ").append(node);
				node = node.getNext();
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public int[] bfs(int start) {
		boolean[] processed = new boolean[nbVertices + 1];
		boolean[] discovered = new boolean[nbVertices + 1];
		int[] parents = new int[nbVertices + 1];

		MyQueue<Integer> verticesToVisit = new MyQueue<Integer>();
		int currentVertex = 0;
		int nextVertex = 0;
		MyEdgeNode currentEdge;

		verticesToVisit.enqueue(start);
		discovered[start] = true;
		while (!verticesToVisit.isEmpty()) {
			currentVertex = verticesToVisit.dequeue();
			processVertexEarly(currentVertex);
			processed[currentVertex] = true;
			currentEdge = edges[currentVertex];
			while (currentEdge != null) {
				nextVertex = currentEdge.getY();
				if (!processed[nextVertex] || isDirected) {
					processEdge(currentVertex, nextVertex);
				}
				if (!discovered[nextVertex]) {
					verticesToVisit.enqueue(nextVertex);
					discovered[nextVertex] = true;
					parents[nextVertex] = currentVertex;
				}
				currentEdge = currentEdge.getNext();
			}
		}
		processVertexLate(currentVertex);
		return parents;
	}

	private void processVertexEarly(int vertex) {
		System.out.println("Processed vertex: " + vertex);
	}

	private void processEdge(int currentVertex, int nextVertex) {
		System.out.println("Processed edge " + currentVertex + " -- " + nextVertex);
	}

	private void processVertexLate(int vertex) {
		// Do nothing -> do the work in processVertexEarly.
	}

	public void findPath(int start, int end, int[] parents) {
		if (start == end || end == -1) {
			System.out.print("\n" + start);
		} else {
			findPath(start, parents[end], parents);
			System.out.print(" " + end);
		}
	}

	public int[] dfs(int currentVertex) {
		boolean[] processed = new boolean[nbVertices + 1];
		boolean[] discovered = new boolean[nbVertices + 1];
		int[] parents = new int[nbVertices + 1];

		discovered[currentVertex] = true;
		processVertexEarly(currentVertex);

		int nextVertex;
		MyEdgeNode currentEdge = edges[currentVertex];
		while (currentEdge != null) {
			nextVertex = currentEdge.getY();
			if (discovered[nextVertex] = false) {
				parents[nextVertex] = currentVertex;
				processEdge(currentVertex, nextVertex);
				dfs(nextVertex);
			} else if (!processed[nextVertex] || !isDirected) {
				processEdge(currentVertex, nextVertex);
			}
			currentEdge = currentEdge.getNext();
		}
		processVertexLate(currentVertex);
		processed[currentVertex] = true;

		return parents;
	}
}
