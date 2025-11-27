package org.program.adapter;

import org.program.order.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class AbstractOrderFileAdapter implements OrderFileAdapter{

    protected static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final String separator;

    protected AbstractOrderFileAdapter(String separator) {
        this.separator = separator;
    }

    public List<Order> getOrdersList(List<String> orders) {
        return orders.stream()
                .map(line -> line.split(separator))
                .filter(parts -> parts.length == 3)
                .map(parts -> new Order(
                        LocalDateTime.parse(parts[0], FORMATTER),
                        parts[1],
                        Double.parseDouble(parts[2])
                ))
                .sorted(Comparator.comparing(Order::getDateTime))
                .toList();
    }
}
