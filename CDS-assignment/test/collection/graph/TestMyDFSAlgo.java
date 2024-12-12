package collection.graph;

import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.datastructures.graph.DFSIterator;
import nl.saxion.cds.datastructures.graph.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyDFSAlgo {
    private MyGraph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new MyGraph<>();
    }

    @Test
    public void GivenEmptyAdjacencyList_WhenInitializingDFSIterator_ThenStackIsEmpty() {
        MyGraph<String> emptyGraph = new MyGraph<>();
        DFSIterator<String> dfsIterator = new DFSIterator<>(emptyGraph.getAdjacencyList());

        assertFalse(dfsIterator.hasNext(), "DFSIterator should not have a next node for an empty adjacency list.");
    }

    @Test
    public void GivenGraphWithMultipleComponents_WhenTraversal_ThenOnlyComponentTraversed() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);

        graph.addEdge("D", "E", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("A");
        assertEquals(3, dfsResult.size());
        assertEquals("A", dfsResult.get(0));
        assertEquals("B", dfsResult.get(1));
        assertEquals("C", dfsResult.get(2));

        dfsResult = graph.depthFirstTraversal("D");
        assertEquals(2, dfsResult.size());
        assertEquals("D", dfsResult.get(0));
        assertEquals("E", dfsResult.get(1));
    }

    @Test
    public void GivenFullyConnectedGraph_WhenTraversal_ThenAllNodesVisited() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "A", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertEquals(3, dfsResult.size());
        assertTrue(dfsResult.contains("A"));
        assertTrue(dfsResult.contains("B"));
        assertTrue(dfsResult.contains("C"));
    }

    @Test
    public void GivenGraphWithSelfLoops_WhenTraversal_ThenSelfLoopsIgnored() {
        graph.addEdge("A", "A", 1);
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertEquals(3, dfsResult.size());
        assertEquals("A", dfsResult.get(0));
        assertEquals("B", dfsResult.get(1));
        assertEquals("C", dfsResult.get(2));
    }

    @Test
    public void GivenGraphWithCycles_WhenTraversal_ThenNoRevisit() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "A", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertEquals(3, dfsResult.size());
        assertEquals("A", dfsResult.get(0));
        assertTrue(dfsResult.contains("B"));
        assertTrue(dfsResult.contains("C"));
    }

    @Test
    public void GivenEmptyGraph_WhenTraversal_ThenEmptyResult() {
        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertTrue(dfsResult.isEmpty());
    }

    @Test
    public void GivenGraphWithDisconnectedNodes_WhenTraversal_ThenOnlyStartNodeReturned() {
        graph.addEdge("A", "B", 1); // Connected component
        graph.addVertex("C");      // Disconnected node

        SaxList<String> dfsResult = graph.depthFirstTraversal("C");

        assertEquals(1, dfsResult.size());
        assertEquals("C", dfsResult.get(0));
    }

    @Test
    public void GivenGraphWithBidirectionalEdges_WhenTraversal_ThenNoRevisit() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "A", 1); // Bidirectional edge
        graph.addEdge("B", "C", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertEquals(3, dfsResult.size());
        assertEquals("A", dfsResult.get(0));
        assertTrue(dfsResult.contains("B"));
        assertTrue(dfsResult.contains("C"));
    }

    @Test
    public void GivenNonExistentStartNode_WhenTraversal_ThenEmptyResult() {
        graph.addEdge("A", "B", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("Z");

        assertTrue(dfsResult.isEmpty(), "Traversal starting at a non-existent node should return an empty list.");
    }

    @Test
    public void GivenExhaustedDFSIterator_WhenCallingNext_ThenThrowException() {
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);

        DFSIterator<String> dfsIterator = new DFSIterator<>(graph.getAdjacencyList(), "A");

        while (dfsIterator.hasNext()) {
            dfsIterator.next();
        }

        assertThrows(ValueNotFoundException.class, dfsIterator::next);
    }
}
