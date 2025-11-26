package org.program.order;

import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime dateTime;
    private final String company;
    private final double quantity;

    public Order(LocalDateTime dateTime, String company, double quantity) {
        this.dateTime = dateTime;
        this.company = company;
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getCompany() {
        return company;
    }
    public double getQuantity() {
        return quantity;
    }
}