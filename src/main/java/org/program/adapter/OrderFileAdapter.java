package org.program.adapter;

import org.program.order.Order;

import java.util.List;

public interface OrderFileAdapter {

    boolean supports(String fileName);
    List<Order> getOrdersList(List<String> orders);
}
