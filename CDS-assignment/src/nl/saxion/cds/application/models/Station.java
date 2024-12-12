package nl.saxion.cds.application.models;

import java.io.FileNotFoundException;

public class Station {
    private final String code, name, country, type;
    private final double latitude, longitude;
    public Station(String code, String name, String country, String type, double latitude, double longitude) throws FileNotFoundException {
        this.code = code;
        this.name = name;
        this.country = country;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Station name and code: ");
        sb.append(getName() + " " + getCode());
        sb.append("\nCountry and type of station: ");
        sb.append(getCountry() + " " + getType());
        sb.append("\nCoordinates (latitude | longitude): ");
        sb.append(getLatitude() + " | " + getLongitude() + "\n");

        return sb.toString();
    }
}
