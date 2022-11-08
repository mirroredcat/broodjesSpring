package be.abis.broodjeorder.service;

import be.abis.broodjeorder.exceptions.OrderAlreadyExistsException;
import be.abis.broodjeorder.exceptions.OrderNotFoundException;
import be.abis.broodjeorder.model.DayOrder;
import be.abis.broodjeorder.model.Order;

import java.util.List;

public interface DayOrderService {

    Order findOrder(String firstName, String LastName) throws OrderNotFoundException;
    void addOrder(Order o) throws OrderAlreadyExistsException;
    void deleteOrder(Order o);
    double calculateDayTotal();
    String printDayOrder();
    String printMenu();
    DayOrder getDayOrder();
    void setDayOrder(DayOrder dayO);

    List<Order> getAllOrders();


}
