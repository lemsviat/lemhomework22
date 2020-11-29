package view;

import controller.SpecialtyController;
import model.AccountStatus;
import model.Specialty;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecialtyView {
    Set<Specialty> specialties= new LinkedHashSet<>();
    String inputSpecialty=" ";

    public Set<Specialty> create() {
        while(!inputSpecialty.equals("0")) {
            Specialty specialty = new Specialty();
            System.out.println("Введите специальность или 0 для выхода");
            inputSpecialty=InputChecker.readInput();
            specialty.setSpecialtyName(inputSpecialty);
            if(!inputSpecialty.equals("0")) specialties.add(specialty);
        }
        System.out.println("specialties= "+specialties);
        /*Set<Specialty> specialties = Stream.of(firstSpeciality, secondSpeciality, thirdSpeciality)
                .collect(Collectors.toCollection(LinkedHashSet::new));*/
        return specialties;
}
}
