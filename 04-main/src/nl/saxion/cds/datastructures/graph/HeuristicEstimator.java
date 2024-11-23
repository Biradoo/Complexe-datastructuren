package nl.saxion.cds.datastructures.graph;

import nl.saxion.cds.application.models.Station;
import nl.saxion.cds.collection.SaxGraph;
import nl.saxion.cds.datastructures.Coordinate;
import nl.saxion.cds.datastructures.MyHashMap;

public class HeuristicEstimator<V> implements SaxGraph.Estimator<V> {
    private final MyHashMap<String, Station> stations;

    public HeuristicEstimator(MyHashMap<String, Station> stations) {
        this.stations = stations;
    }

    public HeuristicEstimator() {
        this.stations = null;
    }

    @Override
    public double estimate(V current, V target) {
        if (stations != null) {
            // Retrieve the Station objects from the map
            Station currentStation = stations.get((String) current);
            Station targetStation = stations.get((String) target);
            // Get the coordinates of each station
            Coordinate startCoord = new Coordinate(currentStation.getLatitude(), targetStation.getLongitude());
            Coordinate endCoord = new Coordinate(currentStation.getLatitude(), targetStation.getLongitude());
            // Calculate and return the haversine distance as the heuristic
            return Coordinate.haversineDistance(startCoord, endCoord);
        } else {
            if(!(current instanceof Coordinate) || !(target instanceof Coordinate)) {
                throw new ClassCastException("Current and Target must be coordinates.");
            }
            return Coordinate.haversineDistance((Coordinate) current, (Coordinate) target);
        }
    }
}
