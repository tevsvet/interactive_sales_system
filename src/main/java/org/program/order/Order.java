package org.program.order;

import java.time.LocalDateTime;
import org.program.discount.Discountable;

public class Order {
    private final LocalDateTime dateTime;
    private final String company;
    private final Product product;
    private final double quantity;

    Order(LocalDateTime dateTime, String company, Product product, double quantity) {
        this.dateTime = dateTime;
        this.company = company;
        this.product = product;
        this.quantity = quantity;
    }

    public double getCost() {
        return product.getPricePerUnit() * quantity;
    }
    public double getCostWithDiscount(Discountable discountPolicy) {
        return discountPolicy.getEligibleProducts().contains(product) ?
                discountPolicy.getCostWithDiscount(getCost()) : getCost();
    }

    public LocalDateTime getDateTime() { return dateTime; }
    public String getCompany() { return company; }
    public Product getProduct() { return product; }
    public double getQuantity() { return quantity; }
}