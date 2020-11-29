package view;

import model.Specialty;

import java.util.LinkedHashSet;
import java.util.Set;

public class SpecialtyView {

    public static final String INPUT_SPECIALTY_NAME = "Введите специальность или 0 для выхода";

    Set<Specialty> specialties = new LinkedHashSet<>();
    String inputSpecialty = " ";

    public Set<Specialty> create() {
        while (!inputSpecialty.equals("0")) {
            Specialty specialty = new Specialty();
            System.out.println(INPUT_SPECIALTY_NAME);
            inputSpecialty = InputChecker.readInput();
            specialty.setSpecialtyName(inputSpecialty);
            if (!inputSpecialty.equals("0")) specialties.add(specialty);
        }
        //System.out.println("specialties= "+specialties);
        return specialties;
    }
}
