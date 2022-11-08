package be.abis.broodjeorder;

import be.abis.broodjeorder.exceptions.SessionNotFoundException;
import be.abis.broodjeorder.model.Session;
import be.abis.broodjeorder.repository.CourseRepository;
import be.abis.broodjeorder.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class FileSessionRepositoryTest {

    @Autowired
    SessionRepository sessionRepository;

    @Test
    public void sessionListGetsCreatedTest() {
        assertEquals(3, sessionRepository.findAllSessions().size());
    }

    @Test
    public void findSessionsOfTodayTest() throws SessionNotFoundException {
        LocalDate currentLocalDate = LocalDate.of(2022,10,25);
        try (MockedStatic<LocalDate> mock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)){
            mock.when(()->LocalDate.now()).thenReturn(currentLocalDate);
            assertEquals(2,sessionRepository.findSessionsOfToday().size());
        }
    }

    @Test
    public void findSessionOfTodayThrowsExceptionTest() {
        LocalDate currentLocalDate = LocalDate.of(2032,10,25);
        try (MockedStatic<LocalDate> mock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)){
            mock.when(()->LocalDate.now()).thenReturn(currentLocalDate);
            assertThrows(SessionNotFoundException.class,()->sessionRepository.findSessionsOfToday());
        }

    }


}
