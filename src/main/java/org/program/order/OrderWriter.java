package org.program.order;

import java.io.IOException;
import java.util.List;

public interface OrderWriter {
    void writeOrders(String path, List<String> lines) throws IOException;
}

