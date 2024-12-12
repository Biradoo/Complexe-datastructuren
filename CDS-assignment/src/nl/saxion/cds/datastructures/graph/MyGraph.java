package nl.saxion.cds.datastructures.graph;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.MyHashMap;
import nl.saxion.cds.datastructures.MyPriorityQueue;

import java.util.Comparator;
import java.util.Iterator;

public class MyGraph<V extends Comparable<V>> implements SaxGraph<V> {
    private final MyHashMap<V, SaxList<DirectedEdge<V>>> adjacencyList = new MyHashMap<>();
    private final MyHashMap<V, Double> distances = new MyHashMap<>();

    @Override
    public void addEdge(V fromValue, V toValue, double weight) {
        if (!adjacencyList.contains(fromValue)) {
            adjacencyList.add(fromValue, new MyArrayList<>());
        }
        if (!adjacencyList.contains(toValue)) {
            adjacencyList.add(toValue, new MyArrayList<>());
        }
        adjacencyList.get(fromValue).addLast(new DirectedEdge<>(fromValue, toValue, weight));
    }

    @Override
    public void addEdgeBidirectional(V fromValue, V toValue, double weight) {
        addEdge(fromValue, toValue, weight);
        addEdge(toValue, fromValue, weight);
    }

    @Override
    public SaxList<DirectedEdge<V>> getEdges(V value) {
        return adjacencyList.contains(value) ? adjacencyList.get(value) : new MyArrayList<>();
    }

    public DirectedEdge<V> getEdge(V from, V to) {
        SaxList<DirectedEdge<V>> edges = adjacencyList.get(from);
        if (edges != null) {
            for (DirectedEdge<V> edge : edges) {
                if (edge.to().equals(to)) {
                    return edge;
                }
            }
        }
        return null;
    }

    @Override
    public double getTotalWeight() {
        double totalWeight = 0;
        for (SaxList<DirectedEdge<V>> edges : adjacencyList.values()) {
            for (DirectedEdge<V> edge : edges) {
                totalWeight += edge.weight();
            }
        }
        return totalWeight;
    }

    public double getDistance(V vertex) {
        if(!distances.contains(vertex)) {
            throw new ValueNotFoundException(vertex.toString());
        } else {
            return distances.get(vertex);
        }
    }

    public SaxList<V> depthFirstTraversal(V startNode) {
        SaxList<V> dfsResult = new MyArrayList<>();
        DFSIterator<V> iterator = new DFSIterator<>(adjacencyList, startNode);

        //Start iteration and add each node to result
        while (iterator.hasNext()) {
            V node = iterator.next();
            dfsResult.addLast(node);
        }
        return dfsResult;
    }

    @Override
    public SaxGraph<V> shortestPathsDijkstra(V startNode) {
        MyPriorityQueue<DijkstraNode<V>> priorityQueue = new MyPriorityQueue<>();
        MyGraph<V> resultGraph = new MyGraph<>();

        for (V vertex : adjacencyList.getKeys()) {
            distances.add(vertex, Double.POSITIVE_INFINITY);
        }

        distances.remove(startNode);
        distances.add(startNode, 0.0);
        priorityQueue.enqueue(new DijkstraNode<>(startNode, 0.0));

        while (!priorityQueue.isEmpty()) {
            DijkstraNode<V> currentEdge = priorityQueue.dequeue();
            V currentNode = currentEdge.getNode();

            for (DirectedEdge<V> edge : getEdges(currentNode)) {
                V neighbor = edge.to();
                double newDist = distances.get(currentNode) + edge.weight();

                if (newDist < distances.get(neighbor)) {
                    distances.remove(neighbor);
                    distances.add(neighbor, newDist);
                    resultGraph.addEdge(currentNode, neighbor, edge.weight());
                    priorityQueue.enqueue(new DijkstraNode<>(neighbor, newDist));
                }
            }
        }
        return resultGraph;
    }

    @Override
    public SaxList<DirectedEdge<V>> shortestPathAStar(V startNode, V endNode, Estimator<V> estimator) {
        if (estimator == null) {
            estimator = (Estimator<V>) new HeuristicEstimator();
        }

        MyPriorityQueue<Node<V>> openSet = new MyPriorityQueue<Node<V>>(Comparator.comparingDouble(Node<V>::getF));
        SaxList<V> closedSet = new MyArrayList<>();

        // Starting node setup
        Node<V> start = new Node<>(startNode, 0, estimator.estimate(startNode, endNode), null);
        openSet.enqueue(start);

        MyHashMap<V, Node<V>> cameFrom = new MyHashMap<>(); //Track path for reconstruction
        MyHashMap<V, Double> gScore = new MyHashMap<>();
        gScore.add(startNode, 0.0);

        while (!openSet.isEmpty()) {
            Node<V> current = openSet.dequeue();

            //Check if we've reached the destination
            if (current.getNode().equals(endNode)) {
                return reconstructPath(cameFrom, current); //Reconstruct path from cameFrom map
            }

            closedSet.addLast(current.getNode());

            //Explore each neighbor of the current node
            for (DirectedEdge<V> edge : getEdges(current.getNode())) {
                V neighbor = edge.to();
                if (closedSet.contains(neighbor)) {
                    continue; //Skip already evaluated neighbors
                }

                double currentGScore = gScore.contains(current.getNode()) ? gScore.get(current.getNode()) : Double.MAX_VALUE;
                double tentativeGScore = currentGScore + edge.weight();


                //Only proceed if this path to neighbor is better than any previous path
                if (tentativeGScore < (gScore.contains(neighbor) ? gScore.get(neighbor) : Double.MAX_VALUE)) {
                    //Record best path to this neighbor
                    cameFrom.put(neighbor, current); //Track predecessor
                    gScore.put(neighbor, tentativeGScore);

                    double fScore = tentativeGScore + estimator.estimate(neighbor, endNode);
                    Node<V> neighborNode = new Node<>(neighbor, tentativeGScore, fScore, current);
                    openSet.enqueue(neighborNode);
                }
            }
        }
        return null; //No path found if openSet is exhausted without finding endNode
    }

    @Override
    public MyGraph<V> minimumCostSpanningTree() {
        MyGraph<V> mcst = new MyGraph<>();
        MyHashMap<V, Boolean> visited = new MyHashMap<>();
        MyPriorityQueue<DirectedEdge<V>> priorityQueue = new MyPriorityQueue<>(
                (edge1, edge2) -> Double.compare(edge1.weight(), edge2.weight())
        );

        // Start with an arbitrary vertex
        V startVertex = adjacencyList.getKeys().iterator().next();
        visited.put(startVertex, true);
        mcst.addVertex(startVertex);

        // Add all edges from the start vertex to the priority queue
        for (DirectedEdge<V> edge : getEdges(startVertex)) {
            priorityQueue.enqueue(edge);
        }

        // Build the MCST
        while (!priorityQueue.isEmpty()) {
            DirectedEdge<V> edge = priorityQueue.dequeue();
            V from = edge.from();
            V to = edge.to();

            // Skip if the destination vertex is already visited
            if (visited.contains(to) && visited.get(to)) {
                continue;
            }

            // Add the edge to the MCST
            mcst.addEdge(from, to, edge.weight());
            visited.put(to, true);

            // Add the vertex to the MCST and enqueue its edges
            for (DirectedEdge<V> neighborEdge : getEdges(to)) {
                if (!visited.contains(neighborEdge.to())) {
                    priorityQueue.enqueue(neighborEdge);
                }
            }
        }
        return mcst;
    }

    public void addVertex(V vertex) {
        if (!adjacencyList.contains(vertex)) {
            adjacencyList.add(vertex, new MyArrayList<>());
        }
    }

    private SaxList<DirectedEdge<V>> reconstructPath(MyHashMap<V, Node<V>> cameFrom, Node<V> current) {
        SaxList<DirectedEdge<V>> totalPath = new MyArrayList<>();
        while (cameFrom.contains(current.getNode())) {
            Node<V> previousNode = cameFrom.get(current.getNode());
            DirectedEdge<V> edge = getEdge(previousNode.getNode(), current.getNode());
            totalPath.addFirst(edge);
            current = previousNode;
        }
        return totalPath;
    }

    @Override
    public Iterator<V> iterator() {
        return new DFSIterator<>(adjacencyList);
    }


    @Override
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    @Override
    public int size() {
        return adjacencyList.size();
    }

    @Override
    public String graphViz(String name) {
        return null;
    }

    @Override
    public String graphViz() {
        return SaxGraph.super.graphViz();
    }

    public MyHashMap<V, SaxList<DirectedEdge<V>>> getAdjacencyList() {
        return adjacencyList;
    }
}