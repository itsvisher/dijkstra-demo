package com.starscriber.dijkstra.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.starscriber.dijkstra.Edge;
import com.starscriber.dijkstra.Vertex;

public class FileOPs {

	/**
	 * Prepares a list of vertices for the graph.
	 *
	 * @param verticesPath path of the vertex list file.
	 *
	 * @return <code>List</code> of <code>Vertex</code>s.
	 */
	public static List<Vertex> prepareVertices(String verticesPath) throws FileNotFoundException, IOException {
		List<Vertex> vertices = new ArrayList<Vertex>();
		BufferedReader br = null;
		Vertex vertex = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(verticesPath)));
			String name = null;
			while ((name = br.readLine()) != null) {
				if (name.contains("-")) {
					System.err.println("Invalid vertices file...");
					return null;
				}
				vertex = new Vertex(Integer.parseInt(name));
				vertices.add(vertex);
			}
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("File: " + verticesPath + " not found.");
		} catch (IOException ioe) {
			throw new IOException("Error reading file: " + verticesPath + ". Exception: " + ioe);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ioe) {
				System.err.println("Error closing reader. Exception: " + ioe);
			}
		}

		return vertices;
	}// end of prepareVertices()

	/**
	 * Prepares a list of edges for the graph.
	 *
	 * @param edgesPath path of the edge list file.
	 *
	 * @return <code>List</code> of <code>Edge</code>s.
	 */
	public static List<Edge> prepareEdges(String edgesPath) throws FileNotFoundException, IOException {
		List<Edge> edges = new ArrayList<Edge>();
		BufferedReader br = null;
		Edge edge = null;
		Vertex source = null;
		Vertex destination = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(edgesPath)));
			String read = null;
			while ((read = br.readLine()) != null) {
				String[] edgeVertices = read.split("-");
				if (edgeVertices.length < 2) {
					System.err.println("Invalid edges file...");
					return null;
				}

				source = new Vertex(Integer.parseInt(edgeVertices[0]));
				destination = new Vertex(Integer.parseInt(edgeVertices[1]));
				edge = new Edge(source, destination);
				edges.add(edge);
			}
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("File: " + edgesPath + " not found.");
		} catch (IOException ioe) {
			throw new IOException("Error reading file: " + edgesPath + ". Exception: " + ioe);
		} finally {
			try {
				br.close();
			} catch (IOException ioe) {
				System.err.println("Error closing reader. Exception: " + ioe);
			}
		}

		return edges;
	}//end of prepareEdges

}// end of class