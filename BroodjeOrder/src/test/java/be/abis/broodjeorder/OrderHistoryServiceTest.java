package be.abis.broodjeorder;

import be.abis.broodjeorder.exceptions.DayOrderNotFoundException;
import be.abis.broodjeorder.model.SandwichCompany;
import be.abis.broodjeorder.model.StoredDayOrder;
import be.abis.broodjeorder.service.OrderHistoryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderHistoryServiceTest {

    @Autowired
    OrderHistoryService orderHistoryService;


    static StoredDayOrder storedDayOrder;


    @BeforeAll
    static void setup() {
        storedDayOrder = new StoredDayOrder();
        storedDayOrder.setSandwichCompany(SandwichCompany.VLEUGELS);
        storedDayOrder.setOrderListSize(5);
        storedDayOrder.setDate(LocalDate.now());
        storedDayOrder.setId(1);
        storedDayOrder.setDayTotal(40.00);
        storedDayOrder.setSessionID(1);

    }

    @Test
    @Order(1)
    public void addOrderTest() throws IOException {
        orderHistoryService.addDayOrder(storedDayOrder);
        assertEquals(1, orderHistoryService.getAllStoredOrders().size());
    }

    @Test
    @Order(2)
    public void findOrderByIDTest() throws DayOrderNotFoundException {
        assertEquals("Vleugels", orderHistoryService.findDayOrderByID(1).getSandwichCompany().getCompanyName());
    }

    @Test
    @Order(3)
    public void findOrderByIDThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class, ()-> orderHistoryService.findDayOrderByID(99));
    }

    @Test
    @Order(4)
    public void findOrderByDateTest() throws DayOrderNotFoundException {
        assertEquals(1, orderHistoryService.findDayOrderByDate(LocalDate.now()).getId());
    }

    @Test
    @Order(5)
    public void findOrderByDateThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class, ()-> orderHistoryService.findDayOrderByDate(LocalDate.of(3023,9,21)));
    }

    @Test
    @Order(6)
    public void findOrderBySessionIDTest() throws DayOrderNotFoundException {
        assertEquals("Vleugels", orderHistoryService.getAllOrdersBySessionByID(1).get(0).getSandwichCompany().getCompanyName());
    }

    @Test
    public void findOrdeBySessionIDThrowsExceptionTest() {
        assertThrows(DayOrderNotFoundException.class,()->orderHistoryService.getAllOrdersBySessionByID(999));
    }

    @Test
    @Order(6)
    public void deleteDayOrderTest() throws IOException {
        int listSize = orderHistoryService.getAllStoredOrders().size();
        orderHistoryService.deleteDayOrder(storedDayOrder);
        assertEquals(listSize-1, orderHistoryService.getAllStoredOrders().size());
    }
}
