package org.program.adapter;

import org.program.order.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderAdapter {

    static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static List<Order> fromString(List<String> orders) {
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
}
