package nl.saxion.cds.application.readers;

import nl.saxion.cds.application.models.Station;
import nl.saxion.cds.application.models.Track;
import nl.saxion.cds.datastructures.MyArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TracksCSVReader {
    public static MyArrayList<Track> readCSV(String filePath) {
        MyArrayList<Track> tracks = new MyArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                String startPoint = parts[0];
                String endPoint = parts[1];
                int cost_unit;
                double distance;

                try { //The double or int may not be parsed right, just an extra check
                    cost_unit = Integer.parseInt(parts[2]);
                    distance = Double.parseDouble(parts[3]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid latitude/longitude in line: " + line); //Debug line
                    continue;
                }
                tracks.addLast(new Track(startPoint, endPoint, cost_unit, distance));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File can not be found");
        }
        return tracks;
    }
}
