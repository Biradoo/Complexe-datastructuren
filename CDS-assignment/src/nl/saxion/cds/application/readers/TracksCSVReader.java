package nl.saxion.cds.application.readers;

import nl.saxion.cds.application.models.Track;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.graph.MyGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TracksCSVReader {
    public static MyGraph<String> readCSV(String filePath) {
        MyGraph<String> tracks = new MyGraph<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                String startPoint = parts[0];
                String endPoint = parts[1];
                double distance;

                try { //The double or int may not be parsed right, just an extra check
                    distance = Double.parseDouble(parts[3]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid latitude/longitude in line: " + line); //Debug line
                    continue;
                }
                tracks.addEdge(startPoint, endPoint, distance);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File can not be found");
        }
        return tracks;
    }
}
