package controller;

import java.util.Scanner;

public class DeleteMenuHandler {
    public static String customerName;

    public static void delete() {
        System.out.println("Введите имя клиента, которого вы хотите удалить");
        Scanner scanner = new Scanner(System.in);
        customerName = scanner.nextLine();
    }
}
