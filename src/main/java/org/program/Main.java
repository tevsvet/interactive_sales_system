package org.program;

import org.program.discount.StepDownDiscountPolicy;
import org.program.order.*;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Product cement = new Product("Cement", Unit.KG, 10);

        StepDownDiscountPolicy cementSale = new StepDownDiscountPolicy(
                50,
                5,
                0,
                Set.of(cement)
        );

        try {
            FileOrderReader reader = new FileOrderReader();
            FileOrderWriter writer = new FileOrderWriter();
            List<String> orders = reader.readOrders("discount_day.txt");
            OneProductOrderService service = new OneProductOrderService(orders, cement, cementSale);
            writer.writeOrders("report.txt", service.getReport());
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
