package org.program.service;

import org.program.order.Order;
import org.program.order.OrderReport;

import java.util.*;

public class OrderService {

    public List<OrderReport> getReport(List<Order> orders,
                                              double costPerUnit,
                                              double startDiscount,
                                              double stepDiscount,
                                              double minDiscount) {

        Map<String,Double> totalCompanyOrders = new HashMap<>();
        int step = 0;

        for (Order order : orders) {
            double currentDiscount = calculateDiscount(startDiscount, stepDiscount, minDiscount, step);
            double cost = order.getQuantity() * costPerUnit;
            double costWithDiscount = cost * (1 - currentDiscount / 100);
            totalCompanyOrders.merge(order.getCompany(), costWithDiscount, Double::sum);
            step++;
        }

        List<OrderReport> reports = new ArrayList<>();
        for (Map.Entry<String, Double> entry : totalCompanyOrders.entrySet()) {
            reports.add(new OrderReport(entry.getKey(), entry.getValue()));
        }
        return reports;
    }

    private double calculateDiscount(double startDiscount,
                                     double stepDiscount,
                                     double minDiscount,
                                     int step) {
        double discount = startDiscount - (stepDiscount * step);
        return Math.max(minDiscount, Math.min(discount, 100));
    }
}
