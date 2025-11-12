package org.program.util;

import org.program.exception.IORuntimeException;

import java.io.*;
import java.util.*;

public class FileUtil {
    public static List<String> readLines(String fileName) {
        List<String> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                orders.add(line);
            }
        } catch (IOException ex) {
            throw new IORuntimeException("Error reading the file: " + fileName, ex);
        }

        return orders;
    }

    public static void writeLines(String fileName, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex){
            throw new IORuntimeException("Error while writing file: " + fileName, ex);
        }
    }
}
