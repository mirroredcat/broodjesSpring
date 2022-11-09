package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.DayOrderNotFoundException;
import be.abis.broodjeorder.exceptions.OrderAlreadyRegisteredException;
import be.abis.broodjeorder.model.StoredDayOrder;
import be.abis.broodjeorder.model.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrderHistoryRepository {

    void addDayOrder(StoredDayOrder storedDayOrder) throws IOException, OrderAlreadyRegisteredException;

    void deleteDayOrder(StoredDayOrder storedDayOrder) throws IOException;

    StoredDayOrder findDayOrderByDate(LocalDate localDate) throws DayOrderNotFoundException;

    StoredDayOrder findDayOrderByID(int id) throws DayOrderNotFoundException;

    StoredDayOrder convertToOrderObj(String attr);

    LocalDate convertDateToObj(String attr);

    List<StoredDayOrder> getDayOrderHistoryList();
}
