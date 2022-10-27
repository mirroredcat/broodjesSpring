package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.CourseNotFoundException;
import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.Course;
import be.abis.broodjeorder.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileCourseRepository implements CourseRepository{

    private List<Course> courseList = new ArrayList<>();
    private static String LOCATION = "courses.csv";

    @Autowired
    StaffRepository staffRepository;

    private List<Course> allCourses = new ArrayList<>();
    private String fileLocation = "courses.csv";


    public FileCourseRepository() throws PersonNotFoundException {

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
           Course c = convertToCourseObj(line);
            allCourses.add(c);
        }
    }


    @Override
    public Course findCourseByID(int id) throws CourseNotFoundException {

        Course foundCourse = allCourses.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElseThrow(()-> new CourseNotFoundException("Course not found")) ;
        return foundCourse;
    }

    @Override
    public Course convertToCourseObj(String attr) throws PersonNotFoundException {
        String[] vals = attr.split(";");
        if(!vals[0].equals("")){
            Course c = new Course();
            c.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
            c.setCourseName(!vals[1].equals("null")? vals[1] : null );
            c.setTeacher(!vals[2].equals("null")? (Teacher) staffRepository.findPersonById(Integer.parseInt(vals[2])) : null);
            return c;
        }
        return null;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }
}
