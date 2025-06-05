package ru.forinnyy.tm.exception.field;

public final class EmailEmptyException extends AbstractFieldException {

    public EmailEmptyException() {
        super("Error! Email is empty...");
    }

}
