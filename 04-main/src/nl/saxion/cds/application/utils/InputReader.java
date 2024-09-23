package nl.saxion.cds.application.utils;

import java.util.Scanner;

public class InputReader {
    private final Scanner scanner = new Scanner(System.in);

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid value (unable to parse), please try again: ");
                scanner.next(); //Clears previous input
                System.out.println(prompt);
            }
        }
    }
}
