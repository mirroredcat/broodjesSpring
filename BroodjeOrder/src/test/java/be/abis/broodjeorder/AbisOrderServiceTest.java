package be.abis.broodjeorder;

import be.abis.broodjeorder.exceptions.OrderAlreadyExistsException;
import be.abis.broodjeorder.exceptions.OrderNotFoundException;
import be.abis.broodjeorder.model.*;
import be.abis.broodjeorder.service.DayOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AbisOrderServiceTest {

    @Autowired
    DayOrderService dos;

    @Mock DayOrder dayOrder;

    @Mock Order o1;
    @Mock Order o2;
    @Mock Order o3;

    @Mock Person p1;
    @Mock Person p2;
    @Mock Person p3;

    @Mock Sandwich s1;
    @Mock Sandwich s2;

    @Mock Menu m1;


    @Test
    public void johnDoeOrderFound() throws OrderNotFoundException {
        List<Order> mockList = new ArrayList<>();
        when(this.p1.getFirstName()).thenReturn("John");
        when(this.p1.getLastName()).thenReturn("Doe");
        mockList.add(o1);
        mockList.add(o2);
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);


        assertEquals("John", dos.findOrder("John", "Doe").getPersonWhoOrdered().getFirstName());
    }

    @Test
    public void findOrderThrowsException (){
        List<Order> mockList = new ArrayList<>();
        when(this.p1.getFirstName()).thenReturn("John");
        when(this.p2.getFirstName()).thenReturn("Lemon");
        mockList.add(o1);
        mockList.add(o2);
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        assertThrows(OrderNotFoundException.class, ()-> dos.findOrder("Mary", "Doe"));
    }

    @Test
    public void addNewOrderTest() throws OrderAlreadyExistsException {
        List<Order> mockList = new ArrayList<>();
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);
        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        int beforeAdd = dos.getAllOrders().size();
        when(this.o3.getPersonWhoOrdered()).thenReturn(p3);
        dos.addOrder(o3);
        int afterAdd = dos.getAllOrders().size();

        assertEquals(afterAdd, beforeAdd + 1);

    }

    @Test
    public void personAlreadyOrdered(){
        List<Order> mockList = new ArrayList<>();
        when(this.o1.getPersonWhoOrdered()).thenReturn(p1);
        when(this.o2.getPersonWhoOrdered()).thenReturn(p2);
        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        assertThrows(OrderAlreadyExistsException.class, ()-> dos.addOrder(o2));
    }

    @Test
    public void removeOrderTest(){
        List<Order> mockList = new ArrayList<>();
        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        int beforeDelete = dos.getAllOrders().size();
        dos.deleteOrder(o1);
        int afterDelete = dos.getAllOrders().size();

        assertEquals(afterDelete, beforeDelete-1);
    }

    @Test
    public void dayTotalIs15(){
        List<Order> mockList = new ArrayList<>();

        when(this.s1.getPrice()).thenReturn(7.50);
        when(this.s2.getPrice()).thenReturn(7.50);

        when(this.o1.getOrderedSandwich()).thenReturn(s1);
        when(this.o2.getOrderedSandwich()).thenReturn(s2);

        mockList.add(o1);
        mockList.add(o2);

        when(this.dayOrder.getOrderList()).thenReturn(mockList);
        dos.setDayOrder(dayOrder);

        assertEquals(15.00, dos.calculateDayTotal());
    }

    @Test
    public void menuPrintsOutTest(){
        when(this.dayOrder.getDayMenu()).thenReturn(m1);
        when(this.m1.toString()).thenReturn("The menu prints correctly");

        dos.setDayOrder(dayOrder);

        assertEquals("The menu prints correctly", dos.printMenu());
    }




}
