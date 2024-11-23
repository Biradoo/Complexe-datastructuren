package collection.graph;

import nl.saxion.cds.collection.EmptyCollectionException;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.collection.SaxList;
import nl.saxion.cds.datastructures.graph.HeuristicEstimator;
import nl.saxion.cds.datastructures.graph.MyGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import nl.saxion.cds.datastructures.Coordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyAStarAlgo {
    private MyGraph<Coordinate> graph;
    private SaxGraph.Estimator<Coordinate> distanceEstimator;

    private final Coordinate pointX = new Coordinate(51.0, 4.0);
    private final Coordinate pointY = new Coordinate(51.5, 4.5);
    private final Coordinate pointZ = new Coordinate(51.2, 4.2);
    private final Coordinate pointW = new Coordinate(51.4, 4.4);

    @BeforeEach
    public void initialize() {
        graph = new MyGraph<>();
        distanceEstimator = new HeuristicEstimator<>();

        graph.addEdge(pointX, pointY, 8);  // direct link
        graph.addEdge(pointX, pointZ, 6);  // indirect path via Z
        graph.addEdge(pointZ, pointW, 3);  // indirect path via W
        graph.addEdge(pointW, pointY, 4);  // indirect path to Y

    }

    @RepeatedTest(5)
    public void GivenDirectRoute_WhenUsingAStar_ThenSelectDirectPath() throws EmptyCollectionException {
        // Test direct route from pointX to pointY (expects path X->Y)
        SaxList<SaxGraph.DirectedEdge<Coordinate>> calculatedPath = graph.shortestPathAStar(pointX, pointY, distanceEstimator);

        // Assert the selected path is X -> Y
        assertEquals(1, calculatedPath.size());
        assertEquals(pointX, calculatedPath.get(0).from());
        assertEquals(pointY, calculatedPath.get(0).to());
        assertEquals(8, calculatedPath.get(0).weight(), 0.1);
    }

    @RepeatedTest(5)
    public void GivenIndirectRoute_WhenUsingAStar_ThenSelectIndirectPath() throws EmptyCollectionException {
        // Test indirect route from pointX to pointW (expects path X -> Z -> W)
        SaxList<SaxGraph.DirectedEdge<Coordinate>> calculatedPath = graph.shortestPathAStar(pointX, pointW, distanceEstimator);

        // Verify the chosen path is X -> Z -> W
        assertEquals(2, calculatedPath.size());

        // First step: X -> Z
        assertEquals(pointX, calculatedPath.get(0).from());
        assertEquals(pointZ, calculatedPath.get(0).to());
        assertEquals(6, calculatedPath.get(0).weight(), 0.1);

        // Second step: Z -> W
        assertEquals(pointZ, calculatedPath.get(1).from());
        assertEquals(pointW, calculatedPath.get(1).to());
        assertEquals(3, calculatedPath.get(1).weight(), 0.1);
    }
}
