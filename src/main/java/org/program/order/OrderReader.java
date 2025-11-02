package org.program.order;

import java.io.IOException;
import java.util.List;

public interface OrderReader {
    List<String> readOrders(String path) throws IOException;
}
