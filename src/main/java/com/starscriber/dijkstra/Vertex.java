package com.starscriber.dijkstra;

import java.util.Set;

/**
 * Vertex of a Graph.
 *
 * @author Vishal | Paradigm Creatives
 */
public class Vertex {
	private final String id;
	private int diameter;
	private float centrality;
	private Set<Vertex> adjacentNodes;

	public Vertex(String id) {
		this.id = id;
	}

	public Vertex(String id, int diameter, float centrality,
			Set<Vertex> adjacenttNodes) {
		super();
		this.id = id;
		this.diameter = diameter;
		this.centrality = centrality;
		this.adjacentNodes = adjacenttNodes;
	}

	public String getId() {
		return id;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public float getCentrality() {
		return centrality;
	}

	public void setCentrality(float centrality) {
		this.centrality = centrality;
	}

	public Set<Vertex> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Set<Vertex> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	@Override
	public String toString() {
		return "Vertex [id = " + id + ", diameter = " + diameter + ", centrality = "
				+ centrality + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Vertex other = (Vertex) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		return true;
	}

}//end of class