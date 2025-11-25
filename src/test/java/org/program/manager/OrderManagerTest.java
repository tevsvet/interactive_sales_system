package org.program.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import org.program.adapter.OrderFileAdapter;
import org.program.adapter.OrderFileAdapterFactory;
import org.program.order.Order;
import org.program.order.OrderReport;
import org.program.service.OrderService;
import org.program.util.FileUtil;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderManagerTest {

    @Mock
    private OrderService service;

    @Mock
    private OrderFileAdapterFactory factory;

    @Mock
    private OrderFileAdapter adapter;

    @InjectMocks
    private OrderManager manager;

    @DisplayName("Should correctly read input file, process orders and write result")
    @Test
    void process_shouldReadProcessAndWriteFileCorrectly() {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        double costPerUnit = 10.0;
        double startDiscount = 50.0;
        double stepDiscount = 5.0;
        double minDiscount = 0.0;

        List<String> fakeLines = List.of("line_1", "line_2");
        List<Order> fakeOrders = List.of(mock(Order.class));
        List<OrderReport> fakeReports = List.of(mock(OrderReport.class));

        try (MockedStatic<FileUtil> mockedFileUtil = mockStatic(FileUtil.class)) {
            mockedFileUtil.when(() -> FileUtil.readLines(inputFile))
                    .thenReturn(fakeLines);

            when(factory.getAdapter(inputFile)).thenReturn(adapter);

            when(adapter.getOrdersList(fakeLines)).thenReturn(fakeOrders);

            when(service.getReport(fakeOrders,
                    costPerUnit,
                    startDiscount,
                    stepDiscount,
                    minDiscount))
                    .thenReturn(fakeReports);

            manager.process(inputFile,
                            outputFile,
                            costPerUnit,
                            startDiscount,
                            stepDiscount,
                            minDiscount);

            mockedFileUtil.verify(() -> FileUtil.readLines(inputFile));

            verify(factory).getAdapter(inputFile);

            verify(adapter).getOrdersList(fakeLines);

            verify(service).getReport(fakeOrders,
                                      costPerUnit,
                                      startDiscount,
                                      stepDiscount,
                                      minDiscount);

            mockedFileUtil.verify(() ->
                    FileUtil.writeLines(eq(outputFile), anyList()));
        }
    }
}
