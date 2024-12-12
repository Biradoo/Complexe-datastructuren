package nl.saxion.cds.application.models;

public class Track {
    private String startPoint, endPoint;
    private int cost_unit;
    private double distance;

    public Track(String startPoint, String endPoint, int cost_unit, double distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.cost_unit = cost_unit;
        this.distance = distance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
