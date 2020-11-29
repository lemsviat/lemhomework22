package controller;

import repository.jdbc.CustomerRepositoryImpl;
import repository.GenericRepository;
import repository.jdbc.SpecialtyRepositoryImpl;
import view.CreateMenuHandler;
import view.DeleteMenuHandler;
import view.ReadMenuHandler;
import view.UpdateMenuHandler;

public class MainController {
    GenericRepository customerRepository=new CustomerRepositoryImpl();
    GenericRepository specialtyRepository= new SpecialtyRepositoryImpl();

    public void create(){
        System.out.println(CreateMenuHandler.create());
        customerRepository.create();
        specialtyRepository.create();
    }
    public void read(){
        ReadMenuHandler.read();
        customerRepository.read();
    }
    public void update(){
        UpdateMenuHandler.update();
        System.out.println("Обновляются данные клиента с именем - " + UpdateMenuHandler.customerName);
        customerRepository.update();
    }
    public void delete(){
        DeleteMenuHandler.delete();
        System.out.println("Удаляется с базы клиент с именем - " + DeleteMenuHandler.customerName);
        customerRepository.delete();
    }
}
