package model;

import java.util.Set;

public class Customer {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private Set<Specialty> specialties;
    private Account account;

    public Customer(String name, Set<Specialty> specialties, Account account) {
        this.name = name;
        this.specialties = specialties;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "name='" + name + '\'' +
                ", specialties=" + specialties +
                ", account=" + account +
                '}';
    }
}
