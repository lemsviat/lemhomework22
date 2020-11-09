package controller;

import java.util.Scanner;

public class ReadMenuHandler {
    public static String customerName;

    public static void read() {
        System.out.println("Введите имя клиента, данные о котором вы хотите получить");
        Scanner scanner = new Scanner(System.in);
        customerName = scanner.nextLine();
    }
}
