package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Person;
import be.abis.broodjeorder.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileStudentRepository implements be.abis.broodjeorder.repository.StudentRepository {

    private List<Student> allStudents  = new ArrayList<>();
    private String fileLocation = "/temp/broodjes/students.csv";

    @PostConstruct
    public void init() {
        this.readFile();

    }


    public FileStudentRepository() {

    }

    public void readFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
            Student s = convertToStudentObj(line);
            allStudents.add(s);
        }

    }

    @Override
    public Student findStudent(String firstName, String lastName) throws PersonNotFoundException {
        Student foundStudent = allStudents.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new PersonNotFoundException("Student " + firstName +" not found or email does not match."));
        return foundStudent;
    }

    @Override
    public Student findStudentById(int id) throws PersonNotFoundException{
        Student foundStudent = allStudents.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElseThrow(() -> new PersonNotFoundException("Student with id " + id + " was not found."));
        return foundStudent;
    }

    @Override
    public List<Student> findStudentsByIds(int... ids) {
        ArrayList<Integer> idList = new ArrayList<>();
        for (int i : ids) {
            idList.add(i);
        }
        List<Student> foundStudentList = allStudents.stream()
                .filter(student -> idList.contains(student.getId()))
                .collect(Collectors.toList());

        return foundStudentList;
    }

    @Override
    public Student convertToStudentObj(String attr){
        String[] vals = attr.split(";");
        if(!vals[0].equals("")){
            Student p = new Student();
            p.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
            p.setFirstName(!vals[1].equals("null")? vals[1] : null );
            p.setLastName(!vals[2].equals("null") ? vals[2] : null);
            return p;
        }
        return null;
    }
    @Override
    public List<Student> findAllStudents() {
        return allStudents;
    }
}
