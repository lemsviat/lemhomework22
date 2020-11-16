package controller;

import model.Account;
import model.AccountStatus;
import model.Customer;
import model.Specialty;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateMenuHandler {
    public Set<Specialty> customerSpecialties;
    public static String customerName;
    public static Specialty firstSpeciality = new Specialty();
    public static Specialty secondSpeciality = new Specialty();
    public static Specialty thirdSpeciality = new Specialty();
    public static Account customerAccount = new Account();

    public static Customer create() {
        System.out.println("Введите имя клиента");
        Scanner scanner = new Scanner(System.in);
        customerName = scanner.nextLine();
        System.out.println("Введите 1 специальность");
        firstSpeciality.setSpecialtyName(scanner.nextLine());
        System.out.println("Введите 2 специальность");
        secondSpeciality.setSpecialtyName(scanner.nextLine());
        System.out.println("Введите 3 специальность");
        thirdSpeciality.setSpecialtyName(scanner.nextLine());
        System.out.println("Введите сумму средств на аккаунте");
        customerAccount.setAccountValue(scanner.nextInt());
        System.out.println("Введите статус аккаунта: 1-ACTIVE, 2-BANNED, 3-DELETED");
        boolean isExitStatusMenu;
        do  {
            int valueToAccountStatus = scanner.nextInt();
            switch (valueToAccountStatus) {
                case (1):
                    customerAccount.setAccountStatus(AccountStatus.ACTIVE);
                    isExitStatusMenu=true;
                    break;
                case (2):
                    customerAccount.setAccountStatus(AccountStatus.BANNED);
                    isExitStatusMenu=true;
                    break;
                case (3):
                    customerAccount.setAccountStatus(AccountStatus.DELETED);
                    isExitStatusMenu=true;
                    break;
                default:
                    System.out.println("Введите число от 1 до 3");
                    isExitStatusMenu=false;
                    break;
            }
        } while (!isExitStatusMenu);
        Set<Specialty> specialties = Stream.of(firstSpeciality, secondSpeciality, thirdSpeciality)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return new Customer(customerName, specialties, customerAccount);
    }
}
