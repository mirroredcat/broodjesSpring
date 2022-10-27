package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Student;

import java.util.List;

public interface StudentRepository {

    Student findStudent(String firstName, String lastName) throws PersonNotFoundException;
    Student findStudentById(int id) throws PersonNotFoundException;
    List<Student> findStudentsByIds(int... ids);
    Student convertToStudentObj(String attr);


}
