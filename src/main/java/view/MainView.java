package view;

import repository.ConnectionFactory;

import java.util.Arrays;

public class MainView {
    static int mainMenuResult;
    public static boolean isExit = false;

    public static final String MAIN_MENU="\nВыберите операцию, которую вы хотите осуществить:\n" +
            "1 - Создать пользователя;\n" +
            "2 - Прочитать данные о пользователе;\n" +
            "3 - Изменить данные о пользователе;\n" +
            "4 - Удалить пользователя;\n" +
            "5 - Выйти из приложения.";
    public static final String GLOBAL_EXIT="-Вы вышли из приложения!-";
    public static final String MAIN_MENU_VARIANTS="Введите число от 1 до 5";

    public static void renderMainMenu(){
            System.out.println(MAIN_MENU);
    }

    public static void chooseAction() {
        CustomerView customerView=new CustomerView();

        mainMenuResult = InputChecker.readIntInput();
        switch (mainMenuResult) {
            case (1):
                customerView.create();
                break;
            case (2):
                customerView.read();
                break;
            case (3):
                customerView.update();
                break;
            case (4):
                customerView.delete();
                break;
            case (5):
                isExit = true;
                System.out.println(GLOBAL_EXIT);
                break;
            default:
                System.out.println(MAIN_MENU_VARIANTS);
        }
    }

}
