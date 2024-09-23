package nl.saxion.cds.application.readers;

import nl.saxion.cds.application.models.Station;
import nl.saxion.cds.datastructures.MyHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StationsCSVReader {

    public static MyHashMap<String, Station> readCSV(String filePath) {
        MyHashMap<String, Station> stations = new MyHashMap<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                String code = parts[0];
                String name = parts[1];
                String country = parts[2];
                String type = parts[3];
                double latitude;
                double longitude;

                try { //The doubles may not be parsed right
                    latitude = Double.parseDouble(parts[4]);
                    longitude = Double.parseDouble(parts[5]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid latitude/longitude in line: " + line); //Debug line
                    continue;
                }
                stations.add(code, new Station(code, name, country, type, latitude, longitude));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File can not be found");
        }
        return stations;
    }
}
