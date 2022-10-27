package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Manager;
import be.abis.broodjeorder.model.Person;
import be.abis.broodjeorder.model.Student;
import be.abis.broodjeorder.model.Teacher;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileStaffRepository implements StaffRepository{

    private List<Person> allStaff  = new ArrayList<>();
    private String fileLocation = "staff.csv";


    public FileStaffRepository() {

        List<String> lines = null;
        boolean isManager = true;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
            if (!isManager) {
                Person p =  convertToTeacherObj(line);
                allStaff.add(p);

            } else {
                Person p = convertToManagerObj(line);
                allStaff.add(p);
                isManager = false;
            }
        }

    }

    @Override
    public Person findPerson(String firstName, String lastName) throws PersonNotFoundException {
        Person foundPerson = allStaff.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new PersonNotFoundException("Person " + firstName +" not found or email does not match."));
        return foundPerson;
    }

    @Override
    public Person findPersonById(int id) throws PersonNotFoundException{
        Person foundPerson = allStaff.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElseThrow(() -> new PersonNotFoundException("Person with id " + id + " was not found."));
        return foundPerson;
    }

    @Override
    public Teacher convertToTeacherObj(String attr){
        String[] vals = attr.split(";");
        if(!vals[0].equals("")){
            Teacher p = new Teacher();
            p.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
            p.setFirstName(!vals[1].equals("null")? vals[1] : null );
            p.setLastName(!vals[2].equals("null") ? vals[2] : null);
            return p;
        }
        return null;
    }
    @Override
    public  Manager convertToManagerObj(String attr){
        String[] vals = attr.split(";");
        if(!vals[0].equals("")){
            Manager p = new Manager();
            p.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
            p.setFirstName(!vals[1].equals("null")? vals[1] : null );
            p.setLastName(!vals[2].equals("null") ? vals[2] : null);
            return p;
        }
        return null;
    }

    public List<Person> getAllStaff() {
        return allStaff;
    }
}
