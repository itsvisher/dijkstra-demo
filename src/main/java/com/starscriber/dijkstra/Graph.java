package com.starscriber.dijkstra;

import java.util.List;

public class Graph {
	private List<Vertex> vertices;
	private List<Edge> edges;

	public Graph() {
		super();
	}

	public Graph(List<Vertex> vertices, List<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}//end of class