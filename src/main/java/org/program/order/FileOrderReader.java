package org.program.order;

import java.io.*;
import java.util.*;

public class FileOrderReader implements OrderReader {

    public List<String> readOrders(String fileName) throws IOException {
        List<String> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                orders.add(line);
            }
        }

        return orders;
    }
}
