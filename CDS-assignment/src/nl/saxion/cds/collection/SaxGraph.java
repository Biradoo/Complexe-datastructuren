package nl.saxion.cds.collection;

/**
 * A directed, weighted graph data structure with nodes and edges. The graph CAN be disconnected.
 *
 * @param <V> type of the nodes in the graph
 */
public interface SaxGraph<V> extends SaxCollection<V>, Iterable<V> {
    /**
     * Add an edge between two nodes (with given values) with the given weight.
     * If a node based on fromValue or toValue does not exist, it is automatically added to the graph.
     * <br/><i>Edges are always directed (and always have a weight) i.e. are NOT bidirectional</i>,
     * so adding an edge means that there will only be an edge between from en to node, NOT vice versa.
     *
     * @param fromValue originating node value
     * @param toValue   connected node value
     * @param weight   weight of the edge
     * @throws KeyNotFoundException if fromValue or toValue are not already part of this graph
     */
    public void addEdge(V fromValue, V toValue, double weight) throws KeyNotFoundException;

    /**
     * Add two edges (see addEdge()), effectively connecting from and to in both directions.
     * @param fromValue
     * @param toValue
     * @param weight
     */
    public void addEdgeBidirectional(V fromValue, V toValue, double weight);

    /**
     * Gets a list of edges from the given node.
     *
     * @param value the value of the node the edges originate from
     * @return a list of edges which originate from the node with the given value
     */
    SaxList<DirectedEdge<V>> getEdges(V value);

    /**
     * @return the total weight of (ALL) edges of the graph
     */
    double getTotalWeight();

    /**
     * Execute the Dijkstra algorithm; determine the shortest paths from the start node to all other nodes.
     *
     * @param startNode the node to start searching from
     * @return the graph (a tree!) which only contains the edges which comprise all shortest paths (a copy)
     */
    SaxGraph<V> shortestPathsDijkstra(V startNode);

    /**
     * Execute the A* algorithm to determine the shortest path from startNode to endNode.
     *
     * @param startNode the node to start searching
     * @param endNode   the target node
     * @param estimator a (handler) function to estimate the distance (weight) between two nodes
     * @return a list of edges (from start to end) which comprise the shortest path from startNode to endNode.
     */
    SaxList<DirectedEdge<V>> shortestPathAStar(V startNode, V endNode, Estimator<V> estimator);

    /**
     * Determine the minimal cost (total weight) of edges which are necessary to connect all nodes.
     * A disconnected graph will still be disconnected, but all edges will be examined;
     * the algorithm must therefore be run on each sub graph.
     *
     * @return the MCST graph (a copy)
     */
    SaxGraph<V> minimumCostSpanningTree();

    @FunctionalInterface
    public interface Estimator<T> {
        double estimate(T current, T target);
    }

    /**
     * A directed edge in the graph.
     *
     * @param from   from node
     * @param to     to node
     * @param weight weight
     * @param <T>    node type
     */
    public record DirectedEdge<T>(T from, T to, double weight) implements Comparable<DirectedEdge<T>> {
        @Override
        public String toString() {
            return to + "(" + weight + ',' + (from == null ? "-" : from) + ")";
        }

        @Override
        public int compareTo(DirectedEdge<T> o) {
            return Double.compare(weight, o.weight);
        }
    }
}
