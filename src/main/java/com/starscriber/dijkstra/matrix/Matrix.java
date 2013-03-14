package com.starscriber.dijkstra.matrix;

import com.starscriber.dijkstra.Graph;

public abstract class Matrix {
	protected int[][] matrix;
	protected Graph graph;

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public abstract void createMatrix();
}//end of class