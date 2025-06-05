package ru.forinnyy.tm.exception.field;

public final class LoginEmptyException extends AbstractFieldException {

    public LoginEmptyException() {
        super("Error! Login is empty...");
    }

}
