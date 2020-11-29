package controller;

import repository.GenericRepository;
import repository.jdbc.CustomerRepositoryImpl;
import view.*;

public class CustomerController {
    GenericRepository customerRepository=new CustomerRepositoryImpl();

    public void create(){
        customerRepository.create();
    }
    public void read(){
        customerRepository.read();
    }
    public void update(){
        //System.out.println("Обновляются данные клиента с именем - " + CustomerView.customerName);
        customerRepository.update();
    }
    public void delete(){
        //System.out.println("Удаляется с базы клиент с именем - " + CustomerView.customerName);
        customerRepository.delete();
    }
}
