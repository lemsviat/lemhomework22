package controller;

import repository.GenericRepository;
import repository.jdbc.AccountRepositoryImpl;

public class AccountController {
    GenericRepository accountRepository= new AccountRepositoryImpl();

    public void create(){
        accountRepository.create();
    }
    public void read(){
    }
    public void update(){
    }
    public void delete(){
    }
}
