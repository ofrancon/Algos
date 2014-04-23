package com.ofrancon.algos.graph;

import org.junit.Test;

public class TestMyGraph {

	private MyGraph createGraph() {
		MyGraph graph = new MyGraph(5, false);
		graph.insertEdge(1, 2);
		graph.insertEdge(1, 5);
		graph.insertEdge(2, 3);
		graph.insertEdge(2, 4);
		graph.insertEdge(2, 5);
		graph.insertEdge(3, 4);
		graph.insertEdge(4, 5);
		return graph;
	}

	private MyGraph createGraph2() {
		MyGraph graph = new MyGraph(6, false);
		graph.insertEdge(1, 2);
		graph.insertEdge(1, 5);
		graph.insertEdge(1, 6);
		graph.insertEdge(2, 3);
		graph.insertEdge(2, 5);
		graph.insertEdge(3, 4);
		graph.insertEdge(4, 5);
		return graph;
	}

	@Test
	public void testPrintMyGraph() {
		MyGraph graph = createGraph();
		System.out.println(graph);
	}

	@Test
	public void testBFS() {
		MyGraph graph = createGraph();
		int[] parents = graph.bfs(1);
		graph.findPath(1, 4, parents);
		graph.findPath(1, 5, parents);
		graph.findPath(1, 3, parents);
	}

	@Test
	public void testDFS() {
		MyGraph graph = createGraph2();
		int[] parents = graph.bfs(1);
	}

}
