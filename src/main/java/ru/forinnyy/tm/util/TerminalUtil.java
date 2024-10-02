package ru.forinnyy.tm.util;

import lombok.NonNull;
import ru.forinnyy.tm.exception.field.NumberIncorrectException;

import java.util.Scanner;

public interface TerminalUtil {

    @NonNull
    Scanner SCANNER = new Scanner(System.in);

    @NonNull
    static String nextLine() {
        return SCANNER.nextLine();
    }

    @NonNull
    static Integer nextNumber() throws NumberIncorrectException {
        @NonNull final String value = nextLine();
        try {
            return Integer.parseInt(value);
        } catch (final RuntimeException e) {
            throw new NumberIncorrectException(value, e);
        }
    }

}
