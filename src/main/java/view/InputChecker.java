package view;

import java.util.Scanner;

public class InputChecker {
    public static Scanner scanner = new Scanner(System.in);

    static int readIntInput() {
        int intInput = 0;
        boolean isInt = false;
        do {
            String input = readInput();
            try {
                intInput = Integer.parseInt(input);
                isInt = true;
            } catch (NumberFormatException e) {
                System.out.println("Введите, пожалуйста, именно ЧИСЛО :)");
            }
        } while (!isInt);

        return intInput;

    }

    static Long readLongInput() {
        long longInput = 0L;
        boolean isLong = false;
        do {
            String input = readInput();
            try {
                longInput = Long.parseLong(input);
                isLong = true;
            } catch (NumberFormatException e) {
                System.out.println("Введите, пожалуйста, именно ЧИСЛО :)");
            }
        } while (!isLong);

        return longInput;
    }

    public static String readInput() {
        String input;
        do {
            input = scanner.nextLine();
        } while (input == null);
        return input;
    }
}
