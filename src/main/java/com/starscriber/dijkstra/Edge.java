package com.starscriber.dijkstra;

public class Edge {
	private int weight;
	private Vertex source;
	private Vertex target;

	public Edge() {
		super();
		weight = 1;
	}

	public Edge(Vertex source, Vertex target) {
		weight = 1;
		this.source = source;
		this.target = target;
	}

	public Edge(int weight, Vertex source, Vertex target) {
		this.weight = weight;
		this.source = source;
		this.target = target;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getTarget() {
		return target;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "(" + source + ", " + target + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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

		Edge other = (Edge) obj;
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}

		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}

		return true;
	}

}//end of class