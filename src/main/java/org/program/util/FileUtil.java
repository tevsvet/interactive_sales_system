package org.program.util;

import java.io.*;
import java.util.*;

public class FileUtil {
    public static List<String> readOrders(String fileName) throws IOException {
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

    public static void writeOrders(String fileName, List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
