package controller;

import repository.MySQLBase;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenuHandler {
    static int mainMenuResult;
    public static boolean isExit = false;

    public static void chooseAction() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        mainMenuResult = scanner.nextInt();
        switch (mainMenuResult) {
           case (1):
                System.out.println(CreateMenuHandler.create());
                MySQLBase.createCustomer();
                break;
            case (2):
                ReadMenuHandler.read();
                MySQLBase.readCustomer();
                break;
            case (3):
                UpdateMenuHandler.update();
                System.out.println("Обновляются данные клиента с именем - " + UpdateMenuHandler.customerName);
                MySQLBase.updateCustomer();
                break;
            case (4):
                DeleteMenuHandler.delete();
                System.out.println("Удаляется с базы клиент с именем - " + DeleteMenuHandler.customerName);
                MySQLBase.deleteCustomer();
                break;
            case (5):
                isExit = true;
                System.out.println("-Вы вышли из программы!-");
                break;
            default:
                System.out.println("Введите число от 1 до 5");
        }
    }
}
