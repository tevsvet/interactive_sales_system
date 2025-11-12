package org.program;

import org.program.exception.IORuntimeException;
import org.program.manager.OrderManager;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            var manager = new OrderManager();
            manager.process("discount_day.txt",
                                     "report.txt",
                                     10,
                                     50,
                                     5,
                                     0);
        } catch (IORuntimeException ex) {
            System.err.println("Error occurred while working with the file: " + ex.getMessage());
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            System.err.println("Unexpected error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
