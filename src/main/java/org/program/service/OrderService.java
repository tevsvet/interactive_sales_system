package org.program.service;

import org.program.order.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderService {

    static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static double calculateDiscount(double startDiscount,
                                            double stepDiscount,
                                            double minDiscount,
                                            int step) {
        double discount = startDiscount - (stepDiscount * step);
        return Math.max(minDiscount, Math.min(discount, 100));
    }

    private static List<Order> getOrdersList(List<String> orders) {
        List<Order> ordersList = new ArrayList<>();

        for (String line : orders) {
            String[] parts = line.split("\\|");

            if (parts.length != 3) continue;

            LocalDateTime time = LocalDateTime.parse(parts[0], FORMATTER);
            String company = parts[1];
            double quantity = Double.parseDouble(parts[2]);

            ordersList.add(new Order(time, company, quantity));
        }
        ordersList.sort(Comparator.comparing(Order::getDateTime));
        return ordersList;
    }

    public static List<Report> getReport(List<String> orders,
                                         double costPerUnit,
                                         double startDiscount,
                                         double stepDiscount,
                                         double minDiscount) {

        Map<String,Double> totalCompanyOrders = new HashMap<>();
        int step = 0;

        for (Order order : getOrdersList(orders)) {
            double currentDiscount = calculateDiscount(startDiscount, stepDiscount, minDiscount, step);
            double cost = order.getQuantity() * costPerUnit;
            double costWithDiscount = cost * (1 - currentDiscount / 100);
            totalCompanyOrders.merge(order.getCompany(), costWithDiscount, Double::sum);
            step++;
        }

        List<Report> reports = new ArrayList<>();
        for (Map.Entry<String, Double> entry : totalCompanyOrders.entrySet()) {
            reports.add(new Report(entry.getKey(), entry.getValue()));
        }
        return reports;
    }
}
