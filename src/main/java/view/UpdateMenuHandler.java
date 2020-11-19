package view;

import java.util.Scanner;

public class UpdateMenuHandler {
    public static String customerName;
    public static int customerChangeAccountValue;

    public static void update() {
        System.out.println("Введите имя клиента, данные которого вы хотите обновить");
        Scanner scanner = new Scanner(System.in);
        customerName = scanner.nextLine();
        System.out.println("Введите сумму, на которую вы хотите изменить аккаунт клиента");
        customerChangeAccountValue = scanner.nextInt();
    }
}
