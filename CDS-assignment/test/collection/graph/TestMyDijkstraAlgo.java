package collection.graph;

import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.datastructures.graph.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMyDijkstraAlgo {
    private MyGraph<String> graph;

    @BeforeEach
    void setup() {
        graph = new MyGraph<>();

        //Setup graph like in the sheets
        graph.addEdgeBidirectional("X", "Y", 1);
        graph.addEdgeBidirectional("X", "Z", 4);

        graph.addEdgeBidirectional("Y", "Z", 2);
        graph.addEdgeBidirectional("Y", "W", 3);
        graph.addEdgeBidirectional("Y", "V", 10);

        graph.addEdgeBidirectional("Z", "U", 3);
        graph.addEdgeBidirectional("Z", "W", 6);

        graph.addEdgeBidirectional("V", "W", 5);
        graph.addEdgeBidirectional("V", "T", 7);
        graph.addEdgeBidirectional("V", "U", 2);

        graph.addEdgeBidirectional("U", "T", 5);
        graph.addEdgeBidirectional("U", "W", 1);
        graph.addEdgeBidirectional("U", "V", 2);
    }

    @Test
    public void GivenGraph_WhenCalculatingShortestPathFromStartingNode_ThenPathsAreCorrectlyCalculated() {
        //Calculate shortest paths from starting node "X"
        graph.shortestPathsDijkstra("X");

        // Expected shortest distances from "X"
        assertEquals(0.0, graph.getDistance("X")); // X -> X
        assertEquals(1.0, graph.getDistance("Y")); // X -> Y
        assertEquals(3.0, graph.getDistance("Z")); // X -> Y -> Z
        assertEquals(4.0, graph.getDistance("W")); // X -> Y -> W
        assertEquals(7.0, graph.getDistance("V")); // X -> Y -> W -> U -> V
        assertEquals(10.0, graph.getDistance("T")); // X -> Y -> W -> U -> T
        assertEquals(5.0, graph.getDistance("U")); // X -> Y -> W -> U
    }

    @Test
    void GivenVertexWithDistance_WhenGettingDistance_ThenReturnCorrectDistance() {
        graph.addEdge("A", "B", 1.0);
        graph.shortestPathsDijkstra("A");
        assertEquals(0.0, graph.getDistance("A"));
    }

    @Test
    void GivenVertexWithoutDistance_WhenGettingDistance_ThenThrowException() {
        assertThrows(ValueNotFoundException.class, () -> graph.getDistance("Z"));
    }
}
