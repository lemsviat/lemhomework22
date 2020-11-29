package controller;

import repository.GenericRepository;
import repository.jdbc.SpecialtyRepositoryImpl;

public class SpecialtyController {
    GenericRepository specialtyRepository= new SpecialtyRepositoryImpl();

    public void create(){
        specialtyRepository.create();
    }
    public void read(){
    }
    public void update(){
    }
    public void delete(){
    }
}
