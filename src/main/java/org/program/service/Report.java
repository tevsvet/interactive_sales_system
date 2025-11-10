package org.program.service;

public class Report {
    private final String company;
    private final double totalCost;

    public Report(String company, double totalCost) {
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
