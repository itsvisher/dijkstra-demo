package com.starscriber.dijkstra.communities;

import java.util.ArrayList;
import java.util.List;

import com.starscriber.dijkstra.Vertex;

public class Community {
	private List<Vertex> vertices;

	public Community(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public Vertex getMaxCentralityVertex() {
		Vertex vertex = null;
		float centrality = (vertices != null)? vertices.get(0).getCentrality(): 0;
		vertex = vertices.get(0);
		for (Vertex v: vertices) {
			if (v.getCentrality() > centrality) {
				vertex = v;
			}
		}

		return vertex;
	}//end of getMaxCentralityVertex()

	public List<Vertex> getNearVertices(int[][] stepsMatrix) {
		List<Vertex> nearVertices = new ArrayList<Vertex>();
		Vertex maxCentralityVertex = getMaxCentralityVertex();
		int index = vertices.indexOf(maxCentralityVertex);

		for (int j = 0; j < vertices.size(); j++) {
			if (stepsMatrix[index][j] < 4 && stepsMatrix[index][j] != 0 && nearVertices.size() < 6) {
				nearVertices.add(vertices.get(j));
			}
		}
		nearVertices.add(maxCentralityVertex);

		return nearVertices;
	}

}//end of class