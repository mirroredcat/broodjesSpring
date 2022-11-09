package be.abis.broodjeorder.service;

import be.abis.broodjeorder.exceptions.DayOrderNotFoundException;
import be.abis.broodjeorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.broodjeorder.model.StoredDayOrder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryService {
    void addDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderAlreadyRegisteredException;

    void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException;

    StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException;

    StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException;

    List<StoredDayOrder> getAllStoredOrders();

    List<StoredDayOrder> getAllOrdersBySessionByID(int id) throws DayOrderNotFoundException;
}
