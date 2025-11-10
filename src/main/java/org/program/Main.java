package org.program;

import org.program.manager.OrderManager;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            OrderManager.showResults("discount_day.txt",
                                     "report.txt",
                                     10,
                                     50,
                                     5,
                                     0);
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
