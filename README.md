# Disconnected Social Network Algorithm

### Introduction

A set of Vertices and Edges represents a Graph.

The present algorithm processes a Graph for building up communities based on some rule (not mentioned here). It uses <code>Dijkstra Algorithm</code> for building up the communities.

### Pre-requisites

The program requires two files, one containing a set of Vertices (nodes) and other containing the set of Edges. Both the files are to be provided to program via command line arguments, in order. File name can be anything. Here the file names used are: <code>vertices.list</code> and <code>edges.list</code>.

The <code>vertices.list</code> file contains vertices in form of numbers, each in new line (example):

    1
    2
    3
    4

and the <code>edges.list</code> contains connections between the vertices (example):

    1-2
    2-1
    2-3
    2-4
    3-2
    4-2

### To build

    mvn clean install

This process generates a jar file in <code>target</code> folder: <code>communities.jar</code>.

### To run

    java -jar target/communities.jar extras/vertices.list extras/edges.list

Both the files, <code>vertices.list</code> and <code>edges.list</code>, are provided as command line arguments.