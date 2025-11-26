package org.program.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedConstruction;
import org.program.exception.IORuntimeException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FileUtilTest {

    @Nested
    class ReaderTest {

        @TempDir
        Path tempDir;

        @Test
        void readLines_shouldReturnListOfStrings_whenFileIsCorrect() throws IOException {
            Path inputFile = tempDir.resolve("input.txt");
            List<String> expected = List.of("line_1", "line_2", "line_3");
            Files.write(inputFile, expected);

            List<String> actual = FileUtil.readLines(inputFile.toString());

            assertEquals(expected, actual);
        }

        @Test
        void readLines_shouldReturnListOfStrings_whenFileContainsEmptyLines() throws IOException {
            Path inputFile = tempDir.resolve("input.txt");
            List<String> input = List.of(
                    "line_1",
                    "",
                    "line_2",
                    "   ",
                    "line_3"
            );
            List<String> expected = List.of("line_1", "line_2", "line_3");
            Files.write(inputFile, input);

            List<String> actual = FileUtil.readLines(inputFile.toString());

            assertEquals(expected, actual);
        }

        @Test
        void readLines_shouldReturnEmptyList_whenFileContainsOnlyEmptyLines() throws IOException {
            Path inputFile = tempDir.resolve("input.txt");
            List<String> input = List.of(
                    "",
                    "   "
            );
            Files.write(inputFile, input);

            List<String> actual = FileUtil.readLines(inputFile.toString());

            assertTrue(actual.isEmpty());
        }

        @Test
        void readLines_shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
            Path inputFile = tempDir.resolve("input.txt");
            Files.write(inputFile, List.of());

            List<String> actual = FileUtil.readLines(inputFile.toString());

            assertTrue(actual.isEmpty());
        }

        @Test
        void readLines_shouldThrowIORuntimeException_whenFileDoesNotExist() {
            assertThrows(IORuntimeException.class, () ->
                    FileUtil.readLines("no_such_file.txt"));
        }

        @Test
         void readLines_shouldThrowIORuntimeException_whenIOExceptionOccurs() {
            try (MockedConstruction<FileReader> ignored = mockConstruction(
                    FileReader.class,
                    (reader, __) ->
                            when(reader.read(any(char[].class), anyInt(), anyInt()))
                                    .thenThrow(new IOException())
            )) {

                assertThrows(IORuntimeException.class, () ->
                        FileUtil.readLines("input.txt"));
            }
        }
    }

    @Nested
    class WriterTest {

        @TempDir
        Path tempDir;

        @Test
        void writeLines_shouldWriteStringsCorrectly() throws IOException {
            Path outputFile = tempDir.resolve("output.txt");
            List<String> expected = List.of("line_1", "line_2", "line_3");

            FileUtil.writeLines(outputFile.toString(), expected);

            List<String> actual = Files.readAllLines(outputFile);
            assertEquals(expected, actual);
        }

        @Test
        void writeLines_shouldCreateEmptyFile_whenListIsEmpty() throws IOException {
            Path outputFile = tempDir.resolve("output.txt");
            List<String> lines = List.of();

            FileUtil.writeLines(outputFile.toString(), lines);

            List<String> actual = Files.readAllLines(outputFile);
            assertTrue(actual.isEmpty());
        }

        @Test
        void writeLines_shouldThrowIORuntimeException_whenIOExceptionOccurs() {
            try (MockedConstruction<BufferedWriter> ignored = mockConstruction(
                    BufferedWriter.class,
                    (writer, __) ->
                            doThrow(new IOException())
                                    .when(writer).write(anyString())
            )) {

                assertThrows(IORuntimeException.class, () ->
                        FileUtil.writeLines("output.txt", List.of("line")));
            }
        }

        @Test
        void writeLines_shouldThrowIORuntimeException_duringWrite() {
            List<String> lines = List.of("line_1", "line_2", "line_3");

            try (MockedConstruction<BufferedWriter> ignored = mockConstruction(
                    BufferedWriter.class,
                    (writer, __) ->
                            doNothing().doThrow(new IOException())
                                    .when(writer).write(anyString())
            )) {

                assertThrows(IORuntimeException.class, () ->
                        FileUtil.writeLines("output.txt", lines));
            }
        }
    }
}
