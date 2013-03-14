package com.starscriber.dijkstra;

/**
 * Edge of a Graph.
 *
 * @author Vishal | Paradigm Creatives
 */
public class Edge {
	private final int weight;
	private final Vertex source;
	private final Vertex destination;

	public Edge(int weight, Vertex source, Vertex destination) {
		super();
		this.weight = weight;
		this.source = source;
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestination() {
		return destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + weight;
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
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}

		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}

		if (weight != other.weight) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Edge [weight = " + weight + ", source = " + source + ", destination = " + destination + "]\n";
	}

}// end of class