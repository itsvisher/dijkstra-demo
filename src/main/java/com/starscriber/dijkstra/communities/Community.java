package com.starscriber.dijkstra.communities;

import java.util.ArrayList;
import java.util.List;

import com.starscriber.dijkstra.Vertex;

public class Community {
	private List<Vertex> vertices;

	public Community(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	/**
	 * Gets the <code>Vertex</code> with highest centrality. In case there are
	 * more than 1 vertex with highest centrality, choose the one with lowest
	 * diameter.
	 *
	 * @return <code>Vertex</code> with highest centrality and lowest diameter.
	 */
	public Vertex getMaxCentralityVertex() {
		List<Vertex> maxCentralityVertices = new ArrayList<Vertex>();
		Vertex vertex = null;
		float centrality = (vertices != null)? vertices.get(0).getCentrality(): 0;
		int diameter = (vertices != null)? vertices.get(0).getDiameter(): 0;
		vertex = vertices.get(0);
		for (Vertex v: vertices) {
			if (v.getCentrality() >= centrality) {
				maxCentralityVertices.add(v);
			}
		}

		if (maxCentralityVertices.size() > 1) {
			for (Vertex u: maxCentralityVertices) {
				if (u.getDiameter() < diameter) {
					vertex = u;
				}
			}
		}

		return vertex;
	}//end of getMaxCentralityVertex()

	/**
	 * Selects 5 nodes excluding the source, starting with lowest steps from source vertex.
	 *
	 * @param stepsMatrix
	 * @return
	 */
	public List<Vertex> getNearVertices(int[][] stepsMatrix) {
		List<Vertex> stepVertices = new ArrayList<Vertex>();
		Vertex maxCentralityVertex = getMaxCentralityVertex();
		int index = vertices.indexOf(maxCentralityVertex);

		for (int j = 0; j < vertices.size(); j++) {
			if (stepsMatrix[index][j] < vertices.size() && stepsMatrix[index][j] != 0 && stepVertices.size() < 5) {
//				if (j > 0 && stepsMatrix[index][j] == stepsMatrix[index][j - 1] && vertices.get(j -1).getCentrality() < vertices.get(j).getCentrality()) {
//					stepVertices.remove(vertices.get(j - 1));
//				}
				stepVertices.add(vertices.get(j));
			}
		}
		stepVertices.add(maxCentralityVertex);

		return stepVertices;
	}

}//end of class