package ru.forinnyy.tm.exception.field;


public final class PasswordEmptyException extends AbstractFieldException {

    public PasswordEmptyException() {
        super("Error! Password is empty...");
    }

}
