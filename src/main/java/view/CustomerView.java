package view;

import controller.CustomerController;
import controller.SpecialtyController;
import model.Account;
import model.AccountStatus;
import model.Customer;
import model.Specialty;

import java.util.Set;

public class CustomerView {
    public static String customerName;
    public static Set<Specialty> specialties;
    public static Account customerAccount = new Account();
    CustomerController customerController = new CustomerController();
    SpecialtyController specialtyController = new SpecialtyController();
    SpecialtyView specialtyView=new SpecialtyView();

    public void create() {
        System.out.println("Введите имя клиента");
        customerName = InputChecker.readInput();
        //specialties = specialtyView.create();

        System.out.println("Введите сумму средств на аккаунте");
        customerAccount.setAccountValue(InputChecker.readIntInput());
        System.out.println("Введите статус аккаунта: 1-ACTIVE, 2-BANNED, 3-DELETED");
        boolean isExitStatusMenu;
        do {
            int valueToAccountStatus = InputChecker.readIntInput();
            switch (valueToAccountStatus) {
                case (1):
                    customerAccount.setAccountStatus(AccountStatus.ACTIVE);
                    isExitStatusMenu = true;
                    break;
                case (2):
                    customerAccount.setAccountStatus(AccountStatus.BANNED);
                    isExitStatusMenu = true;
                    break;
                case (3):
                    customerAccount.setAccountStatus(AccountStatus.DELETED);
                    isExitStatusMenu = true;
                    break;
                default:
                    System.out.println("Введите число от 1 до 3");
                    isExitStatusMenu = false;
                    break;
            }
        } while (!isExitStatusMenu);

        customerController.create();
        specialtyController.create();
        new Customer(customerName, specialties, customerAccount);

    }

    public void read() {
        System.out.println("Введите имя клиента, данные о котором вы хотите получить");
        customerName = InputChecker.readInput();
        customerController.read();
    }

    public static int customerChangeAccountValue;

    public void update() {
        System.out.println("Введите имя клиента, данные которого вы хотите обновить");
        customerName = InputChecker.readInput();
        System.out.println("Введите сумму, на которую вы хотите изменить аккаунт клиента");
        customerChangeAccountValue = InputChecker.readIntInput();
        customerController.update();
    }

    public void delete() {
        System.out.println("Введите имя клиента, которого вы хотите удалить");
        customerName = InputChecker.readInput();
        customerController.delete();
    }
}
