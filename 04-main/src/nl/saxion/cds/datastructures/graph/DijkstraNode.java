package nl.saxion.cds.datastructures.graph;

public class DijkstraNode<V> implements Comparable<DijkstraNode<V>> {
    private final V node;
    private final double distance;

    public DijkstraNode(V node, double distance) {
        this.node = node;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public V getNode() {
        return node;
    }

    @Override
    public int compareTo(DijkstraNode<V> other) {
        return Double.compare(this.distance, other.distance);
    }
}