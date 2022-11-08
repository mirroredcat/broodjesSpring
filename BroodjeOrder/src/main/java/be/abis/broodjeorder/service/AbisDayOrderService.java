package be.abis.broodjeorder.service;


import be.abis.broodjeorder.exceptions.OrderAlreadyExistsException;
import be.abis.broodjeorder.exceptions.OrderNotFoundException;
import be.abis.broodjeorder.model.DayOrder;
import be.abis.broodjeorder.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisDayOrderService implements DayOrderService{

    DayOrder dayO;

    public DayOrder getDayOrder() {
        return dayO;
    }

    public void setDayOrder(DayOrder dayO) {
        this.dayO = dayO;
    }

    @Override
    public List<Order> getAllOrders() {
        return dayO.getOrderList();
    }

    @Override
    public Order findOrder(String firstName, String lastName) throws OrderNotFoundException {
        return dayO.getOrderList().stream()
                .filter(o -> o.getPersonWhoOrdered().getFirstName().equals(firstName)
                        && o.getPersonWhoOrdered().getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new OrderNotFoundException(firstName + " " + lastName + " has not ordered today"));
    }

    @Override
    public void addOrder(Order o) throws OrderAlreadyExistsException {
        boolean foundO = dayO.getOrderList().stream()
                .anyMatch(order -> order.getPersonWhoOrdered().equals(o.getPersonWhoOrdered()));
        if (!foundO){
            dayO.getOrderList().add(o);
        } else {
            throw new OrderAlreadyExistsException(o.getPersonWhoOrdered().getFirstName() + " " + o.getPersonWhoOrdered().getLastName() + " has already ordered today");
        }
    }

    @Override
    public void deleteOrder(Order o) {
        dayO.getOrderList().remove(o);
    }

    @Override
    public double calculateDayTotal() {
        double dayTotal = dayO.getOrderList().stream()
                .mapToDouble(o -> o.getOrderedSandwich().getPrice())
                .sum();
        dayO.setDayTotal(dayTotal);
        return dayTotal;
    }

    @Override
    public String printDayOrder() {

        return null;
    }

    @Override
    public String printMenu() {
        return dayO.getDayMenu().toString();
    }
}
