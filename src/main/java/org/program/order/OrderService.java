package org.program.order;

import org.program.discount.Discountable;
import java.time.format.DateTimeFormatter;
import java.util.List;

abstract class OrderService {
    static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    List<String> orders;
    Discountable discountPolicy;

    public OrderService(List<String> orders, Discountable discountPolicy) {
        this.orders = orders;
        this.discountPolicy = discountPolicy;
    }

    abstract List<Order> getOrdersList();
    abstract List<String> getReport();
}
