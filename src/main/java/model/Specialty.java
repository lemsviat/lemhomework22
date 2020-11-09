package model;

public class Specialty {
    String specialtyName;

    public Specialty() {
    }

    public Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    @Override
    public String toString() {
        return specialtyName;
    }
}
