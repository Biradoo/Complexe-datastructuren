package collection.graph;

import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.datastructures.graph.MyGraph;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyMCST {
    private MyGraph<String> graph = new MyGraph<>();

    @Test
    void GivenGraph_WhenConstructingMCST_ThenCorrectEdgesAndWeightsSelected() {
        assertTrue(graph.isEmpty());

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "D", 3.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "E", 2.5);
        graph.addEdge("E", "F", 1.5);
        graph.addEdge("C", "F", 5.0);
        graph.addEdge("D", "E", 2.0);

        MyGraph<String> mcst = graph.minimumCostSpanningTree();

        assertEquals(6 ,mcst.size());
        assertEquals(10.0, mcst.getTotalWeight());

        assertEquals("[ " + new SaxGraph.DirectedEdge<>("A", "B", 1.0) + " ]", mcst.getEdges("A").toString());
        assertEquals("[ " + new SaxGraph.DirectedEdge<>("B", "C", 2.0) + " " +
                                     new SaxGraph.DirectedEdge<>("B", "E", 2.5) + " ]", mcst.getEdges("B").toString());
        assertEquals("[ " + new SaxGraph.DirectedEdge<>("C", "D", 3.0) + " ]", mcst.getEdges("C").toString());
        assertEquals("[ " + new SaxGraph.DirectedEdge<>("E", "F", 1.5) + " ]", mcst.getEdges("E").toString());

        for (Iterator<String> it = mcst.iterator(); it.hasNext(); ) {
            String vertex = it.next();
            SaxList<SaxGraph.DirectedEdge<String>> edges = mcst.getEdges(vertex);
            for (int i = 0; i < edges.size(); i++) {
                SaxGraph.DirectedEdge<String> edge = edges.get(i);
                String to = edge.to();

                boolean hasReverseEdge = false;
                SaxList<SaxGraph.DirectedEdge<String>> targetEdges = mcst.getEdges(to);
                for (int j = 0; j < targetEdges.size(); j++) {
                    if (targetEdges.get(j).to().equals(vertex)) {
                        hasReverseEdge = true;
                        break;
                    }
                }

                assertFalse(hasReverseEdge);
            }
        }
    }
}
