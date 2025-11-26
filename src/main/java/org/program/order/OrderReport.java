package org.program.order;

public class OrderReport {
    private final String company;
    private final double totalCost;

    public OrderReport(String company, double totalCost) {
        this.company = company;
        this.totalCost = totalCost;
    }

    public String getCompany() {
        return company;
    }
    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return company + " - " + totalCost;
    }
}