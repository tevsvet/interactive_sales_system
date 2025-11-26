package org.program;

import org.program.adapter.HashOrderFileAdapter;
import org.program.adapter.OrderFileAdapter;
import org.program.adapter.OrderFileAdapterFactory;
import org.program.adapter.PipeOrderFileAdapter;
import org.program.manager.OrderManager;
import org.program.service.OrderService;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        OrderService service = new OrderService();

        List<OrderFileAdapter> adapters = List.of(
                new PipeOrderFileAdapter(),
                new HashOrderFileAdapter()
        );
        OrderFileAdapterFactory adapterFactory = new OrderFileAdapterFactory(adapters);

        OrderManager manager = new OrderManager(service, adapterFactory);

        manager.process("discount_day",
                        "report#.txt",
                        10,
                        50,
                        5,
                        0);
    }
}
