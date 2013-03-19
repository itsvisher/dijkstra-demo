package com.starscriber.dijkstra;

import java.util.List;

public class Vertex {
	private int name;
	private int distance;
	private int diameter;
	private Vertex previous;
	private float centrality;
	private List<Vertex> adjacencyList;

	public Vertex() {
		super();
		distance = Integer.MAX_VALUE;
	}

	public Vertex(int name) {
		this.name = name;
		distance = Integer.MAX_VALUE;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	public float getCentrality() {
		return centrality;
	}

	public void setCentrality(float centrality) {
		this.centrality = centrality;
	}

	public List<Vertex> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(List<Vertex> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	@Override
	public String toString() {
		return "" + name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name;
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
		if (name != other.name) {
			return false;
		}

		return true;
	}

}//end of class