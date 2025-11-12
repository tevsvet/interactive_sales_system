package org.program.manager;

import org.program.adapter.OrderAdapter;
import org.program.order.Order;
import org.program.service.OrderService;
import org.program.order.OrderReport;
import org.program.util.FileUtil;

import java.util.*;

public class OrderManager {
    public void process(String inputFileName,
                        String outputFileName,
                        double costPerUnit,
                        double startDiscount,
                        double stepDiscount,
                        double minDiscount) {

        List<String> orders = FileUtil.readLines(inputFileName);
        List<Order> orderList = OrderAdapter.fromString(orders);

        var service = new OrderService();
        List<OrderReport> reports = service.getReport(orderList,
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
