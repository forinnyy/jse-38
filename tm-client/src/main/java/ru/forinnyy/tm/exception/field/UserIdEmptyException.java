package ru.forinnyy.tm.exception.field;

public final class UserIdEmptyException extends AbstractFieldException {

    public UserIdEmptyException() {
        super("Error! User id is empty...");
    }
}
