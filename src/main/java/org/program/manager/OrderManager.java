package org.program.manager;

import org.program.service.OrderService;
import org.program.service.Report;
import org.program.util.FileUtil;

import java.io.IOException;
import java.util.*;

public class OrderManager {
    public static void showResults( String inputFileName,
                                    String outputFileName,
                                    double costPerUnit,
                                    double startDiscount,
                                    double stepDiscount,
                                    double minDiscount
    ) throws IOException {
        List<String> orders = FileUtil.readOrders(inputFileName);

        List<Report> reports = OrderService.getReport(orders,
                                                      costPerUnit,
                                                      startDiscount,
                                                      stepDiscount,
                                                      minDiscount);
        List<String> lines = new ArrayList<>();
        for (Report report : reports) {
            lines.add(report.toString());
        }

        FileUtil.writeOrders(outputFileName, lines);
    }
}
