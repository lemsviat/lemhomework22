package view;

import controller.MainController;

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
               MainController.create();
               break;
            case (2):
                MainController.read();
                break;
            case (3):
                MainController.update();
                break;
            case (4):
                MainController.delete();
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
