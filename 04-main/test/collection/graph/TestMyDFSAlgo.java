package collection.graph;

import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.datastructures.graph.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMyDFSAlgo {
    private MyGraph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new MyGraph<>();

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "E", 1);
        graph.addEdge("E", "F", 1);
    }

    @Test
    public void GivenGraph_WhenDepthFirstTraversal_ThenCorrectOrderReturned() {
        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertEquals("A", dfsResult.get(0));
        assertEquals("C", dfsResult.get(1));
        assertEquals("E", dfsResult.get(2));
        assertEquals("F", dfsResult.get(3));
        assertEquals("B", dfsResult.get(4));
        assertEquals("D", dfsResult.get(5));
    }

    @Test
    public void GivenGraph_WhenTraversalFromIsolatedNode_ThenOnlyIsolatedNodeIsReturned() {
        graph.addEdge("G", "G", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("G");

        assertEquals(1, dfsResult.size());
        assertEquals("G", dfsResult.get(0));
    }

    @Test
    public void GivenGraph_WhenDepthFirstTraversalWithCycle_ThenCorrectOrderWithoutRevisit() {
        graph.addEdge("F", "A", 1);

        SaxList<String> dfsResult = graph.depthFirstTraversal("A");

        assertEquals("A", dfsResult.get(0));
        assertEquals("C", dfsResult.get(1));
        assertEquals("E", dfsResult.get(2));
        assertEquals("F", dfsResult.get(3));
        assertEquals("B", dfsResult.get(4));
        assertEquals("D", dfsResult.get(5));
    }

    @Test
    public void GivenEmptyGraph_WhenDepthFirstTraversal_ThenEmptyResult() {
        MyGraph<String> emptyGraph = new MyGraph<>();

        SaxList<String> dfsResult = emptyGraph.depthFirstTraversal("A");

        assertTrue(dfsResult.isEmpty());
    }
}
