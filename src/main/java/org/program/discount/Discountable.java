package org.program.discount;

import org.program.order.Product;
import java.util.Set;

public interface Discountable {
    double getDiscount();
    double getCostWithDiscount(double count);
    Set<Product> getEligibleProducts();
}
