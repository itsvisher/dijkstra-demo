package com.starscriber.dijkstra.matrix;

import java.util.LinkedList;
import java.util.List;

import com.starscriber.dijkstra.Edge;
import com.starscriber.dijkstra.Graph;
import com.starscriber.dijkstra.Vertex;

/**
 * This class represents the connection matrix between the vertices. If a vertex
 * is connected to another, it is shown as 1 in the matrix, else 0.
 *
 * @author Vishal | Paradigm Creatives
 */
public class ConnectionMatrix {
	protected int[][] matrix;
	private List<Edge> edges;
	private List<Vertex> vertices;

	public ConnectionMatrix(Graph graph) {
		vertices = graph.getVertices();
		edges = graph.getEdges();
		matrix = new int[vertices.size()][vertices.size()];
	}

	/**
	 * Creates connection matrix of the vertices. If a vertex is connected to
	 * another matrix the connection is shown as 1, else 0 in the matrix.
	 */
	public void createMatrix() {
		int length = vertices.size();
		Vertex v = null;
		Edge e = null;
		List<Vertex> adjacencyList = null;
		for (int i = 0; i < length; i++) {
			v = vertices.get(i);
			adjacencyList = new LinkedList<Vertex>();
			for (int j = 0; j < length; j++) {
				e = new Edge(1, v, vertices.get(j));
				if (edges.contains(e)) {
					matrix[i][j] = 1;
					adjacencyList.add(vertices.get(j));
					v.setAdjacencyList(adjacencyList);
				}
			}
		}
	}// end of createMatrix()

	/**
	 * Prints the connection matrix. Call only when created else a matrix of all
	 * ZEROs will be shown.
	 */
	public void showMatrix() {
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();
		}
	}// end of showMatrix()

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}// end of class