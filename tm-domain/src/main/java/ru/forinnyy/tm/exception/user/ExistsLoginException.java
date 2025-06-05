package ru.forinnyy.tm.exception.user;

public final class ExistsLoginException extends AbstractUserException {

    public ExistsLoginException() {
        super("Error! Login already exists...");
    }

}
