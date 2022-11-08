package be.abis.broodjeorder;

import be.abis.broodjeorder.exceptions.SessionNotFoundException;
import be.abis.broodjeorder.model.Session;
import be.abis.broodjeorder.repository.CourseRepository;
import be.abis.broodjeorder.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileSessionRepositoryTest {

    @Autowired
    SessionRepository sessionRepository;

    @Test
    public void sessionListGetsCreatedTest() {
        assertEquals(3, sessionRepository.findAllSessions().size());
    }

    @Test
    public void datessGetCreatedTest() {

        for (Session s : sessionRepository.findAllSessions()) {
            System.out.println(s.getDates());
        }

        System.out.println(LocalDate.now());
    }

    @Test
    public void findSessionTest() throws SessionNotFoundException {

        assertEquals(1,sessionRepository.findSessionsOfToday().size());
    }


}
