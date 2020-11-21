package view;

import controller.MainController;

public class MainMenuHandler {
    static int mainMenuResult;
    public static boolean isExit = false;

    public static void chooseAction() {
        MainController mainController = new MainController();

        mainMenuResult = InputChecker.readIntInput();
        switch (mainMenuResult) {
            case (1):
                mainController.create();
                break;
            case (2):
                mainController.read();
                break;
            case (3):
                mainController.update();
                break;
            case (4):
                mainController.delete();
                break;
            case (5):
                isExit = true;
                System.out.println("-Вы вышли из приложения!-");
                break;
            default:
                System.out.println("Введите число от 1 до 5");
        }
    }

}
