package org.program.adapter;

import org.program.exception.UnsupportedFileFormatException;

import java.util.List;

public class OrderFileAdapterFactory {

    private final List<OrderFileAdapter> adapters;

    public OrderFileAdapterFactory(List<OrderFileAdapter> adapters) {
        this.adapters = adapters;
    }

    public OrderFileAdapter getAdapter(String fileName) {

        for (OrderFileAdapter adapter : adapters) {
            if (adapter.supports(fileName)) {
                return adapter;
            }
        }
        throw new UnsupportedFileFormatException("Unsupported file format: " + fileName);
    }
}
