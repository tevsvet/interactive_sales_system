package org.program.util;

import org.program.exception.IORuntimeException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileUtil {
    public static List<String> readLines(String fileName) {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            return lines
                    .filter(line -> !line.isBlank())
                    .toList();

        } catch (IOException ex) {
            throw new IORuntimeException("Error reading the file: " + fileName, ex);
        }
    }

    public static void writeLines(String fileName, List<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException ex) {
                    throw new IORuntimeException("Failed to write line to file: " + fileName, ex);
                }
            });
        } catch (IOException ex){
            throw new IORuntimeException("Failed to open/close file: " + fileName, ex);
        }
    }
}
