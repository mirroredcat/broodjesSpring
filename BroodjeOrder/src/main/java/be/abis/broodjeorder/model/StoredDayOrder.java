package be.abis.broodjeorder.model;

import java.time.LocalDate;

public class StoredDayOrder {

    int id;
    LocalDate date;
    int orderListSize;
    SandwichCompany sandwichCompany;
    double dayTotal;

    public StoredDayOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderListSize() {
        return orderListSize;
    }

    public void setOrderListSize(int orderListSize) {
        this.orderListSize = orderListSize;
    }

    public SandwichCompany getSandwichCompany() {
        return sandwichCompany;
    }

    public void setSandwichCompany(SandwichCompany sandwichCompany) {
        this.sandwichCompany = sandwichCompany;
    }

    public double getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(double dayTotal) {
        this.dayTotal = dayTotal;
    }
}
