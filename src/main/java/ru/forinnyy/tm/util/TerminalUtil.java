package ru.forinnyy.tm.util;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.field.NumberIncorrectException;

import java.util.Scanner;

public interface TerminalUtil {

    @NotNull
    Scanner SCANNER = new Scanner(System.in);

    @NotNull
    static String nextLine() {
        return SCANNER.nextLine();
    }

    @NotNull
    static Integer nextNumber() throws NumberIncorrectException {
        @NotNull final String value = nextLine();
        try {
            return Integer.parseInt(value);
        } catch (final RuntimeException e) {
            throw new NumberIncorrectException(value, e);
        }
    }

}
