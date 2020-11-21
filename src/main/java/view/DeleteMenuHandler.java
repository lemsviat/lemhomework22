package view;

public class DeleteMenuHandler {
    public static String customerName;

    public static void delete() {
        System.out.println("Введите имя клиента, которого вы хотите удалить");
        customerName = InputChecker.readInput();
    }
}
