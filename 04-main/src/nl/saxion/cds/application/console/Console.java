package nl.saxion.cds.application.console;

import nl.saxion.cds.application.models.Station;
import nl.saxion.cds.application.models.Track;
import nl.saxion.cds.application.readers.StationsCSVReader;
import nl.saxion.cds.application.readers.TracksCSVReader;
import nl.saxion.cds.application.utils.InputReader;
import nl.saxion.cds.collection.ValueNotFoundException;
import nl.saxion.cds.datastructures.MyArrayList;
import nl.saxion.cds.datastructures.MyHashMap;

import java.util.Comparator;


public class Console {
    private final MyHashMap<String, Station> stations = StationsCSVReader.readCSV("resources/stations.csv");
    private final MyArrayList<Track> tracks = TracksCSVReader.readCSV("resources/tracks.csv");
    private final InputReader inputReader = new InputReader();

    public Console() {
    }

    private void printStationsBasedOnTypeAlphabetical() {
        printDifferentTypes();
        int menuSelection = inputReader.readInt("Select a station type: ");

        String selectedType = getStationTypeFromMenu(menuSelection); //Map menu based on menu selection
        if (selectedType != null) {
            MyArrayList<Station> filteredStations = filterStationByType(selectedType); //Filter based on station type

            filteredStations.simpleSort(Comparator.comparing(Station::getName)); //Sort alphabetical by name

            filteredStations.forEach(station -> System.out.println(station.getName())); //Print all stations in alphabetical order
        } else {
            System.out.println("Please try again");
        }
    }


    private void printStationInfo() { //Start of option 1
        System.out.println("Please provide either station code or station (partial) name:");
        String input = inputReader.readString().toLowerCase();

        if (stations.contains(input)) { //If code is provided it can be checked first if its a existing code.
            printStation(stations.get(input));
        } else {
            MyArrayList<Station> matchingStations = findMatchingStations(input);

            if (matchingStations.isEmpty()) {
                throw new ValueNotFoundException("Station not found!");
            } else if (matchingStations.size() == 1) {
                printStation(matchingStations.get(0)); //Exact match will be printed immediately
            } else {
                handleMultipleMatches(matchingStations); //More than 1 match will need to filter more
            }
        }
    }

    private MyArrayList<Station> findMatchingStations(String input) {
        MyArrayList<Station> matchingStations = new MyArrayList<>();

        for (Station station : stations.values()) { //Find matching station
            String lowerCaseStation = station.getName().toLowerCase(); //To also search for partial given input
            if (input.equals(lowerCaseStation) || input.equalsIgnoreCase(station.getCode())) {
                matchingStations.addLast(station);
                return matchingStations; //Return a list with the exact match immediately
            }
            if (lowerCaseStation.contains(input)) {
                matchingStations.addLast(station);
            }
        }
        return matchingStations; //Stations containing the input will be returned
    }


    private void handleMultipleMatches(MyArrayList<Station> matchingStations) { //Handles the case where multiple stations are found
        System.out.println("Multiple stations found:");
        matchingStations.forEach(System.out::println); //Print all matching stations so user can choose from list

        System.out.print("Please type in the station code or exact name to make a choice: ");
        String input = inputReader.readString().toLowerCase();

        Station selectedStation = findExactMatch(matchingStations, input);

        printStation(selectedStation);
    }

    private Station findExactMatch(MyArrayList<Station> matchingStations, String input) { //Finds the exact match based on user's second input
        for (Station station : matchingStations) {
            String lowerCaseStation = station.getName().toLowerCase();
            if (input.equals(lowerCaseStation) || input.equalsIgnoreCase(station.getCode())) {
                return station; //Returns exact match
            }
        }
        throw new ValueNotFoundException("Station not found!");
    }

    private void printStation(Station station) {
        System.out.println("Found station:");
        System.out.println(station);
    }


    private String getStationTypeFromMenu(int selection) { //Maps the menu selection to a station type
        return switch (selection) {
            case 1 -> "megastation";
            case 2 -> "sneltreinstation";
            case 3 -> "stoptreinstation";
            case 4 -> "intercitystation";
            case 5 -> "facultatiefStation";
            case 6 -> "knooppuntSneltreinstation";
            case 7 -> "knooppuntStoptreinstation";
            case 8 -> "knooppuntIntercitystation";
            default -> null;
        };
    }

    private MyArrayList<Station> filterStationByType(String type) {
        MyArrayList<Station> filteredStations = new MyArrayList<>();
        //Filter basedo on type
        for (Station station : stations.values()) {
            if (station.getType().equalsIgnoreCase(type)) {
                filteredStations.addLast(station);
            }
        }
        return filteredStations;
    }

    private void printDifferentTypes() {
        System.out.println("***********************************");
        System.out.println("* 1. Mega station                 *");
        System.out.println("* 2. Sneltrein station            *");
        System.out.println("* 3. Stoptrein station            *");
        System.out.println("* 4. Intercity station            *");
        System.out.println("* 5. Facultatief station          *");
        System.out.println("* 6. Knooppunt Sneltrein station  *");
        System.out.println("* 7. Knooppunt Stoptrein station  *");
        System.out.println("* 8. Knooppunt Intercity stationn *");
        System.out.println("***********************************");
    }

    private void printMenu() {
        System.out.println("************************************************************");
        System.out.println("* 1. Information about a station                           *");
        System.out.println("* 2. All stations based on type                            *");
        System.out.println("* 3. Shortest route between stations                       *");
        System.out.println("* 4. Show min req. rail connections to go to every station *");
        System.out.println("************************************************************");
    }

    public void start() {
        while (true) { //Bad practice I know... just let me be.
            printMenu();
            int selection = inputReader.readInt("Select an option: ");

            switch (selection) {
                case 1:
                    printStationInfo();
                    break;
                case 2:
                    printStationsBasedOnTypeAlphabetical();
                    break;
                case 3:
                    //todo: - [ ] Determine shortest route from 1 station to another, show route and total length
                    break;
                case 4:
                    //todo: - [ ] Make an MSCT
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid selection. Please try again!");
            }
        }
    }
}
