package org.program.order;

import org.program.discount.Discountable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OneProductOrderService extends OrderService {
    private final Product product;

    public OneProductOrderService(List<String> orders, Product product, Discountable discountPolicy) {
        super(orders, discountPolicy);
        this.product = product;
    }

    protected List<Order> getOrdersList() {
        List<Order> ordersList = new ArrayList<>();

        for (String line : orders) {
            String[] parts = line.split("\\|");

            if (parts.length != 3) continue;

            LocalDateTime time = LocalDateTime.parse(parts[0], FORMATTER);
            String company = parts[1];
            double quantity = Double.parseDouble(parts[2]);

            ordersList.add(new Order(time, company, product, quantity));
        }

        ordersList.sort(Comparator.comparing(Order::getDateTime));
        return ordersList;
    }

    public List<String> getReport() {
        List<String> linesOfReport = new ArrayList<>();
        for (Order order : getOrdersList()) {
            linesOfReport.add(order.getCompany() + " - " + order.getCostWithDiscount(discountPolicy));
        }
        return linesOfReport;
    }
}