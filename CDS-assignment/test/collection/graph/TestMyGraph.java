package collection.graph;

import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.datastructures.graph.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestMyGraph {
    MyGraph<String> myGraph;
    @BeforeEach
    void setUp() {
        myGraph = new MyGraph<>();
    }
    @Test
    void GivenEmptyGraph_WhenTestingGraphviz_ThenReturnNull() {
        assertNull(myGraph.graphViz());
    }

    @Test
    void GivenVertexInGraph_WhenGettingEdges_ThenReturnEdgeList() {
        myGraph.addEdge("A", "B", 1.0);
        SaxList<SaxGraph.DirectedEdge<String>> edges = myGraph.getEdges("A");

        assertEquals(1, edges.size());
        assertEquals("B", edges.get(0).to());
    }

    @Test
    void GivenVertexNotInGraph_WhenGettingEdges_ThenReturnEmptyList() {
        SaxList<SaxGraph.DirectedEdge<String>> edges = myGraph.getEdges("Z");
        assertTrue(edges.isEmpty());
    }

    @Test
    void GivenVertexWithEdges_WhenGettingEdge_ThenReturnCorrectEdge() {
        myGraph.addEdge("A", "B", 1.0);
        myGraph.addEdge("A", "C", 2.0);

        SaxGraph.DirectedEdge<String> edge = myGraph.getEdge("A", "B");
        assertNotNull(edge);
        assertEquals(1.0, edge.weight());
    }

    @Test
    void GivenVertexWithoutEdges_WhenGettingEdge_ThenReturnNull() {
        myGraph.addVertex("A");
        SaxGraph.DirectedEdge<String> edge = myGraph.getEdge("A", "B");
        assertNull(edge);
    }



}
