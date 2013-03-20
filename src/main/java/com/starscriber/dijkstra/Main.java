package com.starscriber.dijkstra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.starscriber.dijkstra.communities.Community;
import com.starscriber.dijkstra.matrix.ConnectionMatrix;
import com.starscriber.dijkstra.matrix.StepsMatrix;
import com.starscriber.dijkstra.util.FileOPs;

/**
 * Main. Provide vertices as args[0] and edges as args[1] in command line
 * arguments.
 *
 * @author Vishal | Paradigm Creatives
 */
public class Main {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Please provide Vertices and Edges [Example: extras/vertices.list]...");
			return;
		}

		// Preparing vertices and edges
		List<Vertex> vertices = null;
		List<Edge> edges = null;
		try {
			vertices = FileOPs.prepareVertices(args[0]);
			edges = FileOPs.prepareEdges(args[1]);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		if (vertices == null) {
			System.err.println("Error occurred while preparing vertices...");
			return;
		}

		if (edges == null) {
			System.err.println("Error occurred while preparing edges...");
			return;
		}

		System.out.println("Vertices: " + vertices + "\n");
		System.out.println("Edges: " + edges + "\n");

		// Connection Matrix
		ConnectionMatrix matrix = new ConnectionMatrix(new Graph(vertices, edges));
		matrix.createMatrix();
		System.out.println("\nAdjacency List for all vertices: ");
		for (Vertex v: vertices) {
			System.out.println(v + ": " + v.getAdjacencyList());
		}
		System.out.println("\nConnection Matrix: ");
		matrix.showMatrix();

		// Steps Matrix
		StepsMatrix stepsMatrix = new StepsMatrix(new Graph(vertices, edges));
		stepsMatrix.createMatrix();
		stepsMatrix.prepareDiameterAndCentrality();
		vertices = stepsMatrix.addSteps();
		System.out.println("\nSteps Matrix: ");
		stepsMatrix.showMatrix();

		Community community = new Community(vertices, edges);
//		System.out.println(community.getRefinedCommunity(stepsMatrix.getMatrix()));

		System.out.println(community.getCommunities(stepsMatrix.getMatrix()));
	}//end of main()

}//end of class