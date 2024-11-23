package nl.saxion.cds.datastructures.graph;

import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.datastructures.MyHashMap;
import nl.saxion.cds.datastructures.MyStack;

import java.util.Iterator;

public class DFSIterator<V extends Comparable<V>> implements Iterator<V> {
    private final MyStack<V> stack = new MyStack<>();
    private final MyHashMap<V, Boolean> visited;
    private final MyHashMap<V, SaxList<SaxGraph.DirectedEdge<V>>> adjacencyList;

    public DFSIterator(MyHashMap<V, SaxList<SaxGraph.DirectedEdge<V>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.visited = new MyHashMap<>();
        if (!adjacencyList.isEmpty()) {
            stack.push(adjacencyList.getKeys().iterator().next());
        }
    }

    public DFSIterator(MyHashMap<V, SaxList<SaxGraph.DirectedEdge<V>>> adjacencyList, V startNode) {
        this.adjacencyList = adjacencyList;
        this.visited = new MyHashMap<>();
        if (adjacencyList.contains(startNode)) {
            stack.push(startNode);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public V next() {
        if (!hasNext()) throw new ValueNotFoundException("No next value");
        V node = (V) stack.pop();
        if (!visited.contains(node)) {
            visited.add(node, true);
            for (SaxGraph.DirectedEdge<V> edge : adjacencyList.get(node)) {
                if (!visited.contains(edge.to())) {
                    stack.push(edge.to());
                }
            }
            return node;
        }
        return next();
    }
}