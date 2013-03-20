package com.starscriber.dijkstra.communities;

import java.util.ArrayList;
import java.util.List;

import com.starscriber.dijkstra.Edge;
import com.starscriber.dijkstra.Vertex;

public class Community {
	private List<Vertex> vertices;
	private List<Vertex> completeVertices;
	private List<List<Vertex>> communites;
	private List<Edge> edges;

	public Community(List<Vertex> vertices, List<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
		makeVerticesCopy();
	}

	public List<List<Vertex>> getCommunities(int[][] stepsMatrix) {
		communites = new ArrayList<List<Vertex>>();
		while (vertices.size() != 0) {
			communites.add(getRefinedCommunity(stepsMatrix));
		}

		return communites;
	}

	/**
	 * Gets the <code>Vertex</code> with highest centrality. In case there are
	 * more than 1 vertex with highest centrality, choose the one with lowest
	 * diameter.
	 *
	 * @return <code>Vertex</code> with highest centrality and lowest diameter.
	 */
	private Vertex getMaxCentralityVertex() {
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
	 * Selects 5 nodes excluding the source, starting with lowest steps from
	 * source vertex. If the list has already 5 vertices, and the newer vertex
	 * has the same steps as the last added vertex, then vertex with greater
	 * centrality replaces the last vertex added.
	 *
	 * @param stepsMatrix steps matrix.
	 *
	 * @return raw community.
	 */
	private List<Vertex> getRawCommunity(int[][] stepsMatrix) {
		List<Vertex> rawCommunity = new ArrayList<Vertex>();
		Vertex maxCentralityVertex = getMaxCentralityVertex();
//		int indexOfMax = vertices.indexOf(maxCentralityVertex);
		int indexOfMax = completeVertices.indexOf(maxCentralityVertex);
		int[] stepsOfMax = stepsMatrix[indexOfMax];
		int length = stepsOfMax.length;
		Vertex lastVertex = null;

		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length; j++) {
				int step = stepsOfMax[j];
				if (step == i) {
					// Check if size is already 5 and the new vertex of same steps has greater centrality
					// then remove last and add the new vertex
					if (rawCommunity.size() == 5) {
						if (completeVertices.get(j).getCentrality() > lastVertex.getCentrality()) {
							rawCommunity.remove(lastVertex);
							rawCommunity.add(completeVertices.get(j));
							break;
						}
					}

					lastVertex = completeVertices.get(j);
					if (!lastVertex.isUsed()) {
						rawCommunity.add(lastVertex);
					}
				}
			}

			// Stop if the raw community size is 5
			if (rawCommunity.size() == 5) {
				break;
			}
		}

		// Add the Max Centrality Vertex to the raw community
		rawCommunity.add(maxCentralityVertex);

		return rawCommunity;
	}// end of getRawCommunity()

	/**
	 * Community with all <code>Vertex</code>s with steps <= 10.
	 *
	 * @param rawCommunity the raw community.
	 *
	 * @return <code>List&lt;Vertex&gt;></code> with steps less than 10 forming
	 *         a community.
	 */
	public List<Vertex> getRefinedCommunity(int[][] stepsMatrix) {
		List<Vertex> refinedCommunity = new ArrayList<Vertex>();
		List<Vertex> rawCommunity = getRawCommunity(stepsMatrix);
		List<Vertex> leftBehind = new ArrayList<Vertex>();
		int diameter = 0;

		for (Vertex v: rawCommunity) {
			diameter = v.getDiameter() - getNewDiameter(v, rawCommunity);
			if (diameter <= 10) {
				refinedCommunity.add(v);
				v.setUsed(true);
			} else {
				leftBehind.add(v);
			}
		}

		if (refinedCommunity.size() > 0) {
			removeUsedVertices(refinedCommunity);
		} else {
			vertices.clear();
		}

		if (leftBehind.size() > 0) {
//			removeConnections(leftBehind, refinedCommunity);
			removeUsedVertices(removeConnections(leftBehind, refinedCommunity));
		}

		return refinedCommunity;
	}// end of getRefinedCommunity()

	/**
	 * Removes those <code>Vertex</code>s from the main list which have already
	 * formed a community.
	 *
	 * @param refinedCommunity a well formed community of vertices.
	 */
	private void removeUsedVertices(List<Vertex> refinedCommunity) {
		for (Vertex v: refinedCommunity) {
			vertices.remove(v);
		}
	}// end of removeUsedVertices()

	/**
	 * 
	 * @param leftBehind
	 * @param refinedCommunity
	 */
	private List<Vertex> removeConnections(List<Vertex> leftBehind, List<Vertex> refinedCommunity) {
		List<Edge> removableEdges =  new ArrayList<Edge>();
		List<Vertex> removableLeftBehindVertices = new ArrayList<Vertex>();

//		for (Vertex v: leftBehind) {
//			for (Vertex u: refinedCommunity) {
//				removableEdges.add(new Edge(v, u));
//				removableEdges.add(new Edge(u, v));
//			}
//		}

		for (Vertex v: leftBehind) {
			for (Edge e: edges) {
				if (e.getSource().equals(v)) {
					for (Vertex u: refinedCommunity) {
						if (e.getTarget().equals(u)) {
							removableEdges.add(e);
						}
					}
				}
				if (e.getTarget().equals(v)) {
					for (Vertex u: refinedCommunity) {
						if (e.getSource().equals(u)) {
							removableEdges.add(e);
						}
					}
				}
			}
		}

		for (Edge e: removableEdges) {
			edges.remove(e);
		}

		for (Vertex v: leftBehind) {
			boolean exists = false;
			for (Edge e: edges) {
				if (e.getSource().equals(v)) {
					exists = true;
				}
			}

			if (!exists) {
				removableLeftBehindVertices.add(v);
			}
		}
		
		return removableLeftBehindVertices;
	}//end of removeEdges()

	/**
	 * Gets diameter for the remaining no. of <code>Vertex</code>s for a <code>Vertex</code>.
	 *
	 * @param source starting <code>Vertex</code>.
	 * @param rawCommunity the raw community.
	 *
	 * @return diameter of the <code>Vertex</code>s other than present in raw community.
	 */
	private int getNewDiameter(Vertex source, List<Vertex> rawCommunity) {
		int diameter = 0;
		List<Integer> steps = source.getSteps();
		Vertex u = null;
		for (int i = 0; i < completeVertices.size(); i++) {
			u = completeVertices.get(i);
			if (!rawCommunity.contains(u)) {
				diameter = diameter + steps.get(i);
			}
		}

		return diameter;
	}// end of getNewDiameter()

	/**
	 * Makes a copy of <code>List&lt;Vertex&gt;</code>s.
	 */
	private void makeVerticesCopy() {
		completeVertices = new ArrayList<Vertex>();
		for (Vertex v: vertices) {
			completeVertices.add(v);
		}
	}
}//end of class