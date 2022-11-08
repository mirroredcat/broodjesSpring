package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Manager;
import be.abis.broodjeorder.model.Person;
import be.abis.broodjeorder.model.Teacher;

import java.util.List;

public interface StaffRepository {


     Person findTeacherByName(String firstName, String lastName) throws PersonNotFoundException;
     Teacher findTeacherById(int id) throws PersonNotFoundException;
     Teacher convertToTeacherObj(String attr);
     List<Person> getAllStaff();
}
