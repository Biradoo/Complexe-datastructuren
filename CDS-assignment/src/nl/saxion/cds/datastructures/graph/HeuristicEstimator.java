package nl.saxion.cds.datastructures.graph;

import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.datastructures.Coordinate;

public class HeuristicEstimator<V> implements SaxGraph.Estimator<V> {

    @Override
    public double estimate(V current, V target) {
        if (!(current instanceof Coordinate) || !(target instanceof Coordinate)) {
            throw new ClassCastException("Current and Target must be instances of Coordinate.");
        }

        // Calculate and return the haversine distance as the heuristic
        return Coordinate.haversineDistance((Coordinate) current, (Coordinate) target);
    }
}
