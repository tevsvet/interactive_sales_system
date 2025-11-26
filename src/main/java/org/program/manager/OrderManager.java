package org.program.manager;

import org.program.adapter.OrderFileAdapter;
import org.program.adapter.OrderFileAdapterFactory;
import org.program.order.Order;
import org.program.service.OrderService;
import org.program.order.OrderReport;
import org.program.util.FileUtil;

import java.util.*;

public class OrderManager {
    private final OrderService orderService;
    private final OrderFileAdapterFactory adapterFactory;

    public OrderManager(OrderService orderService, OrderFileAdapterFactory adapterFactory) {
        this.orderService = orderService;
        this.adapterFactory = adapterFactory;
    }

    public void process(String inputFileName,
                        String outputFileName,
                        double costPerUnit,
                        double startDiscount,
                        double stepDiscount,
                        double minDiscount) {

        List<String> orders = FileUtil.readLines(inputFileName);

        OrderFileAdapter adapter = adapterFactory.getAdapter(inputFileName);
        List<Order> orderList = adapter.getOrdersList(orders);

        List<OrderReport> reports = orderService.getReport(orderList,
                                                      costPerUnit,
                                                      startDiscount,
                                                      stepDiscount,
                                                      minDiscount);

        List<String> lines = new ArrayList<>();
        for (OrderReport report : reports) {
            lines.add(report.toString());
        }

        FileUtil.writeLines(outputFileName, lines);
    }
}
