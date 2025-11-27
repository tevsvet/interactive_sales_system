package org.program.adapter;

import org.program.exception.UnsupportedFileFormatException;

import java.util.List;

public class OrderFileAdapterFactory {

    private final List<OrderFileAdapter> adapters;

    public OrderFileAdapterFactory(List<OrderFileAdapter> adapters) {
        this.adapters = adapters;
    }

    public OrderFileAdapter getAdapter(String fileName) {
        return adapters.stream()
                .filter(adapter -> adapter.supports(fileName))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedFileFormatException("Unsupported file format: " + fileName));
    }
}