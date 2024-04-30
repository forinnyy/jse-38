package ru.forinnyy.tm.util;

import ru.forinnyy.tm.exception.field.NumberIncorrectException;

import java.util.Scanner;
import java.util.stream.IntStream;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    static String nextLine() {
        return SCANNER.nextLine();
    }

    static Integer nextNumber() {
        final String value = nextLine();
        try {
            return Integer.parseInt(value);
        } catch (final RuntimeException e) {
            throw new NumberIncorrectException(value, e);
        }
    }

}
