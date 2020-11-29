package view;

import controller.CustomerController;
import controller.SpecialtyController;
import repository.ConnectionFactory;

public class MainView {
    static int mainMenuResult;
    public static boolean isExit = false;

    public static void chooseAction() {
        //CustomerController customerController = new CustomerController();
        //SpecialtyController specialtyController = new SpecialtyController();
        CustomerView customerView=new CustomerView();
        SpecialtyView specialtyView=new SpecialtyView();

        mainMenuResult = InputChecker.readIntInput();
        switch (mainMenuResult) {
            case (1):
                customerView.create();
                //specialtyView.create();
                //customerController.create();
                //specialtyController.create();
                break;
            case (2):
                customerView.read();
                //customerController.read();
                //specialtyController.read();
                break;
            case (3):
                customerView.update();
                //customerController.update();
                //specialtyController.update();
                break;
            case (4):
                customerView.delete();
                //customerController.delete();
                //specialtyController.delete();
                break;
            case (5):
                isExit = true;
                ConnectionFactory.closeConnection();
                System.out.println("-Вы вышли из приложения!-");
                break;
            default:
                System.out.println("Введите число от 1 до 5");
        }
    }

}
