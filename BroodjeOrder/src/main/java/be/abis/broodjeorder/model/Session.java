package be.abis.broodjeorder.model;

import java.time.LocalDate;
import java.util.List;

public class Session {

    private int id;
    private Course courseName;
    private Teacher teacher;
    private List<Student> studentList;
    private List<LocalDate> dates;


    public Session() {
    }

    public Session(int id, Course courseName, Teacher teacher, List<Student> studentList, List<LocalDate> dates) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.studentList = studentList;
        this.dates = dates;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}
