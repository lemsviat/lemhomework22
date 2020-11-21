package view;

public class ReadMenuHandler {
    public static String customerName;

    public static void read() {
        System.out.println("Введите имя клиента, данные о котором вы хотите получить");
        customerName = InputChecker.readInput();
    }
}
