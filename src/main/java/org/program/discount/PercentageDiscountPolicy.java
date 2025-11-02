package org.program.discount;

import org.program.order.Product;
import java.util.Set;

public class PercentageDiscountPolicy extends AbstractDiscountPolicy {
    public PercentageDiscountPolicy(double percentageDiscount, Set<Product> eligibleProducts) {
        super(percentageDiscount, eligibleProducts);
    }

    @Override
    public double getDiscount() {
        return super.getDiscount() < 100 ? super.getDiscount() : 100;
    }

    @Override
    public double getCostWithDiscount(double cost) {
        return cost * (100 - getDiscount()) / 100;
    }
}