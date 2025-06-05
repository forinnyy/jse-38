package ru.forinnyy.tm.exception.field;

public final class NumberIncorrectException extends AbstractFieldException {

    public NumberIncorrectException() {
        super("Error! Number is incorrect...");
    }

    public NumberIncorrectException(final String value, Throwable cause) {
        super("Error! This value \"" + value + "\" is incorrect...");
    }

}
