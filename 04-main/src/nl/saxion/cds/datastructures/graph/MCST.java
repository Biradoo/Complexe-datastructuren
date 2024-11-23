package nl.saxion.cds.datastructures.graph;

import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.MyHashMap;
import nl.saxion.cds.datastructures.MyQueue;

public class MCST<V extends Comparable<V>> {
    MyGraph<V> mcst = new MyGraph<>(); // Contains the MCST
    MyHashMap<V, Boolean> visited = new MyHashMap<>(); // Keeps track of visited nodes
    MyQueue<SaxGraph.DirectedEdge<V>> edgeQueue = new MyQueue<>(); // Queue of edges to be processed

    MyArrayList<V> vertices = new MyArrayList<>();
}
