package org.program.discount;

import org.program.order.Product;
import java.util.Set;

public abstract class AbstractDiscountPolicy implements Discountable {
    protected double discount;
    private Set<Product> eligibleProducts;

    AbstractDiscountPolicy(double discount, Set<Product> eligibleProducts) {
        this.discount = discount;
        this.eligibleProducts = eligibleProducts;
    }

    public double getDiscount() {
        return discount > 0 ? discount : 0;
    }

    public Set<Product> getEligibleProducts() { return eligibleProducts; }
}
