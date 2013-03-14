package com.starscriber.dijkstra.matrix;

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
public class ConnectionMatrix extends Matrix {
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
		Vertex vertex = null;
		Edge edge = null;

		for (int i = 0; i < 18; i++) {
			vertex = vertices.get(i);
			for (int j = 0; j < 18; j++) {
				edge = new Edge(1, vertex, vertices.get(j));
				if (edges.contains(edge)) {
					matrix[i][j] = 1;
				}
			}
		}
	}// end of createMatrix()

	/**
	 * Prints the connection matrix. Call only when created else a matrix of all
	 * ZEROs will be shown.
	 */
	public void showMatrix() {
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 18; j++) {
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