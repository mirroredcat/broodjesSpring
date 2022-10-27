package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Manager;
import be.abis.broodjeorder.model.Person;
import be.abis.broodjeorder.model.Teacher;

public interface StaffRepository {

     Person findPerson(String firstName, String lastName) throws PersonNotFoundException;
     Person findPersonById(int id) throws PersonNotFoundException;
     Teacher convertToTeacherObj(String attr);
     Manager convertToManagerObj(String attr);


}
