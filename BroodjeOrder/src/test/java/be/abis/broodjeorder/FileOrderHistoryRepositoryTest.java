package be.abis.broodjeorder;

import be.abis.broodjeorder.exceptions.DayOrderNotFoundException;
import be.abis.broodjeorder.model.SandwichCompany;
import be.abis.broodjeorder.model.StoredDayOrder;
import be.abis.broodjeorder.repository.OrderHistoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileOrderHistoryRepositoryTest {

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    static StoredDayOrder storedDayOrder;


    @BeforeAll
    static void setup() {
        storedDayOrder = new StoredDayOrder();
        storedDayOrder.setSandwichCompany(SandwichCompany.VLEUGELS);
        storedDayOrder.setOrderListSize(5);
        storedDayOrder.setDate(LocalDate.now());
        storedDayOrder.setId(1);
        storedDayOrder.setDayTotal(40.00);

    }

    @Test
    @Order(1)
    public void addDayOrderTest() throws IOException {
        orderHistoryRepository.addDayOrder(storedDayOrder);
        assertEquals(1,orderHistoryRepository.getDayOrderHistoryList().size());

    }

    @Test
    @Order(2)
    public void findAllDayStoredDayOrdersTest(){
        assertEquals(1,orderHistoryRepository.getDayOrderHistoryList().size());
    }

    @Test
    @Order(3)
    public void findDayOrderByIDTest() throws DayOrderNotFoundException {
        assertEquals("Vleugels", orderHistoryRepository.findDayOrderByID(1).getSandwichCompany().getCompanyName());
    }

    @Test
    @Order(4)
    public void findDayOrderByIDThrowsExceptionWhenNotFoundTest() {
        assertThrows(DayOrderNotFoundException.class,()->orderHistoryRepository.findDayOrderByID(99));
    }

    @Test
    @Order(5)
    public void findDayOrderByDateTest() throws DayOrderNotFoundException {
        assertEquals(1, orderHistoryRepository.findDayOrderByDate(LocalDate.now()).getId());
    }

    @Test
    @Order(6)
    public void findDayOrderByWrongDateThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class,()->orderHistoryRepository.findDayOrderByDate(LocalDate.of(2023,9,21)));
    }

    @Test
    @Order(7)
    public void deleteStoredOrderTest() throws IOException {
        orderHistoryRepository.deleteDayOrder(storedDayOrder);
        assertEquals(0,orderHistoryRepository.getDayOrderHistoryList().size());

    }

}
