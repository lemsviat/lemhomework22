package view;

import controller.AccountController;
import controller.CustomerController;
import controller.SpecialtyController;
import model.Account;
import model.Customer;
import model.Specialty;

import java.util.Set;

public class CustomerView {

    public static final String INPUT_CUSTOMER_NAME = "Введите имя клиента";
    /* public static final String INPUT_ACCOUNT_VALUE = "Введите сумму средств на аккаунте";
     public static final String INPUT_ACCOUNT_STATUS_MENU = "Введите статус аккаунта: 1-ACTIVE, 2-BANNED, 3-DELETED";
     public static final String INPUT_ACCOUNT_STATUS_CORRECTION = "Введите число от 1 до 3";*/
    public static final String READ_CUSTOMER_NAME = "Введите имя клиента, данные о котором вы хотите получить";
    public static final String UPDATE_CUSTOMER_NAME = "Введите имя клиента, данные которого вы хотите обновить";
    public static final String UPDATE_ACCOUNT_VALUE = "Введите сумму, на которую вы хотите изменить аккаунт клиента";
    public static final String DELETE_CUSTOMER_NAME = "Введите имя клиента, которого вы хотите удалить";


    public static String customerName;
    public static Set<Specialty> specialties;
    CustomerController customerController = new CustomerController();
    SpecialtyController specialtyController = new SpecialtyController();
    AccountController accountController = new AccountController();

    public void create() {
        System.out.println(INPUT_CUSTOMER_NAME);
        customerName = InputChecker.readInput();

        /*System.out.println(INPUT_ACCOUNT_VALUE);
        customerAccount.setAccountValue(InputChecker.readLongInput());
        System.out.println(INPUT_ACCOUNT_STATUS_MENU);
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
                    System.out.println(INPUT_ACCOUNT_STATUS_CORRECTION);
                    isExitStatusMenu = false;
                    break;
            }
        } while (!isExitStatusMenu);*/

        Account customerAccount = new AccountView().createAccount();

        customerController.create();
        accountController.create();
        specialtyController.create();

        System.out.println(new Customer(customerName, specialties, customerAccount));

    }

    public void read() {
        System.out.println(READ_CUSTOMER_NAME);
        customerName = InputChecker.readInput();
        customerController.read();
    }

    public static Long customerChangeAccountValue;

    public void update() {
        System.out.println(UPDATE_CUSTOMER_NAME);
        customerName = InputChecker.readInput();
        System.out.println(UPDATE_ACCOUNT_VALUE);
        customerChangeAccountValue = InputChecker.readLongInput();
        customerController.update();
    }

    public void delete() {
        System.out.println(DELETE_CUSTOMER_NAME);
        customerName = InputChecker.readInput();
        customerController.delete();
    }
}
