package controller;

import repository.GenericRepository;
import repository.jdbc.CustomerRepositoryImpl;
import view.*;

public class CustomerController {
    GenericRepository customerRepository=new CustomerRepositoryImpl();

    public void create(){
        //System.out.println(CreateMenuHandler.create());
        customerRepository.create();
    }
    public void read(){
        //ReadMenuHandler.read();
        customerRepository.read();
    }
    public void update(){
        //UpdateMenuHandler.update();
        System.out.println("Обновляются данные клиента с именем - " + CustomerView.customerName);
        customerRepository.update();
    }
    public void delete(){
        //DeleteMenuHandler.delete();
        System.out.println("Удаляется с базы клиент с именем - " + CustomerView.customerName);
        customerRepository.delete();
    }
}
