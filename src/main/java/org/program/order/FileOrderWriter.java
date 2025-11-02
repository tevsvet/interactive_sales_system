package org.program.order;

import java.io.*;
import java.io.IOException;
import java.util.List;

public class FileOrderWriter implements OrderWriter {

    public void writeOrders(String fileName, List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
