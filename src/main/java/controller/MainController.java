package controller;

import repository.MySQLBaseOperator;
import view.CreateMenuHandler;
import view.DeleteMenuHandler;
import view.ReadMenuHandler;
import view.UpdateMenuHandler;

import java.sql.SQLException;

public class MainController {
    public static void create(){
        System.out.println(CreateMenuHandler.create());
        try {
            MySQLBaseOperator.createCustomer();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public static void read(){
        ReadMenuHandler.read();
        try {
            MySQLBaseOperator.readCustomer();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public static void update(){
        UpdateMenuHandler.update();
        System.out.println("Обновляются данные клиента с именем - " + UpdateMenuHandler.customerName);
        try {
            MySQLBaseOperator.updateCustomer();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    public static void delete(){
        DeleteMenuHandler.delete();
        System.out.println("Удаляется с базы клиент с именем - " + DeleteMenuHandler.customerName);
        try {
            MySQLBaseOperator.deleteCustomer();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
