package be.abis.broodjeorder.model;

import java.time.LocalDate;
import java.util.List;

public class DayOrder {

    private LocalDate date;
    private List<Order> orderList;
    private Menu dayMenu;
    private double dayTotal = 0.0;

    public DayOrder(LocalDate date, List<Order> orderList, Menu dayMenu) {
        this.date = date;
        this.orderList = orderList;
        this.dayMenu = dayMenu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Menu getDayMenu() {
        return dayMenu;
    }

    public void setDayMenu(Menu dayMenu) {
        this.dayMenu = dayMenu;
    }

    public double getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(double dayTotal) {
        this.dayTotal = dayTotal;
    }
}
