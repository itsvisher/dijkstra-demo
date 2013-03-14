package com.starscriber.dijkstra.matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.starscriber.dijkstra.Edge;
import com.starscriber.dijkstra.Vertex;

/**
 * This class represents the Steps matrix for the vertices. Steps matrix is
 * nothing but the shortest path matrix (Dijkstra Algorithm) between two or more
 * vertices.
 *
 * @author Vishal | Paradigm Creatives
 */
public class StepsMatrix extends Matrix {
	protected int[][] matrix;
	private List<Edge> edges;
	private List<Vertex> vertices;

	@Override
	public void createMatrix() {
	}// end of createMatrix() 

	/**
	 * Gets shortest distance between two vertices.
	 *
	 * @param source source or starting <code>Vertex</code>.
	 * @param destination destination or ending <code>Vertex</code>.
	 */
	protected void getDistance(Vertex source, Vertex destination) {
	}// end of getDistance()

	/**
	 * List of different paths in order.
	 *
	 * @param source source of starting <code>Vertex</code>.
	 * @param destination destination or ending <code>Vertex</code>.
	 *
	 * @return <code>List</code> of paths containing <code>Vertex</code>.
	 */
	protected List<Vertex> getPath(Vertex source, Vertex destination) {
		List<Vertex> path = new LinkedList<Vertex>();
		Vertex vertex = null;

		for (int i = 0; i < vertices.size(); i++) {
			vertex = vertices.get(i);
		}

		return path;
	}// end of getPaths

}//end of class