package view;

import model.Account;
import model.AccountStatus;

public class AccountView {
    public static Account customerAccount = new Account();

    public static final String INPUT_ACCOUNT_VALUE = "Введите сумму средств на аккаунте";
    public static final String INPUT_ACCOUNT_STATUS_MENU = "Введите статус аккаунта: 1-ACTIVE, 2-BANNED, 3-DELETED";
    public static final String INPUT_ACCOUNT_STATUS_CORRECTION = "Введите число от 1 до 3";

    public Account createAccount() {
        System.out.println(INPUT_ACCOUNT_VALUE);
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
        } while (!isExitStatusMenu);
        return customerAccount;
    }
}
