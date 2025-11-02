package org.program.discount;

import org.program.order.Product;
import java.util.Set;

public class StepDownDiscountPolicy extends PercentageDiscountPolicy {
    private final double step;
    private final double minPercentageDiscount;
    private int temp = 0;

    public StepDownDiscountPolicy(double startPercentageDiscount,
                                  double step,
                                  double minPercentageDiscount,
                                  Set<Product> eligibleProducts) {
        super(startPercentageDiscount, eligibleProducts);
        this.step = step;
        this.minPercentageDiscount = minPercentageDiscount;
    }

    @Override
    public double getDiscount() {
        double discount = super.getDiscount() - (step * temp);
        temp++;
        return Math.max(discount, minPercentageDiscount);
    }

    public double getStep() {
        return step;
    }
    public double getMinPercentageDiscount() {
        return minPercentageDiscount;
    }
}