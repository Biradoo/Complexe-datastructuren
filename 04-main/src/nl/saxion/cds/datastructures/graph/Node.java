package nl.saxion.cds.datastructures.graph;

public class Node<T> implements Comparable<Node<T>> {
    private final T node;
    private final double g; //Cost from start to this node
    private final double f; //Estimated cost from start to end
    private final Node<T> parent;

    public Node(T node, double g, double f, Node<T> parent) {
        this.node = node;
        this.g = g;
        this.f = f;
        this.parent = parent;
    }

    public T getNode() {
        return node;
    }

    public double getF() {
        return f;
    }

    @Override
    public int compareTo(Node<T> other) {
        return Double.compare(this.f, other.f);
    }
}