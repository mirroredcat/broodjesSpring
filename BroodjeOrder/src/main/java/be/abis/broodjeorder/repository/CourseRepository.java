package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.CourseNotFoundException;
import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Course;

public interface CourseRepository {

    Course findCourseByID(int id) throws CourseNotFoundException;
    Course convertToCourseObj(String attr) throws PersonNotFoundException;


}
