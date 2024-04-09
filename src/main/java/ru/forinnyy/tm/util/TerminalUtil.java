package ru.forinnyy.tm.util;

import java.util.Scanner;
import java.util.stream.IntStream;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    static String nextLine() {
        return SCANNER.nextLine();
    }

    static Integer nextNumber() {
        final String value = nextLine();
        return Integer.parseInt(value);
    }

}
