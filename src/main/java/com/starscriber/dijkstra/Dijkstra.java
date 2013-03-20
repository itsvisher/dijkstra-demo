package com.starscriber.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
	private final List<Edge> edges;
	private Set<Vertex> visitedSet;
	private Set<Vertex> unvisitedSet;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;

	public Dijkstra(Graph graph) {
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	/**
	 * Prepares set of data such as visited and unvisited sets, distances etc.
	 *
	 * @param source source <code>Vertex</code>
	 */
	public void prepareData(Vertex source) {
		visitedSet = new HashSet<Vertex>();
		unvisitedSet = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Integer>();
		predecessors = new HashMap<Vertex, Vertex>();
		distance.put(source, 0);
		unvisitedSet.add(source);

		while (unvisitedSet.size() > 0) {
			Vertex v = getMinimum(unvisitedSet);
			visitedSet.add(v);
			unvisitedSet.remove(v);
			findMinimalDistances(v);
		}
	}

	private void findMinimalDistances(Vertex vertex) {
		List<Vertex> adjacentVertices = vertex.getAdjacencyList();
		for (Vertex target : adjacentVertices) {
			if (getShortestDistance(target) > getShortestDistance(vertex) + distanceBetween(vertex, target)) {
				distance.put(target, getShortestDistance(vertex) + distanceBetween(vertex, target));
				predecessors.put(target, vertex);
				unvisitedSet.add(target);
			}
		}
	}

	private int distanceBetween(Vertex u, Vertex v) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(u) && edge.getTarget().equals(v)) {
				return edge.getWeight();
			}
		}

		throw new RuntimeException("Should not happen");
	}

	private Vertex getMinimum(Set<Vertex> vertices) {
		Vertex minimum = null;
		for (Vertex vertex : vertices) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}

		return minimum;
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/**
	 * This method returns the path from the source to the target and
	 * NULL if no such path exists.
	 */
	public LinkedList<Vertex> getShortestPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}//end of getPath()

}//end of class