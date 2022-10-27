package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.CourseNotFoundException;
import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.model.*;
import jdk.javadoc.internal.doclets.formats.html.DeprecatedListWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class FileSessionRepository implements SessionRepository {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StaffRepository staffRepository;

    private List<Session> sessionList = new ArrayList<>();
    private String fileLocation = "sessions.csv";


    public FileSessionRepository() throws CourseNotFoundException, PersonNotFoundException {

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String s : lines) {
            String[] vals = s.split(";");
            if (!vals[0].equals("")) {
                Session session = new Session();
                session.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
                session.setCourseName(!vals[1].equals("null") ? courseRepository.findCourseByID(Integer.parseInt(vals[1])) : null);
                session.setTeacher(!vals[2].equals("null") ? (Teacher) staffRepository.findPersonById(Integer.parseInt(vals[2])) : null);
                session.setStudentList(!vals[3].equals("null") ? studentRepository.findStudentsByIds(Integer.parseInt(vals[3])) : null);
                session.setDates(!vals[4].equals("null") ? convertToDatesListObj(vals[4]) : null);
                sessionList.add(session);
            }
        }
    }

    public List<LocalDate> convertToDatesListObj(String attr) {
        String[] vals = attr.split(",");
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> dateList = new ArrayList<>();
        for (String d : vals) {
            dateList.add(LocalDate.parse(d,dtm));
        }

        return dateList;
    }





}
